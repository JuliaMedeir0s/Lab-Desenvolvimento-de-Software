import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Input from "./ui/Input";
import Button from "./ui/Button";
import { toast } from "sonner";
import { cadastrarAluno } from "../services/aluno.service";
import { buscarInstituicoes, Instituicao } from "../services/instituicao.service";

const AlunoForm: React.FC = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    cpf: "",
    rg: "",
    instituicao: "",
    rua: "",
    numero: "",
    bairro: "",
    cidade: "",
    uf: "",
    cep: "",
  });

  const [errors, setErrors] = useState<Record<string, string>>({});

  const [instituicoes, setInstituicoes] = useState<Instituicao[]>([]);

  useEffect(() => {
    async function carregarInstituicoes() {
      try {
        const data = await buscarInstituicoes();
        setInstituicoes(data);
      } catch (error) {
        console.error("Erro ao buscar instituições", error);
      }
    }

    carregarInstituicoes();
  }, []);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));

    if (errors[name]) {
      setErrors((prev) => ({ ...prev, [name]: "" }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Record<string, string> = {};

    const requiredFields = [
      "nome",
      "email",
      "senha",
      "cpf",
      "rg",
      "instituicao",
      "rua",
      "numero",
      "bairro",
      "cidade",
      "uf",
      "cep",
    ];

    requiredFields.forEach((field) => {
      if (!formData[field as keyof typeof formData]) {
        newErrors[field] = "Campo obrigatório";
      }
    });

    if (formData.email && !/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "E-mail inválido";
    }

    if (formData.senha && formData.senha.length < 8) {
      newErrors.senha = "A senha deve ter no mínimo 8 caracteres";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!validateForm()) return;

    try {
      const instituicaoId = Number(formData.instituicao);
      const instituicaoSelecionada = instituicoes.find(
        (i) => i.id === instituicaoId
      );
      
      if (!instituicaoSelecionada) {
        toast.error("Instituição inválida.");
        return;
      }

      await cadastrarAluno({
        nome: formData.nome,
        email: formData.email,
        senha: formData.senha,
        tipoUsuario: "ALUNO",
        cpf: formData.cpf,
        rg: formData.rg,
        instituicaoId: instituicaoSelecionada.id,
        endereco: {
          rua: formData.rua,
          numero: formData.numero,
          bairro: formData.bairro,
          cidade: formData.cidade,
          uf: formData.uf,
          cep: formData.cep,
        },
      });

      toast.success("Cadastro realizado com sucesso!");
      navigate("/"); // Volta para login
    } catch (error: any) {
      toast.error(
        error.response?.data?.error ||
          "Erro ao realizar o cadastro. Verifique os dados e tente novamente."
      );
    }
  };

  const handleBackToLogin = () => {
    navigate("/");
  };

  return (
    <form onSubmit={handleSubmit} className="w-full space-y-6">
      <div className="space-y-4">
        <Input
          type="text"
          name="nome"
          label="Nome"
          placeholder="Digite seu nome completo"
          value={formData.nome}
          onChange={handleChange}
          error={errors.nome}
        />
        <Input
          type="email"
          name="email"
          label="E-mail"
          placeholder="seu@email.com"
          value={formData.email}
          onChange={handleChange}
          error={errors.email}
        />
        <Input
          type="password"
          name="senha"
          label="Senha"
          placeholder="••••••••"
          value={formData.senha}
          onChange={handleChange}
          error={errors.senha}
        />
        <div className="flex gap-4">
          <Input
            type="text"
            name="cpf"
            label="CPF"
            placeholder="000.000.000-00"
            value={formData.cpf}
            onChange={handleChange}
            error={errors.cpf}
          />
          <Input
            type="text"
            name="rg"
            label="RG"
            placeholder="00.000.000-0"
            value={formData.rg}
            onChange={handleChange}
            error={errors.rg}
          />
        </div>
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Instituição
          </label>
          <select
            name="instituicao"
            value={formData.instituicao}
            onChange={handleChange}
            className="w-full px-3 py-2 bg-white border border-gray-300 rounded-md"
          >
            <option value="">Selecione uma instituição</option>
            {instituicoes.map((inst) => (
              <option key={inst.id} value={inst.id}>
                {inst.nome}
              </option>
            ))}
          </select>

          {errors.instituicao && (
            <p className="mt-1 text-sm text-red-600">{errors.instituicao}</p>
          )}
        </div>
        <div className="flex gap-4">
          <Input
            type="text"
            name="rua"
            label="Rua"
            placeholder="Nome da rua"
            value={formData.rua}
            onChange={handleChange}
            error={errors.rua}
          />
          <Input
            type="text"
            name="bairro"
            label="Bairro"
            placeholder="Centro"
            value={formData.bairro}
            onChange={handleChange}
            error={errors.bairro}
          />
          <Input
            type="text"
            name="numero"
            label="Número"
            placeholder="123"
            value={formData.numero}
            onChange={handleChange}
            error={errors.numero}
          />
        </div>
        <div className="flex gap-4">
          <Input
            type="text"
            name="cidade"
            label="Cidade"
            placeholder="Nome da cidade"
            value={formData.cidade}
            onChange={handleChange}
            error={errors.cidade}
          />
          <Input
            type="text"
            name="uf"
            label="UF"
            placeholder="MG"
            value={formData.uf}
            onChange={handleChange}
            error={errors.uf}
            maxLength={2}
          />
          <Input
            type="text"
            name="cep"
            label="CEP"
            placeholder="00000-000"
            value={formData.cep}
            onChange={handleChange}
            error={errors.cep}
          />
        </div>
      </div>

      <div className="space-y-4 pt-2">
        <Button type="submit" fullWidth>
          Cadastrar
        </Button>
        <button
          type="button"
          onClick={handleBackToLogin}
          className="w-full text-center text-sm text-indigo-600 hover:text-indigo-700 hover:underline focus:outline-none"
        >
          Voltar para login
        </button>
      </div>
    </form>
  );
};

export default AlunoForm;