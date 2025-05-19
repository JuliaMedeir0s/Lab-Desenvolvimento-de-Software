const API_URL = "http://localhost:3000";

export const parceiroService = {
  cadastrarVantagem: async (
    parceiroId: number,
    dados: {
      nome: string;
      descricao: string;
      imagem?: string;
      custo: number;
    }
  ) => {
    const token = localStorage.getItem("token");

    const response = await fetch(
      `${API_URL}/parceiros/${parceiroId}/vantagens`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(dados),
      }
    );

    if (!response.ok) {
      throw new Error("Erro ao cadastrar vantagem");
    }

    return await response.json();
  },

  listarVantagens: async (parceiroId: number) => {
    const token = localStorage.getItem("token");

    const response = await fetch(
      `${API_URL}/parceiros/${parceiroId}/vantagens`,
      {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (!response.ok) {
      throw new Error("Erro ao listar vantagens");
    }

    return await response.json();
  },
};
