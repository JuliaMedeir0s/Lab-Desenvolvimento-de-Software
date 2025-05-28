import axios from "axios";

const API_URL = "http://localhost:3000/auth";

export interface AlunoCadastroData {
  nome: string;
  email: string;
  senha: string;
  tipoUsuario: string;
  cpf: string;
  rg: string;
  instituicaoId: number;
  endereco: {
    rua: string;
    numero: string;
    bairro: string;
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

// Busca as informações do aluno
export async function getAlunoInfo() {
  const token = localStorage.getItem("token");

  const response = await axios.get("http://localhost:3000/alunos/me", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return response.data;
}

// Busca as transações do aluno
export async function getExtratoAluno() {
  const token = localStorage.getItem("token");
  const response = await axios.get("http://localhost:3000/alunos/me/extrato", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return response.data; // { saldo, transacoes }
}

// Buscar todas as vantagens disponíveis
export async function listarVantagensDisponiveis() {
  const token = localStorage.getItem("token");
  const response = await axios.get("http://localhost:3000/vantagens", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data; 
}

// Realizar resgate de uma vantagem
export async function resgatarVantagem(vantagemId: string) {
  const token = localStorage.getItem("token");
  const response = await axios.post(
    `http://localhost:3000/alunos/me/resgatar/${vantagemId}`,
    {},
    {
      headers: { Authorization: `Bearer ${token}` },
    }
  );
  return response.data; 
}
