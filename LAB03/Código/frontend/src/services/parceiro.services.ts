import axios from "axios";

export async function cadastrarParceiro(data: {
  nome: string;
  email: string;
  senha: string;
  cnpj?: string;
}) {
  return await axios.post("http://localhost:3000/auth/register", {
    ...data,
    tipoUsuario: "PARCEIRO",
  });
}

// Busca as informações da empresa
export async function getParceiroInfo() {
  const token = localStorage.getItem("token");

  const response = await axios.get("http://localhost:3000/parceiros/me", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  return response.data; 
}  