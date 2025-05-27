import axios from "axios";

const API_URL = "http://localhost:3000"; // 👉 Trocar pela URL da sua API

interface LoginResponse {
  message: string;
  token: string;
}

export async function login(email: string, senha: string) {
  const response = await axios.post<LoginResponse>(`${API_URL}/login`, {
    email,
    senha,
  });

  return response.data;
}