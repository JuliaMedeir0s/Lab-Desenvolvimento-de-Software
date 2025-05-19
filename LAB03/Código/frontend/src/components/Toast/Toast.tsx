import React from "react";
import { toastContainer } from "./styles";

interface ToastProps {
  message: string;
  type?: "success" | "error" | "info";
}

function Toast({ message, type = "info" }: ToastProps) {
  return <div style={toastContainer(type)}>{message}</div>;
}

export default Toast;
