import axios from "axios";

const API_URL = "http://localhost:3000/auth"; 

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