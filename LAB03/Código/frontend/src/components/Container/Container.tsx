import React from "react";
import { containerStyle } from "./styles";

interface ContainerProps {
  children: React.ReactNode;
}

function Container({ children }: ContainerProps) {
  return <div style={containerStyle}>{children}</div>;
}

export default Container;
