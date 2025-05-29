import React, { useEffect, useState } from "react";
import { LogOut, SendHorizonal, FileText } from "lucide-react";
import { useNavigate } from "react-router-dom";
import { toast } from "sonner";
import {
  getProfessorInfo,
  getExtratoProfessor,
} from "../services/professor.service";

const HomeProfessorPage: React.FC = () => {
  const navigate = useNavigate();
  const [professorName, setProfessorName] = useState("");
  const [coinBalance, setCoinBalance] = useState(0);

  useEffect(() => {
    const rawToken = localStorage.getItem("token");

    if (!rawToken) {
      toast.error("Token não encontrado. Faça login novamente.");
      navigate("/login");
      return;
    }

    const token: string = rawToken;

    async function fetchData() {
      try {
        const prof = await getProfessorInfo(token);
        setProfessorName(prof.usuario.nome);

        const extrato = await getExtratoProfessor(token);
        setCoinBalance(extrato.saldo ?? 0);
      } catch (err) {
        toast.error("Erro ao carregar dados do professor.");
        console.error(err);
      }
    }

    fetchData();
  }, [navigate]);   

  const handleLogout = () => {
    localStorage.removeItem("token");
    toast.info("Saindo do sistema...");
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-4 md:p-8">
      <div className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8 flex justify-between items-center">
          <h1 className="text-2xl md:text-3xl font-bold text-gray-800">
            Bem-vindo, Professor{" "}
            <span className="text-indigo-600">{professorName}</span>!
          </h1>
          <button
            onClick={handleLogout}
            className="flex items-center gap-2 border border-gray-300 text-gray-700 hover:bg-gray-100 px-4 py-2 rounded-lg transition-colors duration-200"
          >
            <LogOut size={20} />
            <span className="hidden sm:inline">Sair</span>
          </button>
        </div>

        <div className="bg-white rounded-xl shadow-lg p-6 mb-8">
          <div className="text-center">
            <h2 className="text-xl text-gray-600 mb-2">Seu saldo</h2>
            <p className="text-4xl font-bold text-indigo-600">
              {coinBalance} moedas
            </p>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <button
            onClick={() => navigate("/enviar-moedas")}
            className="bg-white rounded-xl shadow-lg p-8 hover:shadow-xl transition-shadow duration-200 text-center group"
          >
            <div className="flex flex-col items-center gap-4">
              <div className="p-4 bg-indigo-100 rounded-full group-hover:bg-indigo-200 transition-colors duration-200">
                <SendHorizonal size={32} className="text-indigo-600" />
              </div>
              <div>
                <h3 className="text-xl font-semibold text-gray-800 mb-2">
                  Enviar Moedas para Alunos
                </h3>
                <p className="text-gray-600">
                  Distribua moedas para reconhecer o desempenho dos alunos
                </p>
              </div>
            </div>
          </button>

          <button
            onClick={() => navigate("/extrato-professor")}
            className="bg-white rounded-xl shadow-lg p-8 hover:shadow-xl transition-shadow duration-200 text-center group"
          >
            <div className="flex flex-col items-center gap-4">
              <div className="p-4 bg-indigo-100 rounded-full group-hover:bg-indigo-200 transition-colors duration-200">
                <FileText size={32} className="text-indigo-600" />
              </div>
              <div>
                <h3 className="text-xl font-semibold text-gray-800 mb-2">
                  Ver Extrato de Moedas
                </h3>
                <p className="text-gray-600">
                  Acompanhe seu histórico de distribuição de moedas
                </p>
              </div>
            </div>
          </button>
        </div>
      </div>
    </div>
  );
};

export default HomeProfessorPage;