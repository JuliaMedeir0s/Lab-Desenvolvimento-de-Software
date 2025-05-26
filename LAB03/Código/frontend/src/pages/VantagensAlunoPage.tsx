import React from "react";
import { ArrowLeft, LogOut, Gift } from "lucide-react";
import { toast } from "../components/ui/toast";

interface Reward {
  id: string;
  name: string;
  description: string;
  cost: number;
  imageUrl?: string;
}

const mockRewards: Reward[] = [
  {
    id: "1",
    name: "Vale Presente Livraria",
    description:
      "Vale presente no valor de R$ 50 para usar na Livraria Cultura",
    cost: 50,
    imageUrl:
      "https://images.pexels.com/photos/2041540/pexels-photo-2041540.jpeg",
  },
  {
    id: "2",
    name: "Curso Online",
    description: "Acesso a um curso online de sua escolha na plataforma Udemy",
    cost: 75,
    imageUrl:
      "https://images.pexels.com/photos/5905709/pexels-photo-5905709.jpeg",
  },
  {
    id: "3",
    name: "Desconto em Restaurante",
    description: "Cupom de 30% de desconto no restaurante parceiro",
    cost: 30,
    imageUrl: "https://images.pexels.com/photos/67468/pexels-photo-67468.jpeg",
  },
];

export function VantagensAlunoPage() {
  const handleLogout = () => {
    // Implementar lógica de logout
    toast.info("Saindo do sistema...");
  };

  const handleRedeem = (reward: Reward) => {
    // Implementar lógica de resgate
    toast.success(
      `Solicitação de resgate para "${reward.name}" realizada com sucesso!`
    );
  };

  return (
    <div className="min-h-screen bg-gray-100">
      {/* Header */}
      <header className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
          <div className="flex justify-between items-center">
            <button
              onClick={() => window.history.back()}
              className="flex items-center text-gray-600 hover:text-gray-900 transition-colors"
            >
              <ArrowLeft className="w-5 h-5 mr-2" />
              Voltar
            </button>
            <button
              onClick={handleLogout}
              className="flex items-center text-red-600 hover:text-red-700 transition-colors"
            >
              <LogOut className="w-5 h-5 mr-2" />
              Sair
            </button>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="bg-white rounded-xl shadow-lg p-6">
          <div className="flex items-center gap-3 mb-8">
            <Gift className="w-8 h-8 text-indigo-600" />
            <h1 className="text-2xl font-bold text-gray-900">
              Resgatar uma vantagem
            </h1>
          </div>

          {mockRewards.length > 0 ? (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {mockRewards.map((reward) => (
                <div
                  key={reward.id}
                  className="bg-white border border-gray-200 rounded-lg overflow-hidden shadow-md transition-transform hover:scale-[1.02]"
                >
                  {reward.imageUrl && (
                    <img
                      src={reward.imageUrl}
                      alt={reward.name}
                      className="w-full h-48 object-cover"
                    />
                  )}
                  <div className="p-5">
                    <h3 className="text-xl font-semibold text-gray-900 mb-2">
                      {reward.name}
                    </h3>
                    <p className="text-gray-600 mb-4">{reward.description}</p>
                    <div className="flex items-center justify-between mb-4">
                      <span className="text-lg font-medium text-indigo-600">
                        Custa: {reward.cost} moedas
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

export default VantagensAlunoPage;
