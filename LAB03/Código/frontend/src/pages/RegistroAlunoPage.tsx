import React from "react";
import AlunoForm from "../components/AlunoForm";
import { Coins } from "lucide-react";

const RegistroAlunoPage: React.FC = () => {
  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4">
      <div className="bg-white rounded-xl shadow-lg p-8 w-full max-w-2xl">
        <div className="text-center mb-8">
          <div className="flex justify-center mb-4">
            <Coins size={48} className="text-indigo-600" />
          </div>
          <h1 className="text-2xl font-bold text-gray-800">
            Cadastro de Aluno
          </h1>
          <p className="text-gray-600 mt-2">
            Preencha seus dados para criar sua conta
          </p>
        </div>

        <AlunoForm />

        <div className="mt-6 text-center text-sm text-gray-600">
          <p>© {new Date().getFullYear()} Sistema de Moeda Estudantil</p>
        </div>
      </div>
    </div>
  );
};

export default RegistroAlunoPage;
