//mostra a toast
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