import React, { useState, FormEvent } from "react";
import { ArrowLeft, LogOut } from "lucide-react";
import { useNavigate } from "react-router-dom";
import { toast } from "sonner";
import { Student } from "../types";

const EnvioMoedasPage: React.FC = () => {
  const navigate = useNavigate();
  const [selectedStudent, setSelectedStudent] = useState<Student | null>(null);
  const [coinAmount, setCoinAmount] = useState<number>(0);
  const [reason, setReason] = useState("");

  const currentBalance = 1000;

  const students: Student[] = [
    {
      id: "1",
      name: "Ana Silva",
      email: "ana@email.com",
      course: "Engenharia",
    },
    {
      id: "2",
      name: "Bruno Santos",
      email: "bruno@email.com",
      course: "Medicina",
    },
    {
      id: "3",
      name: "Carla Oliveira",
      email: "carla@email.com",
      course: "Direito",
    },
  ];

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    if (!selectedStudent) {
      toast.error("Por favor, selecione um aluno.");
      return;
    }

    if (coinAmount <= 0) {
      toast.error("A quantidade de moedas deve ser maior que zero.");
      return;
    }

    if (coinAmount > currentBalance) {
      toast.warning("Saldo insuficiente para realizar o envio.");
      return;
    }

    if (!reason.trim()) {
      toast.error("Por favor, informe o motivo do envio.");
      return;
    }

    try {
      console.log("Sending coins:", {
        studentId: selectedStudent.id,
        amount: coinAmount,
        reason,
      });

      toast.success("Moedas enviadas com sucesso!");
      navigate("/dashboard-professor");
    } catch (error) {
      toast.error(
        "Erro ao enviar moedas. Verifique os dados e tente novamente."
      );
    }
  };

  const handleLogout = () => {
    toast.info("Saindo do sistema...");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-4 md:p-8">
      <div className="max-w-3xl mx-auto">
        {/* Header */}
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8 flex justify-between items-center">
          <button
            onClick={() => navigate("/dashboard-professor")}
            className="flex items-center gap-2 text-gray-700 hover:text-gray-900 transition-colors duration-200"
          >
            <ArrowLeft size={20} />
            <span>Voltar</span>
          </button>
          <button
            onClick={handleLogout}
            className="flex items-center gap-2 border border-gray-300 text-gray-700 hover:bg-gray-100 px-4 py-2 rounded-lg transition-colors duration-200"
          >
            <LogOut size={20} />
            <span className="hidden sm:inline">Sair</span>
          </button>
        </div>

        <div className="bg-white rounded-xl shadow-lg p-6">
          <h1 className="text-2xl font-bold text-gray-800 text-center mb-8">
            Enviar Moedas para Aluno
          </h1>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label
                htmlFor="student"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Selecione o aluno
              </label>
              <select
                id="student"
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                value={selectedStudent?.id || ""}
                onChange={(e) => {
                  const student = students.find((s) => s.id === e.target.value);
                  setSelectedStudent(student || null);
                }}
                required
              >
                <option value="">Selecione um aluno</option>
                {students.map((student) => (
                  <option key={student.id} value={student.id}>
                    {student.name} - {student.course}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label
                htmlFor="amount"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Quantidade de moedas
              </label>
              <input
                type="number"
                id="amount"
                min="1"
                value={coinAmount}
                onChange={(e) => setCoinAmount(Number(e.target.value))}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                required
              />
            </div>

            <div>
              <label
                htmlFor="reason"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Motivo do envio
              </label>
              <textarea
                id="reason"
                value={reason}
                onChange={(e) => setReason(e.target.value)}
                rows={4}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                placeholder="Descreva o motivo do envio das moedas..."
                required
              />
            </div>

            <div className="bg-gray-50 p-4 rounded-lg">
              <p className="text-gray-700 font-medium">
                Saldo atual:{" "}
                <span className="text-indigo-600">{currentBalance} moedas</span>
              </p>
            </div>

            <div className="flex flex-col sm:flex-row gap-4 pt-4">
              <button
                type="button"
                onClick={() => navigate("/dashboard-professor")}
                className="w-full px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-100 transition-colors duration-200"
              >
                Cancelar
              </button>
              <button
                type="submit"
                className="w-full px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors duration-200"
              >
                Enviar Moedas
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default EnvioMoedasPage;
