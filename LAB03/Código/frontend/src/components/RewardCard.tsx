import React from "react";
import { Edit, Trash2 } from "lucide-react";
import { Reward } from "../types";

interface RewardCardProps {
  reward: Reward;
  onEdit: () => void;
  onDelete: () => void;
}

const RewardCard: React.FC<RewardCardProps> = ({
  reward,
  onEdit,
  onDelete,
}) => {
  return (
    <div className="bg-white border border-gray-200 rounded-lg shadow-md overflow-hidden transition-all duration-200 hover:shadow-lg">
      {reward.imageUrl && (
        <div className="h-48 overflow-hidden">
          <img
            src={reward.imageUrl}
            alt={reward.name}
            className="w-full h-full object-cover transition-transform duration-500 hover:scale-105"
          />
        </div>
      )}
      <div className="p-5">
        <h3 className="text-xl font-semibold text-gray-800 mb-2">
          {reward.name}
        </h3>
        <p className="text-gray-600 mb-4">{reward.description}</p>
        <div className="flex items-center justify-between mb-4">
          <div className="bg-indigo-100 text-indigo-800 px-3 py-1 rounded-full font-medium">
            {reward.coinValue} moedas
          </div>
        </div>
        <div className="flex gap-2">
          <button
            onClick={onEdit}
            className="flex items-center justify-center gap-2 flex-1 border border-gray-300 text-gray-700 hover:bg-gray-100 px-4 py-2 rounded-lg transition-colors duration-200"
          >
            <Edit size={18} />
            <span>Editar</span>
          </button>
          <button
            onClick={onDelete}
            className="flex items-center justify-center gap-2 flex-1 bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded-lg transition-colors duration-200"
          >
            <Trash2 size={18} />
            <span>Excluir</span>
          </button>
        </div>
      </div>
    </div>
  );
};

export default RewardCard;
