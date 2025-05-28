import React, { useEffect, useState } from "react";
import { ArrowLeft, LogOut, Gift } from "lucide-react";
import { toast } from "sonner";
import { useAuth } from "../contexts/AuthContext";
import {
  listarVantagensDisponiveis,
  resgatarVantagem,
} from "../services/aluno.service";

interface Reward {
  id: string;
  titulo: string;
  descricao: string;
  preco: number;
  imagemUrl?: string;
}

export function ResgateVantagemPage() {
  const [rewards, setRewards] = useState<Reward[]>([]);
  const { logout } = useAuth();

  useEffect(() => {
    async function fetchRewards() {
      try {
        const data = await listarVantagensDisponiveis();
        setRewards(data);
      } catch {
        toast.error("Erro ao carregar as vantagens.");
      }
    }

    fetchRewards();
  }, []);

  const handleLogout = () => {
    logout();
  };

  const handleRedeem = async (reward: Reward) => {
    try {
      const res = await resgatarVantagem(reward.id);
      toast.success(
        `Resgate de "${reward.titulo}" concluído! Código: ${res.codigo}`
      );
    } catch (error: any) {
      toast.error(
        error.response?.data?.error ||
          `Erro ao resgatar "${reward.titulo}". Verifique seu saldo.`
      );
    }
  };

  return (
    <div className="min-h-screen bg-gray-100">
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

      <main className="max-w-7xl mx-auto px-4 py-8">
        <div className="bg-white rounded-xl shadow-lg p-6">
          <div className="flex items-center gap-3 mb-8">
            <Gift className="w-8 h-8 text-indigo-600" />
            <h1 className="text-2xl font-bold text-gray-900">
              Resgatar uma vantagem
            </h1>
          </div>

          {rewards.length > 0 ? (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {rewards.map((reward) => (
                <div
                  key={reward.id}
                  className="bg-white border border-gray-200 rounded-lg overflow-hidden shadow-md transition-transform hover:scale-[1.02]"
                >
                  {reward.imagemUrl && (
                    <img
                      src={reward.imagemUrl}
                      alt={reward.titulo}
                      className="w-full h-48 object-cover"
                    />
                  )}
                  <div className="p-5">
                    <h3 className="text-xl font-semibold text-gray-900 mb-2">
                      {reward.titulo}
                    </h3>
                    <p className="text-gray-600 mb-4">{reward.descricao}</p>
                    <div className="flex items-center justify-between mb-4">
                      <span className="text-lg font-medium text-indigo-600">
                        Custa: {reward.preco} moedas
                      </span>
                    </div>
                    <button
                      onClick={() => handleRedeem(reward)}
                      className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 transition-colors flex items-center justify-center gap-2"
                    >
                      <Gift className="w-5 h-5" />
                      Resgatar
                    </button>
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <div className="text-center py-12">
              <Gift className="w-16 h-16 text-gray-400 mx-auto mb-4" />
              <p className="text-gray-500 text-lg">
                Nenhuma vantagem disponível no momento
              </p>
            </div>
          )}
        </div>
      </main>
    </div>
  );
}

export default ResgateVantagemPage;