const API_URL = "http://localhost:3000";

export const alunoService = {
  getExtrato: async (alunoId: number) => {
    const token = localStorage.getItem("token");

    const response = await fetch(`${API_URL}/alunos/${alunoId}/extrato`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      throw new Error("Erro ao buscar extrato");
    }

    return await response.json(); // espera lista de transações
  },

  resgatarVantagem: async (alunoId: number, vantagemId: number) => {
    const token = localStorage.getItem("token");

    const response = await fetch(`${API_URL}/alunos/${alunoId}/resgatar`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ vantagemId }),
    });

    if (!response.ok) {
      throw new Error("Erro ao resgatar vantagem");
    }

    return await response.json(); // deve retornar código ou mensagem
  },
};
