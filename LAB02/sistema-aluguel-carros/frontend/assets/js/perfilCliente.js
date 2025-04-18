document.addEventListener('DOMContentLoaded', () => {
    carregarPerfil();
    preencherSelectProfissoes();
});

//preenche as opções do select de profissões
function preencherSelectProfissoes() {
    const select = document.getElementById('cliente-profissao');
    select.innerHTML = '<option value="">Selecione...</option>';

    PROFISSOES.forEach(profissao => {
        const option = document.createElement('option');
        option.value = profissao;
        option.textContent = profissao;
        select.appendChild(option);
    });
}

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

function carregarPerfil() {
    const usuarioId = getUserIdFromToken();

    const token = sessionStorage.getItem('token');

    if (!usuarioId || !token) {
        window.location.href = '../index.html';
        return;
    }

    fetch(`${API.CLIENTES}/${usuarioId}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(res => {
            if (!res.ok) throw new Error('Erro ao buscar perfil'); ;
            return res.json();
        })
        .then(cliente => {
            document.getElementById('cliente-nome').value = cliente.nome;
            document.getElementById('cliente-cpf').value = cliente.cpf;
            document.getElementById('cliente-rg').value = cliente.rg;

            // Profissão
            if (PROFISSOES.includes(cliente.profissao)) {
                document.getElementById('cliente-profissao').value = cliente.profissao;
                verificarOutraProfissao();
            } else {
                document.getElementById('cliente-profissao').value = 'Outro';
                verificarOutraProfissao();
                document.getElementById('cliente-profissao-outro').value = cliente.profissao;
            }

            // Endereço
            const e = cliente.endereco || {};
            document.getElementById('endereco-rua').value = e.rua || '';
            document.getElementById('endereco-numero').value = e.numero || '';
            document.getElementById('endereco-bairro').value = e.bairro || '';
            document.getElementById('endereco-cep').value = e.cep || '';
            document.getElementById('endereco-cidade').value = e.cidade || '';
            document.getElementById('endereco-estado').value = e.estado || '';

            // Empregos
            const container = document.getElementById('empregos-container');
            container.innerHTML = '';
            (cliente.empregos || []).forEach(emprego => {
                const div = document.createElement('div');
                div.classList.add('row', 'g-2', 'mb-2');

                div.innerHTML = `
            <div class="col-8">
              <input type="text" class="form-control" value="${emprego.empresa}" readonly>
            </div>
            <div class="col-4">
              <input type="number" class="form-control" value="${emprego.renda}" readonly>
            </div>
          `;
                container.appendChild(div);
            });
        })
        .catch(erro => {
            showToast('Erro ao carregar perfil', 'error');
            console.error(erro);
        });
}

function habilitarCampo(id) {
    const el = document.getElementById(id);
    el.removeAttribute('readonly');
    el.removeAttribute('disabled');
    el.focus();
    mostrarBotaoSalvar();
}

function mostrarBotaoSalvar() {
    document.getElementById('btn-salvar').style.display = 'inline-block';
}

function atualizarCliente(event) {
    event.preventDefault();

    const clienteId = sessionStorage.getItem('clienteId');
    if (!clienteId) return;

    const cliente = {
        nome: document.getElementById('cliente-nome').value,
        cpf: document.getElementById('cliente-cpf').value,
        rg: document.getElementById('cliente-rg').value,
        profissao: document.getElementById('cliente-profissao').value === 'Outro'
            ? document.getElementById('cliente-profissao-outro').value
            : document.getElementById('cliente-profissao').value,
        endereco: {
            rua: document.getElementById('endereco-rua').value,
            numero: document.getElementById('endereco-numero').value,
            bairro: document.getElementById('endereco-bairro').value,
            cep: document.getElementById('endereco-cep').value,
            cidade: document.getElementById('endereco-cidade').value,
            estado: document.getElementById('endereco-estado').value
        }
    };

    fetch(`${API.CLIENTES}/${usuarioId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(cliente)
    })
        .then(res => {
            if (!res.ok) throw new Error('Erro ao salvar');
            showToast('Dados atualizados com sucesso!', 'success');
            document.getElementById('btn-salvar').style.display = 'none';
        })
        .catch(err => {
            showToast(err.message, 'error');
        });
}

function confirmarExclusao() {
    if (confirm('Tem certeza que deseja excluir sua conta? Esta ação não poderá ser desfeita.')) {
        excluirConta();
    }
}

function excluirConta() {
    const clienteId = sessionStorage.getItem('clienteId');
    fetch(`${API.CLIENTES}/${usuarioId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(res => {
            if (!res.ok) throw new Error('Erro ao excluir conta');
            sessionStorage.removeItem('clienteId');
            showToast('Conta excluída com sucesso!', 'success');
            setTimeout(() => window.location.href = 'index.html', 1500);
        })
        .catch(err => {
            showToast(err.message, 'error');
        });
}
