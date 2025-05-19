import { useState } from "react";
import { useNavigate } from "react-router-dom";

import Container from "../components/Container/Container";
import Form from "../components/Form/Form";
import Label from "../components/Label/Label";
import Input from "../components/Input/Input";
import Button from "../components/Button/Button";
import SelectInstituicao from "../components/SelectInstituicao/SelectInstituicao";
import Toast from "../components/Toast/Toast";

function RegistroAluno() {
  const navigate = useNavigate();

  // Campos
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [cpf, setCpf] = useState("");
  const [rg, setRg] = useState("");
  const [instituicaoId, setInstituicaoId] = useState(0);

  // Endereço
  const [rua, setRua] = useState("");
  const [numero, setNumero] = useState("");
  const [cidade, setCidade] = useState("");
  const [uf, setUf] = useState("");
  const [cep, setCep] = useState("");

  const [erro, setErro] = useState("");
  const [sucesso, setSucesso] = useState("");

  // Mock temporário de instituições
  const instituicoes = [
    { id: 1, nome: "PUC Minas" },
    { id: 2, nome: "UFMG" },
  ];

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const dados = {
        nome,
        email,
        senha,
        cpf,
        rg,
        instituicaoId,
        endereco: {
          rua,
          numero,
          cidade,
          uf,
          cep,
        },
      };

      // alunoService.registrar(dados)
      console.log("Dados enviados:", dados);

      setSucesso("Cadastro realizado com sucesso!");
      setErro("");
      setTimeout(() => navigate("/login"), 2000);
    } catch (err) {
      setErro("Erro ao cadastrar aluno.");
    }
  };

  return (
    <Container>
      <h1>Cadastro de Aluno</h1>

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

        <Label htmlFor="cpf">CPF</Label>
        <Input
          id="cpf"
          value={cpf}
          onChange={(e) => setCpf(e.target.value)}
          required
        />

        <Label htmlFor="rg">RG</Label>
        <Input
          id="rg"
          value={rg}
          onChange={(e) => setRg(e.target.value)}
          required
        />

        <Label htmlFor="instituicao">Instituição</Label>
        <SelectInstituicao
          value={instituicaoId}
          onChange={(e) => setInstituicaoId(Number(e.target.value))}
          opcoes={instituicoes}
        />

        <Label htmlFor="rua">Rua</Label>
        <Input
          id="rua"
          value={rua}
          onChange={(e) => setRua(e.target.value)}
          required
        />

        <Label htmlFor="numero">Número</Label>
        <Input
          id="numero"
          value={numero}
          onChange={(e) => setNumero(e.target.value)}
          required
        />

        <Label htmlFor="cidade">Cidade</Label>
        <Input
          id="cidade"
          value={cidade}
          onChange={(e) => setCidade(e.target.value)}
          required
        />

        <Label htmlFor="uf">UF</Label>
        <Input
          id="uf"
          value={uf}
          onChange={(e) => setUf(e.target.value)}
          required
        />

        <Label htmlFor="cep">CEP</Label>
        <Input
          id="cep"
          value={cep}
          onChange={(e) => setCep(e.target.value)}
          required
        />

        <Button type="submit">Cadastrar</Button>

        {erro && <Toast type="error" message={erro} />}
        {sucesso && <Toast type="success" message={sucesso} />}
      </Form>
    </Container>
  );
}

export default RegistroAluno;
