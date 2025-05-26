import { toast as sonnerToast } from "sonner";
import { CheckCircle, XCircle, AlertTriangle, Info } from "lucide-react";

export type ToastType = "success" | "error" | "warning" | "info";

interface ToastOptions {
  title?: string;
  description?: string;
}

const ICONS = {
  success: <CheckCircle className="w-5 h-5 text-green-600" />,
  error: <XCircle className="w-5 h-5 text-red-600" />,
  warning: <AlertTriangle className="w-5 h-5 text-yellow-600" />,
  info: <Info className="w-5 h-5 text-indigo-600" />,
};

const DEFAULT_MESSAGES = {
  success: "Operação realizada com sucesso!",
  error: "Ocorreu um erro. Tente novamente.",
  warning: "Atenção! Verifique os dados informados.",
  info: "Seus dados foram atualizados.",
};

function createToast(
  type: ToastType,
  message: string = DEFAULT_MESSAGES[type],
  options?: ToastOptions
) {
  const icon = ICONS[type];

  return sonnerToast(
    <div className="flex items-start">
      <span className="mr-2 flex-shrink-0">{icon}</span>
      <div>
        {options?.title && <p className="font-medium">{options.title}</p>}
        <p>{message}</p>
      </div>
    </div>
  );
}

export const toast = {
  success: (message?: string, options?: ToastOptions) =>
    createToast("success", message, options),

  error: (message?: string, options?: ToastOptions) =>
    createToast("error", message, options),

  warning: (message?: string, options?: ToastOptions) =>
    createToast("warning", message, options),

  info: (message?: string, options?: ToastOptions) =>
    createToast("info", message, options),
};