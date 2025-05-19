const API_URL = "http://localhost:3000";

export const authService = {
  login: async (email: string, senha: string): Promise<string> => {
    const response = await fetch(`${API_URL}/auth`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, senha }),
    });

    if (!response.ok) {
      throw new Error("Erro ao fazer login");
    }

    const data = await response.json();
    localStorage.setItem("token", data.token);
    localStorage.setItem("tipoUsuario", data.tipoUsuario);
    return data.tipoUsuario;
  },

  logout: () => {
    localStorage.removeItem("token");
    localStorage.removeItem("tipoUsuario");
  },

  getToken: () => {
    return localStorage.getItem("token");
  },

  getTipoUsuario: () => {
    return localStorage.getItem("tipoUsuario");
  },

  isAuthenticated: () => {
    return !!localStorage.getItem("token");
  },
};
