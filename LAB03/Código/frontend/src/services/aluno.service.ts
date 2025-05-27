import axios from "axios";

const API_URL = "http://localhost:3000/auth";

export interface AlunoCadastroData {
  nome: string;
  email: string;
  senha: string;
  cpf: string;
  rg: string;
  instituicaoId: string;
  endereco: {
    rua: string;
    numero: string;
    cidade: string;
    uf: string;
    cep: string;
  };
}

export async function cadastrarAluno(data: AlunoCadastroData) {
  const response = await axios.post(`${API_URL}/register`, {
    ...data,
    tipoUsuario: "ALUNO",
  });

  return response.data;
}