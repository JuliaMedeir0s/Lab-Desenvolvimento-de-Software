document.addEventListener('DOMContentLoaded', () => {
    const clienteId = sessionStorage.getItem('clienteId');
    if (!clienteId) {
        window.location.href = '../index.html';
        return;
    }

    carregarVeiculos();
    listarPedidos();
});

function carregarVeiculos() {
    fetch(API.VEICULOS)
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
    const clienteId = sessionStorage.getItem('clienteId');

    fetch(`${API.PEDIDOS}/cliente/${clienteId}`)
        .then(res => res.json())
        .then(pedidos => {
            const tbody = document.querySelector('#tabela-pedidos tbody');
            tbody.innerHTML = '';

            pedidos.forEach(pedido => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
            <td>${pedido.veiculo?.modelo || '---'}</td>
            <td>${formatarData(pedido.dataInicio)} até ${formatarData(pedido.dataFim)}</td>
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

    const clienteId = sessionStorage.getItem('clienteId');
    const pedido = {
        clienteId: parseInt(clienteId),
        veiculoId: parseInt(document.getElementById('veiculo-id').value),
        dataInicio: document.getElementById('data-inicio').value,
        dataFim: document.getElementById('data-fim').value,
        observacao: document.getElementById('observacao').value
    };

    fetch(API.PEDIDOS, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
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