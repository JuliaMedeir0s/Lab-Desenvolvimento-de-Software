import React, { useState } from "react";
import Input from "./ui/Input";
import Button from "./ui/Button";

interface PasswordRecoveryFormData {
  email: string;
}

const RecuperarSenhaForm: React.FC = () => {
  const [formData, setFormData] = useState<PasswordRecoveryFormData>({
    email: "",
  });

  const [errors, setErrors] = useState<Partial<PasswordRecoveryFormData>>({});

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (errors[name as keyof PasswordRecoveryFormData]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Partial<PasswordRecoveryFormData> = {};

    if (!formData.email) {
      newErrors.email = "E-mail é obrigatório";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "E-mail inválido";
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
          type="email"
          name="email"
          label="E-mail"
          placeholder="seu@email.com"
          value={formData.email}
          onChange={handleChange}
          error={errors.email}
          required
        />
      </div>

      <div className="space-y-4">
        <Button type="submit" fullWidth>
          Enviar instruções
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

export default RecuperarSenhaForm;
