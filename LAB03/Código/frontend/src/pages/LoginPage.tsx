import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { authService } from "../services/authService";

import Container from "../components/Container/Container";
import Form from "../components/Form/Form";
import Label from "../components/Label/Label";
import Input from "../components/Input/Input";
import Button from "../components/Button/Button";
import Toast from "../components/Toast/Toast";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const tipo = await authService.login(email, senha);
      if (tipo === "aluno") navigate("/aluno");
      else if (tipo === "professor") navigate("/professor");
      else if (tipo === "parceiro") navigate("/parceiro");
    } catch (err) {
      setErro("Email ou senha inválidos.");
    }
  };

  return (
    <Container>
      <h1>Login</h1>

      <Form onSubmit={handleLogin}>
        <div>
          <Label htmlFor="email">E-mail</Label>
          <Input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div>
          <Label htmlFor="senha">Senha</Label>
          <Input
            id="senha"
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            required
          />
        </div>

        <Button type="submit">Entrar</Button>

        {erro && <Toast type="error" message={erro} />}
      </Form>

      <div style={{ marginTop: "24px" }}>
        <p>Não tem conta?</p>
        <Button type="button" onClick={() => navigate("/registro/aluno")}>
          Quero me registrar como aluno
        </Button>
        <Button type="button" onClick={() => navigate("/registro/parceiro")}>
          Quero ser um parceiro
        </Button>
      </div>
    </Container>
  );
}

export default LoginPage;
