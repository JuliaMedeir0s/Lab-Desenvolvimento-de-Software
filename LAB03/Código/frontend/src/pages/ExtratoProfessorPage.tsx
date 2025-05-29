import React, { useState, useEffect } from "react";
import { ArrowLeft, LogOut } from "lucide-react";
import TransactionTable from "../components/TransactionTable";
import EmptyState from "../components/EmptyState";
import { Transaction } from "../types";
import { useNavigate } from "react-router-dom";
import { getExtratoProfessor } from "../services/professor.service";
import { toast } from "sonner";

const ExtratoProfessorPage: React.FC = () => {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      toast.error("Token não encontrado");
      navigate("/login");
      return;
    }

    const fetchExtrato = async () => {
      try {
        const data = await getExtratoProfessor(token);
        const transacoesFormatadas: Transaction[] = data.transacoes.map(
          (t: any) => ({
            id: t.id,
            date: t.data,
            studentName: t.aluno?.usuario?.nome ?? "-",
            amount: t.valor,
            reason: t.mensagem,
          })
        );

        setTransactions(transacoesFormatadas);
      } catch (error) {
        console.error(error);
        toast.error("Erro ao carregar transações.");
      }
    };

    fetchExtrato();
  }, []);

  const handleBack = () => {
    navigate("/dashboard-professor");
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-4 md:p-6">
      <div className="max-w-6xl mx-auto">
        {/* Header */}
        <div className="flex justify-between items-center mb-8">
          <button
            onClick={handleBack}
            className="flex items-center text-gray-700 hover:text-blue-600 transition-colors"
          >
            <ArrowLeft className="w-5 h-5 mr-2" />
            <span className="font-medium">Voltar</span>
          </button>

          <button
            onClick={handleLogout}
            className="flex items-center text-gray-700 hover:text-red-600 transition-colors"
          >
            <span className="font-medium mr-2">Sair</span>
            <LogOut className="w-5 h-5" />
          </button>
        </div>

        {/* Main Content */}
        <div className="bg-white rounded-xl shadow-lg p-6 md:p-8">
          <h1 className="text-2xl md:text-3xl font-bold text-gray-800 mb-6">
            Extrato de Moedas
          </h1>

          {transactions.length > 0 ? (
            <TransactionTable transactions={transactions} />
          ) : (
            <EmptyState message="Nenhuma transação encontrada." />
          )}
        </div>
      </div>
    </div>
  );
};

export default ExtratoProfessorPage;