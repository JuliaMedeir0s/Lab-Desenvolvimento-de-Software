import React from "react";

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
    label?: string;
    id: string;
}

function Input({ label, id, ...rest }: InputProps) {
  return (
    <div>
      {label && <label htmlFor={id}>{label}</label>}
      <input id={id} {...rest} />
    </div>
  );
}

export default Input;
