import React, { useState } from "react";
import Input from "./ui/Input";
import Button from "./ui/Button";
import { PartnerRegistrationData } from "../types";
import { useNavigate } from "react-router-dom";
import { cadastrarParceiro } from "../services/parceiro.services";
import { toast } from "sonner";

const ParceiroForm: React.FC = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState<PartnerRegistrationData>({
    nome: "",
    email: "",
    senha: "",
    cnpj: "",
  });

  const [errors, setErrors] = useState<Partial<PartnerRegistrationData>>({});

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (errors[name as keyof PartnerRegistrationData]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Partial<PartnerRegistrationData> = {};

    if (!formData.nome) {
      newErrors.nome = "Nome é obrigatório";
    }

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

    if (formData.cnpj && !/^\d{14}$/.test(formData.cnpj.replace(/\D/g, ""))) {
      newErrors.cnpj = "CNPJ inválido";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!validateForm()) return;

    try {
      const payload = {
        nome: formData.nome,
        email: formData.email,
        senha: formData.senha,
        cnpj: formData.cnpj || undefined,
      };

      await cadastrarParceiro(payload); 

      toast.success("Cadastro realizado com sucesso!");
      navigate("/"); 
    } catch (error: any) {
      toast.error(
        error.response?.data?.error ||
          "Erro ao cadastrar parceiro. Verifique os dados."
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
          label="Nome da empresa"
          placeholder="Digite o nome da empresa"
          value={formData.nome}
          onChange={handleChange}
          error={errors.nome}
          required
        />

        <Input
          type="email"
          name="email"
          label="E-mail"
          placeholder="empresa@email.com"
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

        <Input
          type="text"
          name="cnpj"
          label="CNPJ (opcional)"
          placeholder="00.000.000/0000-00"
          value={formData.cnpj}
          onChange={handleChange}
          error={errors.cnpj}
        />
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

export default ParceiroForm;
