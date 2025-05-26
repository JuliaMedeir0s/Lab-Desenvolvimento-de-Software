import React, { useState } from "react";
import Input from "./ui/Input";
import Button from "./ui/Button";
import { LoginFormData } from "../types";

const LoginForm: React.FC = () => {
  const [formData, setFormData] = useState<LoginFormData>({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState<Partial<LoginFormData>>({});

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    // Clear error when user types
    if (errors[name as keyof LoginFormData]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Partial<LoginFormData> = {};

    if (!formData.email) {
      newErrors.email = "E-mail é obrigatório";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "E-mail inválido";
    }

    if (!formData.password) {
      newErrors.password = "Senha é obrigatória";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (validateForm()) {
      // Here you would handle the login logic
      console.log("Form submitted:", formData);
    }
  };

  const handleRegisterAsStudent = () => {
    console.log("Registrar como aluno");
    // Navigate to student registration
  };

  const handleRegisterAsPartner = () => {
    console.log("Registrar como parceiro");
    // Navigate to partner registration
  };

  const handleForgotPassword = () => {
    console.log("Esqueci minha senha");
    // Navigate to password recovery
  };

  return (
    <form onSubmit={handleSubmit} className="w-full space-y-6">
      <div className="space-y-4">
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

        <div className="space-y-1">
          <Input
            type="password"
            name="password"
            label="Senha"
            placeholder="••••••••"
            value={formData.password}
            onChange={handleChange}
            error={errors.password}
            required
          />
          <div className="text-right">
            <button
              type="button"
              onClick={handleForgotPassword}
              className="text-sm text-indigo-600 hover:text-indigo-700 hover:underline focus:outline-none"
            >
              Esqueci minha senha
            </button>
          </div>
        </div>
      </div>

      <div className="space-y-3 pt-2">
        <Button type="submit" fullWidth>
          Entrar
        </Button>

        <Button
          type="button"
          variant="secondary"
          fullWidth
          onClick={handleRegisterAsStudent}
        >
          Quero me registrar como aluno
        </Button>

        <Button
          type="button"
          variant="outline"
          fullWidth
          onClick={handleRegisterAsPartner}
        >
          Quero ser um parceiro
        </Button>
      </div>
    </form>
  );
};

export default LoginForm;
