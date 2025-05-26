import React, { useState } from "react";
import Input from "./ui/Input";
import Button from "./ui/Button";

interface PasswordResetFormData {
  password: string;
  confirmPassword: string;
}

const NovaSenhaForm: React.FC = () => {
  const [formData, setFormData] = useState<PasswordResetFormData>({
    password: "",
    confirmPassword: "",
  });

  const [errors, setErrors] = useState<Partial<PasswordResetFormData>>({});

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (errors[name as keyof PasswordResetFormData]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Partial<PasswordResetFormData> = {};

    if (!formData.password) {
      newErrors.password = "Nova senha é obrigatória";
    } else if (formData.password.length < 8) {
      newErrors.password = "A senha deve ter pelo menos 8 caracteres";
    }

    if (!formData.confirmPassword) {
      newErrors.confirmPassword = "Confirmação de senha é obrigatória";
    } else if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = "As senhas não coincidem";
    }

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
          type="password"
          name="password"
          label="Nova senha"
          placeholder="••••••••"
          value={formData.password}
          onChange={handleChange}
          error={errors.password}
          required
        />

        <Input
          type="password"
          name="confirmPassword"
          label="Confirmar nova senha"
          placeholder="••••••••"
          value={formData.confirmPassword}
          onChange={handleChange}
          error={errors.confirmPassword}
          required
        />
      </div>

      <div className="space-y-4">
        <Button type="submit" fullWidth>
          Redefinir senha
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

export default NovaSenhaForm;
