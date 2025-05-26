import React from "react";
import { AlertCircle } from "lucide-react";

interface EmptyStateProps {
  message: string;
}

const EmptyState: React.FC<EmptyStateProps> = ({ message }) => {
  return (
    <div className="flex flex-col items-center justify-center py-12 px-4 text-center">
      <AlertCircle className="w-12 h-12 text-gray-400 mb-4" />
      <p className="text-lg text-gray-600 font-medium">{message}</p>
      <p className="mt-2 text-sm text-gray-500">
        Quando você distribuir moedas para alunos, suas transações aparecerão
        aqui.
      </p>
    </div>
  );
};

export default EmptyState;
