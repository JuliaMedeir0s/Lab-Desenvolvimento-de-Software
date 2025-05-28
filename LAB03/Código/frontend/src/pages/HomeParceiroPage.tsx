import React, { useEffect, useState } from "react";
import { PlusCircle, LogOut } from "lucide-react";
import { toast } from "sonner";
import { Reward } from "../types";
import RewardCard from "../components/RewardCard";
import ConfirmationDialog from "../components/ConfirmationDialog";
import EditVantagemModal from "../components/EditVantagemModal";
import { useAuth } from "../contexts/AuthContext";
import { getParceiroInfo } from "../services/parceiro.services";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const HomeParceiroPage: React.FC = () => {
  const navigate = useNavigate();
  const { logout } = useAuth();

  const [vantagemSelecionada, setVantagemSelecionada] = useState<any | null>(
    null
  );
  const [modalAberto, setModalAberto] = useState(false);

  const [companyName, setCompanyName] = useState("...");
  const [rewards, setRewards] = useState<Reward[]>([]);

  useEffect(() => {
    async function fetchParceiro() {
      try {
        const data = await getParceiroInfo();
        setCompanyName(data.usuario.nome);
        const formatted = data.vantagens.map((v: any) => ({
          id: v.id,
          name: v.nome,
          description: v.descricao,
          coinValue: v.custo,
          imageUrl: v.imagem || "",
        }));
        setRewards(formatted);
      } catch {
        toast.error("Erro ao carregar dados do parceiro.");
      }
    }

    fetchParceiro();
  }, []);

  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [rewardToDelete, setRewardToDelete] = useState<string | null>(null);

  const handleLogout = () => {
    logout();
  };

  const handleEditReward = (rewardId: string) => {
    const vantagem = rewards.find((r) => r.id === rewardId);
    if (vantagem) {
      setVantagemSelecionada(vantagem);
      setModalAberto(true);
    }
  };  

  const handleUpdateVantagem = () => {
    recarregarVantagens(); 
  };

  const openDeleteDialog = (id: string) => {
    setRewardToDelete(id);
    setIsDialogOpen(true);
  };

  const confirmDelete = async () => {
    if (!rewardToDelete) return;

    try {
      const token = localStorage.getItem("token");

      await axios.delete(`http://localhost:3000/vantagens/${rewardToDelete}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      setRewards((prev) =>
        prev.filter((reward) => reward.id !== rewardToDelete)
      );

      toast.success("Vantagem excluída com sucesso!");
    } catch (error) {
      toast.error("Erro ao excluir vantagem.");
      console.error(error);
    } finally {
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
    navigate("/cadastro-vantagem");
  };

  const recarregarVantagens = () => {
    getParceiroInfo()
      .then((data) => {
        const formatted = data.vantagens.map((v: any) => ({
          id: v.id,
          name: v.nome,
          description: v.descricao,
          coinValue: v.custo,
          imageUrl: v.imagem || "",
        }));
        setRewards(formatted);
      })
      .catch(() => {
        toast.error("Erro ao recarregar vantagens");
      });
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

      {modalAberto && vantagemSelecionada && (
        <EditVantagemModal
          isOpen={modalAberto}
          onClose={() => setModalAberto(false)}
          vantagem={vantagemSelecionada}
          onUpdate={handleUpdateVantagem}
        />
      )}

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
