import axios from "axios";

export async function cadastrarVantagem(data: {
  nome: string;
  descricao: string;
  custo: number;
  imagem: string;
}) {
  const token = localStorage.getItem("token");

  const response = await axios.post(
    "http://localhost:3000/vantagens/me",
    data,
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );

  return response.data;
}
