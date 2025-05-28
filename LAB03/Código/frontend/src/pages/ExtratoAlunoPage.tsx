import React, { useEffect, useState } from "react";
import { ArrowLeft, LogOut } from "lucide-react";
import { toast } from "sonner";
import { getExtratoAluno } from "../services/aluno.service";
import { useAuth } from "../contexts/AuthContext";

interface Transaction {
  id: string;
  data: string;
  tipo: "ENVIO" | "RESGATE";
  quantidade: number;
  descricao: string;
}

function ExtratoAlunoPage() {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const { logout } = useAuth();

  useEffect(() => {
    async function fetchExtrato() {
      try {
        const data = await getExtratoAluno();
        setTransactions(data.transacoes);
      } catch (err) {
        toast.error("Erro ao carregar extrato.");
      }
    }

    fetchExtrato();
  }, []);

  const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    return new Intl.DateTimeFormat("pt-BR").format(date);
  };

  const handleLogout = () => {
    logout();
  };

  return (
    <div className="min-h-screen bg-gray-100">
      {/* Header */}
      <header className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
          <button
            onClick={() => window.history.back()}
            className="flex items-center text-gray-600 hover:text-gray-900"
          >
            <ArrowLeft className="w-5 h-5 mr-2" />
            Voltar
          </button>
          <button
            onClick={handleLogout}
            className="flex items-center text-red-600 hover:text-red-700"
          >
            <LogOut className="w-5 h-5 mr-2" />
            Sair
          </button>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 py-8">
        <div className="bg-white rounded-xl shadow-lg p-6">
          <h1 className="text-2xl font-bold text-gray-900 mb-6">
            Extrato de Moedas
          </h1>

          {transactions.length > 0 ? (
            <div className="overflow-x-auto">
              <table className="min-w-full divide-y divide-gray-200">
                <thead>
                  <tr>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                      Data
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                      Tipo
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                      Valor
                    </th>
                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                      Descrição
                    </th>
                  </tr>
                </thead>
                <tbody className="bg-white divide-y divide-gray-200">
                  {transactions.map((t) => (
                    <tr key={t.id}>
                      <td className="px-6 py-4 text-sm text-gray-900">
                        {formatDate(t.data)}
                      </td>
                      <td className="px-6 py-4 text-sm text-gray-900">
                        {t.tipo === "ENVIO" ? "Envio" : "Resgate"}
                      </td>
                      <td
                        className={`px-6 py-4 text-sm font-medium ${
                          t.quantidade > 0 ? "text-green-600" : "text-red-600"
                        }`}
                      >
                        {t.quantidade > 0 ? "+" : ""}
                        {t.quantidade} moedas
                      </td>
                      <td className="px-6 py-4 text-sm text-gray-900">
                        {t.descricao}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ) : (
            <div className="text-center py-12">
              <p className="text-gray-500 text-lg">
                Nenhuma transação encontrada
              </p>
            </div>
          )}
        </div>
      </main>
    </div>
  );
}

export default ExtratoAlunoPage;