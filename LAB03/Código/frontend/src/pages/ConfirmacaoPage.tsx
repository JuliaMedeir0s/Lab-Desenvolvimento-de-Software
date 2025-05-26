import React from "react";
import { ArrowLeft, LogOut, Gift } from "lucide-react";
import { toast } from "../components/ui/toast";
import { useNavigate } from "react-router-dom";

interface RewardConfirmation {
  id: string;
  name: string;
  description: string;
  cost: number;
}

// Exemplo de dados - substituir pela integração real
const mockReward: RewardConfirmation = {
  id: "1",
  name: "Vale Presente Livraria",
  description: "Vale presente no valor de R$ 50 para usar na Livraria Cultura",
  cost: 50,
};

export function ConfirmacaoPage() {
  const navigate = useNavigate();

  const handleLogout = () => {
    toast.info("Saindo do sistema...");
  };

  const handleConfirm = async () => {
    try {

      toast.success(
        "Resgate realizado com sucesso! Um cupom foi enviado para o seu e-mail."
      );
      navigate("/rewards"); 
    } catch (error) {
      toast.error("Saldo insuficiente para realizar o resgate.");
    }
  };

  const handleCancel = () => {
    navigate("/rewards");
  };

  return (
    <div className="min-h-screen bg-gray-100">
      <header className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
          <div className="flex justify-between items-center">
            <button
              onClick={handleCancel}
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

      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="max-w-2xl mx-auto bg-white rounded-xl shadow-lg p-6">
          <div className="flex items-center gap-3 mb-8">
            <Gift className="w-8 h-8 text-indigo-600" />
            <h1 className="text-2xl font-bold text-gray-900">
              Confirmar Resgate
            </h1>
          </div>

          <div className="bg-gray-50 rounded-lg p-6 mb-8">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">
              {mockReward.name}
            </h2>
            <p className="text-gray-600 mb-4">{mockReward.description}</p>
            <div className="flex items-center justify-between py-3 border-t border-gray-200">
              <span className="text-gray-600">Custo</span>
              <span className="text-lg font-medium text-indigo-600">
                {mockReward.cost} moedas
              </span>
            </div>
          </div>

          <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-4 mb-8">
            <p className="text-yellow-600 text-center">
              Este valor será debitado do seu saldo. Deseja continuar?
            </p>
          </div>

          <div className="space-y-4">
            <button
              onClick={handleConfirm}
              className="w-full bg-indigo-600 text-white py-3 px-4 rounded-md hover:bg-indigo-700 transition-colors flex items-center justify-center gap-2"
            >
              <Gift className="w-5 h-5" />
              Confirmar Resgate
            </button>
            <button
              onClick={handleCancel}
              className="w-full border border-gray-300 text-gray-700 py-3 px-4 rounded-md hover:bg-gray-100 transition-colors"
            >
              Cancelar
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default ConfirmacaoPage;
