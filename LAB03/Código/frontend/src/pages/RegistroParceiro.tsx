import { useState } from "react";
import { useNavigate } from "react-router-dom";

import Container from "../components/Container/Container";
import Form from "../components/Form/Form";
import Label from "../components/Label/Label";
import Input from "../components/Input/Input";
import Button from "../components/Button/Button";
import Toast from "../components/Toast/Toast";

function RegistroParceiro() {
  const navigate = useNavigate();

  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [cnpj, setCnpj] = useState("");

  const [erro, setErro] = useState("");
  const [sucesso, setSucesso] = useState("");

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const dados = { nome, email, senha, cnpj };

      // parceiroService.registrar(dados)
      console.log("Dados enviados:", dados);

      setSucesso("Cadastro realizado com sucesso!");
      setErro("");
      setTimeout(() => navigate("/login"), 2000);
    } catch (err) {
      setErro("Erro ao cadastrar parceiro.");
    }
  };

  return (
    <Container>
      <h1>Cadastro de Empresa Parceira</h1>

      <Form onSubmit={handleSubmit}>
        <Label htmlFor="nome">Nome</Label>
        <Input
          id="nome"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          required
        />

        <Label htmlFor="email">E-mail</Label>
        <Input
          id="email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <Label htmlFor="senha">Senha</Label>
        <Input
          id="senha"
          type="password"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          required
        />

        <Label htmlFor="cnpj">CNPJ</Label>
        <Input
          id="cnpj"
          value={cnpj}
          onChange={(e) => setCnpj(e.target.value)}
        />

        <Button type="submit">Cadastrar</Button>

        {erro && <Toast type="error" message={erro} />}
        {sucesso && <Toast type="success" message={sucesso} />}
      </Form>
    </Container>
  );
}

export default RegistroParceiro;
