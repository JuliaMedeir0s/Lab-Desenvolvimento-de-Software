import React from "react";
import { labelStyle } from "./styles";

interface LabelProps {
  htmlFor: string;
  children: React.ReactNode;
}

function Label({ htmlFor, children }: LabelProps) {
  return (
    <label htmlFor={htmlFor} style={labelStyle}>
      {children}
    </label>
  );
}

export default Label;
