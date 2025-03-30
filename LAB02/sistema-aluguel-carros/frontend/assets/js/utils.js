// Toast
function showToast(message, type = 'success') {
  const toastArea = document.getElementById('toast-area');

  const toastId = 'toast-' + Date.now();
  const icon = type === 'success' ? '✅' : type === 'error' ? '❌' : 'ℹ️';

  const toast = document.createElement('div');
  toast.className = `toast align-items-center text-bg-${type} border-0 show mb-2`;
  toast.id = toastId;
  toast.setAttribute('role', 'alert');
  toast.setAttribute('aria-live', 'assertive');
  toast.setAttribute('aria-atomic', 'true');

  toast.innerHTML = `
      <div class="d-flex">
        <div class="toast-body">
          ${icon} ${message}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    `;

  toastArea.appendChild(toast);

  setTimeout(() => {
    const toastInstance = bootstrap.Toast.getOrCreateInstance(toast);
    toastInstance.hide();
    setTimeout(() => toast.remove(), 500);
  }, 3000);
}

// Máscaras
function aplicarMascaraCPF(input) {
  let valor = input.value.replace(/\D/g, '');
  if (valor.length > 11) valor = valor.slice(0, 11);
  valor = valor.replace(/(\d{3})(\d)/, '$1.$2');
  valor = valor.replace(/(\d{3})(\d)/, '$1.$2');
  valor = valor.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
  input.value = valor;
}

function aplicarMascaraRG(input) {
  let valor = input.value.replace(/\D/g, '');
  if (valor.length > 9) valor = valor.slice(0, 9);
  valor = valor.replace(/(\d{2})(\d)/, '$1.$2');
  valor = valor.replace(/(\d{3})(\d)/, '$1.$2');
  valor = valor.replace(/(\d{3})(\d{1})$/, '$1-$2');
  input.value = valor;
}

// Validação
function validarCPF(cpf) {
  cpf = cpf.replace(/[^\d]+/g, '');
  if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) return false;

  let soma = 0;
  for (let i = 0; i < 9; i++) soma += parseInt(cpf.charAt(i)) * (10 - i);
  let resto = 11 - (soma % 11);
  if (resto === 10 || resto === 11) resto = 0;
  if (resto !== parseInt(cpf.charAt(9))) return false;

  soma = 0;
  for (let i = 0; i < 10; i++) soma += parseInt(cpf.charAt(i)) * (11 - i);
  resto = 11 - (soma % 11);
  if (resto === 10 || resto === 11) resto = 0;
  return resto === parseInt(cpf.charAt(10));
}

function validarRG(rg) {
  rg = rg.replace(/\D/g, '');
  return rg.length >= 7 && rg.length <= 9;
}

function validarEmail(email) {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email.toLowerCase());
}

function validarSenha(senha) {
  return senha.length >= 6;
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