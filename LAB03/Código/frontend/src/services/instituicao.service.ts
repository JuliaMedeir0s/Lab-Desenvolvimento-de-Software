import axios from "axios";

const API_URL = "http://localhost:3000";

export interface Instituicao {
  id: number;
  nome: string;
}

export async function buscarInstituicoes(): Promise<Instituicao[]> {
  const response = await axios.get(`${API_URL}/instituicoes`);
  return response.data;
}