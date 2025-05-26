import React from "react";
import { Toaster as SonnerToaster } from "sonner";

export function ToastProvider() {
  return (
    <SonnerToaster
      position="top-right"
      toastOptions={{
        duration: 3000,
        className: "sonner-toast",
        classNames: {
          success: "bg-green-500 text-white",
          error: "bg-red-500 text-white",
          warning: "bg-yellow-500 text-white",
          info: "bg-indigo-500 text-white",
        },
      }}
    />
  );
}
