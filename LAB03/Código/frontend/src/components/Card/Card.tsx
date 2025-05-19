import React from "react";
import { cardStyle } from "./styles";

interface CardProps {
  children: React.ReactNode;
}

function Card({ children }: CardProps) {
  return <div style={cardStyle}>{children}</div>;
}

export default Card;
