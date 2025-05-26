import React, { useState } from "react";
import Input from "./ui/Input";
import Button from "./ui/Button";
import { StudentRegistrationData } from "../types";

const AlunoForm: React.FC = () => {
  const [formData, setFormData] = useState<StudentRegistrationData>({
    nome: "",
    email: "",
    senha: "",
    cpf: "",
    rg: "",
    instituicao: "",
    rua: "",
    numero: "",
    cidade: "",
    uf: "",
    cep: "",
  });

  const [errors, setErrors] = useState<Partial<StudentRegistrationData>>({});

  const instituicoes = ["PUC Minas", "UFMG", "CEFET-MG", "UNA", "FUMEC"];

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (errors[name as keyof StudentRegistrationData]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Partial<StudentRegistrationData> = {};

    if (!formData.nome) newErrors.nome = "Nome é obrigatório";
    if (!formData.email) {
      newErrors.email = "E-mail é obrigatório";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "E-mail inválido";
    }
    if (!formData.senha) {
      newErrors.senha = "Senha é obrigatória";
    } else if (formData.senha.length < 8) {
      newErrors.senha = "A senha deve ter pelo menos 8 caracteres";
    }
    if (!formData.cpf) newErrors.cpf = "CPF é obrigatório";
    if (!formData.rg) newErrors.rg = "RG é obrigatório";
    if (!formData.instituicao)
      newErrors.instituicao = "Instituição é obrigatória";
    if (!formData.rua) newErrors.rua = "Rua é obrigatória";
    if (!formData.numero) newErrors.numero = "Número é obrigatório";
    if (!formData.cidade) newErrors.cidade = "Cidade é obrigatória";
    if (!formData.uf) newErrors.uf = "UF é obrigatória";
    if (!formData.cep) newErrors.cep = "CEP é obrigatório";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (validateForm()) {
      console.log("Form submitted:", formData);
    }
  };

  const handleBackToLogin = () => {
    console.log("Voltar para login");
    // Navigate back to login
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
          required
        />

        <Input
          type="email"
          name="email"
          label="E-mail"
          placeholder="seu@email.com"
          value={formData.email}
          onChange={handleChange}
          error={errors.email}
          required
        />

        <Input
          type="password"
          name="senha"
          label="Senha"
          placeholder="••••••••"
          value={formData.senha}
          onChange={handleChange}
          error={errors.senha}
          required
        />

        <div className="flex gap-4">
          <div className="flex-1">
            <Input
              type="text"
              name="cpf"
              label="CPF"
              placeholder="000.000.000-00"
              value={formData.cpf}
              onChange={handleChange}
              error={errors.cpf}
              required
            />
          </div>
          <div className="flex-1">
            <Input
              type="text"
              name="rg"
              label="RG"
              placeholder="00.000.000-0"
              value={formData.rg}
              onChange={handleChange}
              error={errors.rg}
              required
            />
          </div>
        </div>

        <div className="w-full">
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Instituição
          </label>
          <select
            name="instituicao"
            value={formData.instituicao}
            onChange={handleChange}
            className="w-full px-3 py-2 bg-white border border-gray-300 rounded-md text-sm shadow-sm placeholder-slate-400 focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500"
            required
          >
            <option value="">Selecione uma instituição</option>
            {instituicoes.map((inst) => (
              <option key={inst} value={inst}>
                {inst}
              </option>
            ))}
          </select>
          {errors.instituicao && (
            <p className="mt-1 text-sm text-red-600">{errors.instituicao}</p>
          )}
        </div>

        <div className="flex gap-4">
          <div className="flex-[3]">
            <Input
              type="text"
              name="rua"
              label="Rua"
              placeholder="Nome da rua"
              value={formData.rua}
              onChange={handleChange}
              error={errors.rua}
              required
            />
          </div>
          <div className="flex-1">
            <Input
              type="text"
              name="numero"
              label="Número"
              placeholder="123"
              value={formData.numero}
              onChange={handleChange}
              error={errors.numero}
              required
            />
          </div>
        </div>

        <div className="flex gap-4">
          <div className="flex-[2]">
            <Input
              type="text"
              name="cidade"
              label="Cidade"
              placeholder="Nome da cidade"
              value={formData.cidade}
              onChange={handleChange}
              error={errors.cidade}
              required
            />
          </div>
          <div className="flex-1">
            <Input
              type="text"
              name="uf"
              label="UF"
              placeholder="MG"
              value={formData.uf}
              onChange={handleChange}
              error={errors.uf}
              maxLength={2}
              required
            />
          </div>
          <div className="flex-[1.5]">
            <Input
              type="text"
              name="cep"
              label="CEP"
              placeholder="00000-000"
              value={formData.cep}
              onChange={handleChange}
              error={errors.cep}
              required
            />
          </div>
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
