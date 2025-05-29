import React, { useEffect, useState } from "react";
import { Coins, LogOut, Receipt, Gift } from "lucide-react";
import { getAlunoInfo } from "../services/aluno.service";
import { useNavigate } from "react-router-dom";
import { toast } from "sonner";

const DashboardAluno: React.FC = () => {
  const navigate = useNavigate();
  const [studentName, setStudentName] = useState("...");
  const [balance, setBalance] = useState(0);

  useEffect(() => {
    async function fetchAluno() {
      try {
        const data = await getAlunoInfo();
        setStudentName(data.nome);
        setBalance(data.saldo);
      } catch (error) {
        console.error("Erro ao buscar aluno", error);
      }
    }

    fetchAluno();
  }, []);
  const handleViewStatement = () => {
    navigate("/extrato-aluno");
  };

  const handleRedeemRewards = () => {
    navigate("/resgate-vantagem");
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    toast.success("Você saiu do sistema.");
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gray-100">
      {/* Header */}
      <header className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
          <div className="flex items-center">
            <Coins className="h-8 w-8 text-indigo-600" />
            <span className="ml-2 text-xl font-semibold text-gray-900">
              Sistema de Moeda Estudantil
            </span>
          </div>
          <button
            onClick={handleLogout}
            className="flex items-center text-gray-600 hover:text-gray-900"
          >
            <LogOut className="h-5 w-5 mr-1" />
            <span>Sair</span>
          </button>
        </div>
      </header>

      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="bg-white rounded-xl shadow-lg p-6 md:p-8">
          {/* Mensagem inicial */}
          <div className="text-center mb-8">
            <h1 className="text-2xl md:text-3xl font-bold text-gray-900">
              Bem-vindo, {studentName}!
            </h1>
          </div>

          <div className="bg-indigo-50 rounded-lg p-6 mb-8 text-center">
            <h2 className="text-lg text-indigo-900 mb-2">Seu saldo</h2>
            <div className="flex items-center justify-center gap-2">
              <Coins className="h-8 w-8 text-indigo-600" />
              <span className="text-3xl font-bold text-indigo-600">
                {balance}
              </span>
              <span className="text-xl text-indigo-600">moedas</span>
            </div>
          </div>

          {/* Ações */}
          <div className="grid md:grid-cols-2 gap-6">
            <button
              onClick={handleViewStatement}
              className="flex flex-col items-center p-6 bg-indigo-600 rounded-xl text-white hover:bg-indigo-700 transition-colors duration-200"
            >
              <Receipt className="h-12 w-12 mb-4" />
              <span className="text-lg font-semibold">
                Ver extrato de moedas
              </span>
              <span className="text-sm opacity-80 mt-1">
                Acompanhe suas transações
              </span>
            </button>

            <button
              onClick={handleRedeemRewards}
              className="flex flex-col items-center p-6 bg-indigo-600 rounded-xl text-white hover:bg-indigo-700 transition-colors duration-200"
            >
              <Gift className="h-12 w-12 mb-4" />
              <span className="text-lg font-semibold">
                Resgatar uma vantagem
              </span>
              <span className="text-sm opacity-80 mt-1">
                Troque suas moedas por benefícios
              </span>
            </button>
          </div>
        </div>
      </main>
    </div>
  );
};

export default DashboardAluno;
