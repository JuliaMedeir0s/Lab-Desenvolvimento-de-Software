document.addEventListener('DOMContentLoaded', () => {
    carregarClientes();
});

//carrega a tabela com os cliente da base
function carregarClientes() {
    fetch(API.CLIENTES)
        .then(response => response.json())
        .then(clientes => {
            const tabela = document.getElementById('tabela-clientes');
            tabela.innerHTML = '';

            clientes.forEach(cliente => {
                const tr = document.createElement('tr');

                tr.innerHTML = `
            <td>${cliente.nome}</td>
            <td>${cliente.cpf}</td>
            <td>${cliente.profissao || '-'}</td>
            <td>
              <button class="btn btn-sm btn-warning" onclick="editarCliente(${cliente.id})">Editar</button>
              <button class="btn btn-sm btn-danger" onclick="deletarCliente(${cliente.id})">Excluir</button>
            </td>
          `;

                tabela.appendChild(tr);
            });
        })
        .catch(error => {
            showToast('Erro ao carregar cliente.', 'error');
            console.error('Erro ao carregar clientes:', error);
        });
}

//cadastra um novo cliente na base
function salvarCliente(event) {
    event.preventDefault();

    const clienteId = document.getElementById('cliente-id').value;

    const cliente = {
        nome: document.getElementById('cliente-nome').value,
        cpf: document.getElementById('cliente-cpf').value,
        rg: document.getElementById('cliente-rg').value,
        profissao: document.getElementById('cliente-profissao').value,
        endereco: {
            rua: document.getElementById('endereco-rua').value,
            numero: document.getElementById('endereco-numero').value,
            bairro: document.getElementById('endereco-bairro').value,
            cep: document.getElementById('endereco-cep').value,
            cidade: document.getElementById('endereco-cidade').value,
            estado: document.getElementById('endereco-estado').value
        },
        empregos: []
    };

    // Pegar vínculos empregatícios
    const empregos = document.querySelectorAll('.emprego-item');
    empregos.forEach(emprego => {
        const empresa = emprego.querySelector('.empresa').value;
        const renda = parseFloat(emprego.querySelector('.renda').value);

        if (empresa && !isNaN(renda)) {
            cliente.empregos.push({ empresa, renda });
        }
    });

    const metodo = clienteId ? 'PUT' : 'POST';
    const url = clienteId ? `${API.CLIENTES}/${clienteId}` : API.CLIENTES;

    fetch(url, {
        method: metodo,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(cliente)
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao salvar cliente');
            return response.json();
        })
        .then(() => {
            document.querySelector('#clienteModal .btn-close').click();
            carregarClientes();
            showToast('Cliente adicionado com sucesso!', 'success');
            limparFormulario();
        })
        .catch(error => {
            showToast('Erro ao salvar novo cliente.', 'error');
            alert(error.message);
        });
}

//responsavel por editar um cliente a partir do id
function editarCliente(id) {
    fetch(`${API.CLIENTES}/${id}`)
        .then(response => response.json())
        .then(cliente => {
            document.getElementById('cliente-id').value = cliente.id;
            document.getElementById('cliente-nome').value = cliente.nome;
            document.getElementById('cliente-cpf').value = cliente.cpf;
            document.getElementById('cliente-rg').value = cliente.rg;
            document.getElementById('cliente-profissao').value = cliente.profissao || '';

            const endereco = cliente.endereco || {};
            document.getElementById('endereco-rua').value = endereco.rua || '';
            document.getElementById('endereco-numero').value = endereco.numero || '';
            document.getElementById('endereco-bairro').value = endereco.bairro || '';
            document.getElementById('endereco-cep').value = endereco.cep || '';
            document.getElementById('endereco-cidade').value = endereco.cidade || '';
            document.getElementById('endereco-estado').value = endereco.estado || '';

            // Empregos
            const container = document.getElementById('empregos-container');
            container.innerHTML = '';

            (cliente.empregos || []).forEach(emprego => {
                const bloco = document.createElement('div');
                bloco.classList.add('emprego-item', 'row', 'g-2', 'mb-2');

                bloco.innerHTML = `
            <div class="col-8">
              <input type="text" class="form-control empresa" placeholder="Empresa" value="${emprego.empresa}" />
            </div>
            <div class="col-4">
              <input type="number" class="form-control renda" placeholder="Renda" value="${emprego.renda}" />
            </div>
          `;

                container.appendChild(bloco);
            });

            const modal = new bootstrap.Modal(document.getElementById('clienteModal'));
            modal.show();
        })
        .catch(error => {
            showToast('Erro ao carregar cliente.', 'error');
            console.error(error);
        });
}

//excluir um cliente da base
function deletarCliente(id) {
    const confirmacao = confirm('Tem certeza que deseja excluir este cliente?');

    if (!confirmacao) return;

    fetch(`${API.CLIENTES}/${id}`, {
        method: 'DELETE'
    })
        .then(() => {
            showToast('Cliente excluído com sucesso!', 'success');
            carregarClientes();
        })
        .catch(error => {
            showToast('Erro ao excluir cliente.', 'error');
        });
}

//cria os campos de entidades empregadoras
function adicionarEmprego() {
    const container = document.getElementById('empregos-container');
    const totalEmpregos = container.querySelectorAll('.emprego-item').length;

    if (totalEmpregos >= 3) {
        showToast('Você só pode adicionar até 3 vínculos empregatícios.', 'error');
        return;
    }

    const bloco = document.createElement('div');
    bloco.classList.add('emprego-item', 'row', 'g-2', 'mb-2');

    bloco.innerHTML = `
      <div class="col-8">
        <input type="text" class="form-control empresa" placeholder="Empresa" />
      </div>
      <div class="col-4 d-flex gap-2">
        <input type="number" class="form-control renda" placeholder="Renda" />
        <button class="btn btn-outline-danger" type="button" onclick="removerEmprego(this)">×</button>
      </div>
    `;

    container.appendChild(bloco);
}

function removerEmprego(botao) {
    const bloco = botao.closest('.emprego-item');
    bloco.remove();
}

//busca o endereço pelo CEP
function buscarEnderecoPorCEP() {
    const cep = document.getElementById('endereco-cep').value.replace(/\D/g, '');

    if (cep.length !== 8) return;

    fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(response => response.json())
        .then(data => {
            if (data.erro) {
                alert('CEP não encontrado.');
                return;
            }

            document.getElementById('endereco-rua').value = data.logradouro || '';
            document.getElementById('endereco-bairro').value = data.bairro || '';
            document.getElementById('endereco-cidade').value = data.localidade || '';
            document.getElementById('endereco-estado').value = data.uf || '';
        })
        .catch(() => {
            showToast('Erro ao buscar o CEP.', 'error');
        });
}

//limpa os campos do form depois de um novo cliente adicionado
function limparFormulario() {
    document.getElementById('cliente-id').value = '';
    document.getElementById('cliente-nome').value = '';
    document.getElementById('cliente-cpf').value = '';
    document.getElementById('cliente-rg').value = '';
    document.getElementById('cliente-profissao').value = '';
    document.getElementById('endereco-rua').value = '';
    document.getElementById('endereco-numero').value = '';
    document.getElementById('endereco-bairro').value = '';
    document.getElementById('endereco-cep').value = '';
    document.getElementById('endereco-cidade').value = '';
    document.getElementById('endereco-estado').value = '';
    document.getElementById('empregos-container').innerHTML = '';
}