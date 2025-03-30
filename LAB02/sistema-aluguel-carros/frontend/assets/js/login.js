document.addEventListener('DOMContentLoaded', () => {

});

function login(event) {
    event.preventDefault();

    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;

    if (!validarEmail(email)) {
        showToast('E-mail inválido.', 'error');
        return;
    }

    if (!validarSenha(senha)) {
        showToast('A senha deve ter pelo menos 6 caracteres.', 'error');
        return;
    }

    fetch(`${API.BASE}/auth`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha })
    })
        .then(res => {
            if (!res.ok) throw new Error('E-mail ou senha incorretos.');
            return res.json();
        })
        .then(data => {
            sessionStorage.setItem('token', data.token);
            sessionStorage.setItem('usuarioId', data.id);
            sessionStorage.setItem('usuarioNome', data.nome);
            sessionStorage.setItem('usuarioTipo', data.tipo);

            showToast('Login realizado com sucesso!', 'success');
            setTimeout(() => window.location.href = 'pages/pedido.html', 1000);
        })
        .catch(err => {
            showToast(err.message, 'error');
        });
}

function recuperarSenha(event) {
    event.preventDefault();

    const email = document.getElementById('email-recuperacao').value.trim();

    if (!validarEmail(email)) {
        showToast('Digite um e-mail válido.', 'error');
        return;
    }

    fetch(`${API.BASE}/recuperar-senha`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email })
    })
        .then(res => {
            if (!res.ok) throw new Error('E-mail não encontrado.');
            return res.text();
        })
        .then(() => {
            showToast('Código enviado para o seu e-mail.', 'success');

            document.getElementById('email-redefinicao').value = email;

            // Fecha o primeiro modal
            const modalRecuperar = bootstrap.Modal.getInstance(document.getElementById('modalRecuperarSenha'));
            modalRecuperar.hide();

            // Abre o segundo modal
            const modalRedefinir = new bootstrap.Modal(document.getElementById('modalRedefinirSenha'));
            modalRedefinir.show();
        })
        .catch(err => {
            showToast(err.message, 'error');
        });
}

function redefinirSenha(event) {
    event.preventDefault();

    const email = document.getElementById('email-redefinicao').value.trim();
    const codigo = document.getElementById('codigo-redefinicao').value.trim();
    const novaSenha = document.getElementById('nova-senha').value;
    const confirmarSenha = document.getElementById('confirmar-senha').value;

    if (!validarSenha(novaSenha)) {
        showToast('A nova senha deve ter pelo menos 6 caracteres.', 'error');
        return;
    }

    if (novaSenha !== confirmarSenha) {
        showToast('As senhas não coincidem.', 'error');
        return;
    }

    fetch(`${API.BASE}/redefinir-senha`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, codigo, novaSenha })
    })
        .then(res => {
            if (!res.ok) throw new Error('Código inválido ou expirado.');
            return res.text();
        })
        .then(() => {
            showToast('Senha redefinida com sucesso!', 'success');
            const modal = bootstrap.Modal.getInstance(document.getElementById('modalRedefinirSenha'));
            modal.hide();
        })
        .catch(err => {
            showToast(err.message, 'error');
        });
}
