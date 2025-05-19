import { useNavigate } from "react-router-dom";
import Container from "../components/Container/Container";
import Button from "../components/Button/Button";

function HomeAluno() {
  const navigate = useNavigate();
  const nome = localStorage.getItem("nome") || "Aluno";

  return (
    <Container>
      <h1>Bem-vindo, {nome}!</h1>
      <p>O que você deseja fazer?</p>

      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "12px",
          marginTop: "24px",
        }}
      >
        <Button onClick={() => navigate("/aluno/extrato")}>
          Ver extrato de moedas
        </Button>
        <Button onClick={() => navigate("/aluno/resgatar")}>
          Resgatar uma vantagem
        </Button>
      </div>
    </Container>
  );
}

export default HomeAluno;
