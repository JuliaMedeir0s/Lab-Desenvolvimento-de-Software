document.addEventListener('DOMContentLoaded', () => {
    preencherSelectProfissoes();
});

//cadastra um novo cliente na base
function salvarCliente(event) {
    event.preventDefault();

    const clienteId = document.getElementById('cliente-id').value;

    const cliente = {
        nome: document.getElementById('cliente-nome').value,
        cpf: document.getElementById('cliente-cpf').value,
        rg: document.getElementById('cliente-rg').value,
        //validar a profissão
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
        },
        empregos: [],
        email: document.getElementById('cliente-email').value,
        senha: document.getElementById('cliente-senha').value
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

    const cpf = document.getElementById('cliente-cpf').value;
    if (!validarCPF(cpf)) {
        showToast('CPF inválido. Verifique os números digitados.', 'error');
        return;
    }

    const email = document.getElementById('cliente-email').value;
    const senha = document.getElementById('cliente-senha').value;

    if (!validarEmail(email)) {
        showToast('E-mail inválido. Verifique o formato.', 'error');
        return;
    }

    if (!validarSenha(senha)) {
        showToast('A senha deve conter pelo menos 6 caracteres.', 'error');
        return;
    }

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

//se selecionar "outra" aparece o campo para digitar
function verificarOutraProfissao() {
    const select = document.getElementById('cliente-profissao');
    const outroContainer = document.getElementById('profissao-outro-container');

    if (select.value === 'Outro') {
        outroContainer.classList.remove('d-none');
        document.getElementById('cliente-profissao-outro').required = true;
    } else {
        outroContainer.classList.add('d-none');
        document.getElementById('cliente-profissao-outro').required = false;
    }
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