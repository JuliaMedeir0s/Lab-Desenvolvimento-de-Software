const API_URL = "http://localhost:3000";

export const vantagemService = {
  listarTodas: async () => {
    const token = localStorage.getItem("token");

    const response = await fetch(`${API_URL}/vantagens`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      throw new Error("Erro ao listar vantagens");
    }

    return await response.json(); // retorna array de vantagens
  },

  buscarPorId: async (id: number) => {
    const token = localStorage.getItem("token");

    const response = await fetch(`${API_URL}/vantagens/${id}`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      throw new Error("Erro ao buscar vantagem");
    }

    return await response.json(); // retorna uma vantagem
  },
};
