import React from "react";
import { formStyle } from "./styles";

interface FormProps {
  onSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
  children: React.ReactNode;
}

function Form({ onSubmit, children }: FormProps) {
  return (
    <form onSubmit={onSubmit} style={formStyle}>
      {children}
    </form>
  );
}

export default Form;
