import React, { useState, ChangeEvent, FormEvent } from "react";
import { ArrowLeft, LogOut, Upload } from "lucide-react";
import { toast } from "sonner";
import { useNavigate } from "react-router-dom";
import { cadastrarVantagem } from "../services/vantagem.service";

interface FormData {
  name: string;
  description: string;
  coinValue: number;
  image: File | null;
}

const CadastroVantagemPage: React.FC = () => {
  const navigate = useNavigate();
  const [imagePreview, setImagePreview] = useState<string | null>(null);
  const [formData, setFormData] = useState<FormData>({
    name: "",
    description: "",
    coinValue: 0,
    image: null,
  });

  const handleInputChange = (
    e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setFormData((prev) => ({
        ...prev,
        image: file,
      }));

      const reader = new FileReader();
      reader.onloadend = () => {
        setImagePreview(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    try {
      const payload = {
        nome: formData.name,
        descricao: formData.description,
        custo: Number(formData.coinValue),
        imagem: imagePreview || "",
      };      

      if (!imagePreview) {
        toast.error("Selecione uma imagem para a vantagem.");
        return;
      }
      console.log("Enviando payload:", payload);
      await cadastrarVantagem(payload);

      toast.success("Vantagem cadastrada com sucesso!");
      navigate("/dashboard-parceiro");
    } catch (error: any) {
      toast.error(
        error.response?.data?.error ||
          "Erro ao cadastrar vantagem. Verifique os dados e tente novamente."
      );
    }
  };

  const handleLogout = () => {
    toast.info("Saindo do sistema...");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-4 md:p-8">
      <div className="max-w-3xl mx-auto">
        <div className="bg-white rounded-xl shadow-lg p-6 mb-8 flex justify-between items-center">
          <button
            onClick={() => navigate("/dashboard-parceiro")}
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
            Cadastrar Vantagem
          </h1>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label
                htmlFor="name"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Nome da vantagem<span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                id="name"
                name="name"
                required
                value={formData.name}
                onChange={handleInputChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                placeholder="Digite o nome da vantagem"
              />
            </div>

            <div>
              <label
                htmlFor="description"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Descrição da vantagem<span className="text-red-500">*</span>
              </label>
              <textarea
                id="description"
                name="description"
                required
                value={formData.description}
                onChange={handleInputChange}
                rows={4}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                placeholder="Digite a descrição da vantagem"
              />
            </div>

            <div>
              <label
                htmlFor="coinValue"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Valor em moedas<span className="text-red-500">*</span>
              </label>
              <input
                type="number"
                id="coinValue"
                name="coinValue"
                required
                min="0"
                value={formData.coinValue}
                onChange={handleInputChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                placeholder="Digite o valor em moedas"
              />
            </div>

            <div>
              <label
                htmlFor="image"
                className="block text-sm font-medium text-gray-700 mb-1"
              >
                Imagem da vantagem<span className="text-red-500">*</span>
              </label>
              <div className="mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-lg">
                <div className="space-y-1 text-center">
                  {imagePreview ? (
                    <div className="mb-4">
                      <img
                        src={imagePreview}
                        alt="Preview"
                        className="mx-auto h-32 w-auto rounded-lg"
                      />
                    </div>
                  ) : (
                    <Upload
                      className="mx-auto h-12 w-12 text-gray-400"
                      aria-hidden="true"
                    />
                  )}
                  <div className="flex text-sm text-gray-600">
                    <label
                      htmlFor="image"
                      className="relative cursor-pointer rounded-md font-medium text-indigo-600 hover:text-indigo-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-indigo-500"
                    >
                      <span>Fazer upload de arquivo</span>
                      <input
                        id="image"
                        name="image"
                        type="file"
                        accept="image/*"
                        onChange={handleImageChange}
                        className="sr-only"
                      />
                    </label>
                    <p className="pl-1">ou arraste e solte</p>
                  </div>
                  <p className="text-xs text-gray-500">
                    PNG, JPG, GIF até 10MB
                  </p>
                </div>
              </div>
              <p className="text-sm text-gray-500 mt-2">
                Dimensão recomendada:{" "} <strong>800x400px</strong>. Tamanho máximo: <strong>1MB</strong>.
              </p>
            </div>
            <div className="flex flex-col sm:flex-row gap-4 pt-4">
              <button
                type="button"
                onClick={() => navigate("/dashboard-parceiro")}
                className="w-full px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-100 transition-colors duration-200"
              >
                Cancelar
              </button>
              <button
                type="submit"
                className="w-full px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors duration-200"
              >
                Cadastrar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default CadastroVantagemPage;
