import React, { useState } from "react";
import { ArrowLeft, LogOut } from "lucide-react";
import TransactionTable from "../components/TransactionTable";
import EmptyState from "../components/EmptyState";
import { Transaction } from "../types";
import { mockTransactions } from "../data/mockTransactions";

const ExtratoProfessorPage: React.FC = () => {
  const [transactions, setTransactions] =
    useState<Transaction[]>(mockTransactions);

  // In a real application, you would fetch transactions from an API
  // useEffect(() => {
  //   const fetchTransactions = async () => {
  //     try {
  //       const response = await fetch('/api/transactions');
  //       const data = await response.json();
  //       setTransactions(data);
  //     } catch (error) {
  //       console.error('Error fetching transactions:', error);
  //       
  //       // toast.error('Erro ao carregar transações');
  //     }
  //   };
  //
  //   fetchTransactions();
  // }, []);

  const handleBack = () => {
    // navigate('/dashboard/professor');
    console.log("Navigating back to professor dashboard");
  };

  const handleLogout = () => {
    // logout();
    // navigate('/login');
    console.log("Logging out");
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
