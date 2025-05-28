import { useState, useRef } from "react";
import { toast } from "sonner";
import axios from "axios";
import { Edit } from "lucide-react";

interface EditVantagemModalProps {
  isOpen: boolean;
  onClose: () => void;
  vantagem: {
    id: string;
    name: string;
    description: string;
    coinValue: number;
    imageUrl: string;
  };
  onUpdate: (updated: any) => void;
}

const EditVantagemModal: React.FC<EditVantagemModalProps> = ({
  isOpen,
  onClose,
  vantagem,
  onUpdate,
}) => {
  const [formData, setFormData] = useState({
    nome: vantagem.name,
    descricao: vantagem.description,
    custo: vantagem.coinValue,
    imagemUrl: vantagem.imageUrl,
  });

  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setFormData((prev) => ({
          ...prev,
          imagemUrl: reader.result as string,
        }));
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = async () => {
    try {
      const token = localStorage.getItem("token");
      const payload = {
        nome: formData.nome,
        descricao: formData.descricao,
        custo: Number(formData.custo),
        imagem: formData.imagemUrl,
      };

      const response = await axios.put(
        `http://localhost:3000/vantagens/${vantagem.id}`,
        payload,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      toast.success("Vantagem atualizada com sucesso!");
      onUpdate(response.data);
      onClose();
    } catch (error) {
      toast.error("Erro ao atualizar a vantagem.");
      console.error(error);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 z-50 flex justify-center items-center">
      <div className="bg-white p-6 rounded-md w-full max-w-lg shadow-lg">
        <h2 className="text-xl font-bold mb-4">Editar Vantagem</h2>

        {/* Upload de imagem com ícone */}
        <div
          className="mb-4 relative w-full h-48 border border-dashed rounded-md flex items-center justify-center bg-gray-50 cursor-pointer"
          onClick={() => fileInputRef.current?.click()}
        >
          {formData.imagemUrl ? (
            <img
              src={formData.imagemUrl}
              alt="Preview"
              className="object-contain h-full"
            />
          ) : (
            <p className="text-gray-400">Clique para selecionar uma imagem</p>
          )}

          <div className="absolute top-2 right-2 bg-white rounded-full p-1 shadow">
            <Edit size={18} className="text-gray-700" />
          </div>

          <input
            type="file"
            ref={fileInputRef}
            onChange={handleImageChange}
            accept="image/*"
            className="hidden"
          />
        </div>

        {/* Formulário */}
        <div className="flex flex-col gap-3">
          <input
            type="text"
            name="nome"
            value={formData.nome}
            onChange={handleChange}
            className="border p-2 rounded"
            placeholder="Nome"
          />
          <textarea
            name="descricao"
            value={formData.descricao}
            onChange={handleChange}
            className="border p-2 rounded"
            placeholder="Descrição"
          />
          <input
            type="number"
            name="custo"
            value={formData.custo}
            onChange={handleChange}
            className="border p-2 rounded"
            placeholder="Custo em moedas"
          />
        </div>

        {/* Ações */}
        <div className="flex justify-end gap-2 mt-6">
          <button onClick={onClose} className="px-4 py-2 border rounded">
            Cancelar
          </button>
          <button
            onClick={handleSubmit}
            className="px-4 py-2 bg-indigo-600 text-white rounded"
          >
            Salvar
          </button>
        </div>
      </div>
    </div>
  );
};

export default EditVantagemModal;