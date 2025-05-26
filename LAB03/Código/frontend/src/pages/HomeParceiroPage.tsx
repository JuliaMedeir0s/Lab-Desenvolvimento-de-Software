import React, { useState } from "react";
import { PlusCircle, LogOut, Edit, Trash2 } from "lucide-react";
import { toast } from "sonner";
import { Reward } from "../types";
import RewardCard from "../components/RewardCard";
import ConfirmationDialog from "../components/ConfirmationDialog";

const HomeParceiroPage: React.FC = () => {
  const companyName = "Empresa ABC"; 

  const [rewards, setRewards] = useState<Reward[]>([
    {
      id: "1",
      name: "Desconto de 50% em Livros",
      description: "Válido para compras acima de R$100 na Livraria Central",
      coinValue: 500,
      imageUrl:
        "https://images.pexels.com/photos/1370295/pexels-photo-1370295.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    },
    {
      id: "2",
      name: "Entrada Gratuita no Cinema",
      description: "Válido para qualquer sessão de segunda a quinta",
      coinValue: 350,
      imageUrl:
        "https://images.pexels.com/photos/7991579/pexels-photo-7991579.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    },
    {
      id: "3",
      name: "Curso Online Gratuito",
      description: "Escolha entre 10 cursos disponíveis na plataforma",
      coinValue: 800,
      imageUrl:
        "https://images.pexels.com/photos/5905709/pexels-photo-5905709.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    },
  ]);

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [rewardToDelete, setRewardToDelete] = useState<string | null>(null);

  const handleLogout = () => {
    toast.info("Saindo do sistema...");
  };

  const handleEditReward = (id: string) => {
    toast.info(`Editando vantagem ${id}`);
  };

  const openDeleteDialog = (id: string) => {
    setRewardToDelete(id);
    setIsDialogOpen(true);
  };

  const confirmDelete = () => {
    if (rewardToDelete) {
      setRewards(rewards.filter((reward) => reward.id !== rewardToDelete));
      toast.success("Vantagem excluída com sucesso!");
      setIsDialogOpen(false);
      setRewardToDelete(null);
    }
  };

  const cancelDelete = () => {
    setIsDialogOpen(false);
    setRewardToDelete(null);
  };

  const handleAddReward = () => {
    toast.info("Adicionando nova vantagem...");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-4 md:p-8">
      <div className="max-w-7xl mx-auto">
        {/* Header */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8 flex flex-col md:flex-row justify-between items-start md:items-center">
          <h1 className="text-2xl md:text-3xl font-bold text-gray-800">
            Bem-vindo, <span className="text-indigo-600">{companyName}</span>!
          </h1>
          <div className="flex gap-4 mt-4 md:mt-0">
            <button
              onClick={handleAddReward}
              className="flex items-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg transition-colors duration-200"
            >
              <PlusCircle size={20} />
              <span>Cadastrar nova vantagem</span>
            </button>
            <button
              onClick={handleLogout}
              className="flex items-center gap-2 border border-gray-300 text-gray-700 hover:bg-gray-100 px-4 py-2 rounded-lg transition-colors duration-200"
            >
              <LogOut size={20} />
              <span className="hidden sm:inline">Sair</span>
            </button>
          </div>
        </div>

        {/* Vantagens */}
        <div className="bg-white rounded-xl shadow-lg p-6">
          <h2 className="text-xl font-semibold text-gray-800 mb-6">
            Vantagens Cadastradas
          </h2>

          {rewards.length > 0 ? (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {rewards.map((reward) => (
                <RewardCard
                  key={reward.id}
                  reward={reward}
                  onEdit={() => handleEditReward(reward.id)}
                  onDelete={() => openDeleteDialog(reward.id)}
                />
              ))}
            </div>
          ) : (
            <div className="flex flex-col items-center justify-center p-8">
              <p className="text-gray-500 text-lg">
                Nenhuma vantagem cadastrada até o momento.
              </p>
              <button
                onClick={handleAddReward}
                className="mt-4 flex items-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg transition-colors duration-200"
              >
                <PlusCircle size={20} />
                <span>Cadastrar nova vantagem</span>
              </button>
            </div>
          )}
        </div>
      </div>

      <ConfirmationDialog
        isOpen={isDialogOpen}
        title="Excluir Vantagem"
        message="Deseja realmente excluir esta vantagem?"
        confirmText="Excluir"
        cancelText="Cancelar"
        onConfirm={confirmDelete}
        onCancel={cancelDelete}
      />
    </div>
  );
};

export default HomeParceiroPage;
