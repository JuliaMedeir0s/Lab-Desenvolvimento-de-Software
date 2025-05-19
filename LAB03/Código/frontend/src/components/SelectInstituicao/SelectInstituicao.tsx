import React from "react";
import { selectStyle } from "./styles";

export interface Instituicao {
  id: number;
  nome: string;
}

interface SelectInstituicaoProps {
  value: number;
  onChange: (e: React.ChangeEvent<HTMLSelectElement>) => void;
  opcoes: Instituicao[];
}

function SelectInstituicao({
  value,
  onChange,
  opcoes,
}: SelectInstituicaoProps) {
  return (
    <select value={value} onChange={onChange} style={selectStyle}>
      <option value="">Selecione uma instituição</option>
      {opcoes.map((inst) => (
        <option key={inst.id} value={inst.id}>
          {inst.nome}
        </option>
      ))}
    </select>
  );
}

export default SelectInstituicao;
