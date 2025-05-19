const API_URL = "http://localhost:3000";

export const professorService = {
  enviarMoedas: async (
    professorId: number,
    alunoId: number,
    valor: number,
    mensagem: string
  ) => {
    const token = localStorage.getItem("token");

    const response = await fetch(
      `${API_URL}/professores/${professorId}/enviar-moedas`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ alunoId, valor, mensagem }),
      }
    );

    if (!response.ok) {
      throw new Error("Erro ao enviar moedas");
    }

    return await response.json();
  },

  getExtrato: async (professorId: number) => {
    const token = localStorage.getItem("token");

    const response = await fetch(
      `${API_URL}/professores/${professorId}/extrato`,
      {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (!response.ok) {
      throw new Error("Erro ao buscar extrato");
    }

    return await response.json(); // espera lista de transações
  },
};
