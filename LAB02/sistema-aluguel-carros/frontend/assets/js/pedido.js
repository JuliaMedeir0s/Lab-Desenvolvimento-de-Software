document.addEventListener('DOMContentLoaded', () => {
    // const clienteId = sessionStorage.getItem('clienteId');
    // if (!clienteId) {
    //     window.location.href = '../index.html';
    //     return;
    // }

    carregarVeiculos();
    listarPedidos();
});

function getUserIdFromToken() {
    // Recupera o token do sessionStorage
    const token = sessionStorage.getItem('token');

    if (!token) {
        console.error("Token não encontrado!");
        return null;
    }

    // Decodifica o token JWT (sem validar a assinatura)
    const payloadBase64 = token.split('.')[1]; // Pega a parte do payload
    const payloadJson = atob(payloadBase64); // Converte de Base64 para JSON
    const payload = JSON.parse(payloadJson); // Converte JSON para objeto

    return payload.id; // Retorna o ID do usuário
}

function carregarUsuarioLogado(token) {

    fetch(`${API.CLIENTES}/me`, {
        headers: { 'Authorization': `Bearer ${token}` }
    })
        .then(res => {
            if (!res.ok) throw new Error('Erro ao carregar informações do usuário.');
            return res.json();
        })
        .then(usuario => {
            console.log('Usuário logado:', usuario);
            
        })
        .catch(err => {
            console.error(err.message);
            showToast('Erro ao carregar informações do usuário.', 'error');
        });
}

function carregarVeiculos() {
    const token = sessionStorage.getItem('token');

    fetch(API.VEICULOS, {
        headers: { 'Authorization': `Bearer ${token}` }
    })
        .then(res => res.json())
        .then(veiculos => {
            const select = document.getElementById('veiculo-id');
            select.innerHTML = '';
            veiculos.forEach(veiculo => {
                const option = document.createElement('option');
                option.value = veiculo.id;
                option.textContent = `${veiculo.marca} ${veiculo.modelo} (${veiculo.placa})`;
                select.appendChild(option);
            });
        })
        .catch(() => {
            showToast('Erro ao carregar veículos.', 'error');
        });
}

function listarPedidos() {
    const token = sessionStorage.getItem('token');
    const clienteId =  getUserIdFromToken();

    fetch(`${API.PEDIDOS}/cliente/${clienteId}`, {
        headers: {
             'Authorization': `Bearer ${token}` }
    })
        .then(res => res.json())
        .then(pedidos => {
            const tbody = document.querySelector('#tabela-pedidos tbody');
            tbody.innerHTML = '';

            pedidos.forEach(pedido => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
            <td>${pedido.veiculoId?.modelo || '---'}</td>
            <td>${formatarData(pedido.data_inicio)} até ${formatarData(pedido.data_fim)}</td>
            <td><span class="badge bg-${corStatus(pedido.status)}">${pedido.status}</span></td>
          `;
                tbody.appendChild(tr);
            });
        })
        .catch(() => {
            showToast('Erro ao carregar pedidos.', 'error');
        });
}

function criarPedido(event) {
    event.preventDefault();

    const token = sessionStorage.getItem('token');
    const clienteId = getUserIdFromToken();
    const pedido = {
        UsuarioId: parseInt(clienteId),
        veiculoId: parseInt(document.getElementById('veiculo-id').value),
        data_inicio: document.getElementById('data-inicio').value,
        data_fim: document.getElementById('data-fim').value,
        observacao: document.getElementById('observacao').value
    };

    fetch(API.PEDIDOS, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
            'Authorization': `Bearer ${sessionStorage.getItem('token')}`
        },
        body: JSON.stringify(pedido)
    })
        .then(res => {
            if (!res.ok) throw new Error('Erro ao enviar pedido.');
            return res.json();
        })
        .then(() => {
            showToast('Pedido enviado com sucesso!', 'success');
            const modal = bootstrap.Modal.getInstance(document.getElementById('modalNovoPedido'));
            modal.hide();
            listarPedidos();
        })
        .catch(err => {
            showToast(err.message, 'error');
        });
}

// Funções auxiliares
function formatarData(dataISO) {
    const [ano, mes, dia] = dataISO.split('T')[0].split('-');
    return `${dia}/${mes}/${ano}`;
}

function corStatus(status) {
    switch (status.toLowerCase()) {
        case 'aprovado': return 'success';
        case 'rejeitado': return 'danger';
        default: return 'secondary';
    }
}