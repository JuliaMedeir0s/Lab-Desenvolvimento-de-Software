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