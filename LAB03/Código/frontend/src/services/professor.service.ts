import axios from "axios";

const API_URL = "http://localhost:3000/professores";

export async function getProfessorInfo(token: string) {
  const res = await axios.get(`${API_URL}/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
}

export async function getExtratoProfessor(token: string) {
  const res = await axios.get(`${API_URL}/me/transacoes`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
}

export async function buscarAlunosDaInstituicao(token: string) {
  const res = await axios.get("/professores/me/alunos", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
}

export async function enviarMoedas(
  token: string,
  alunoId: number,
  valor: number,
  mensagem: string
) {
  const res = await axios.post(
    `/professores/me/enviar/${alunoId}`,
    { valor, mensagem },
    {
      headers: { Authorization: `Bearer ${token}` },
    }
  );
  return res.data;
}