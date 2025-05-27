import { ReactNode, createContext, useContext, useState } from "react";
import { login } from "../services/auth.service";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

interface TokenData {
  userId: string;
  tipo: "ALUNO" | "PROFESSOR" | "PARCEIRO";
  iat: number;
  exp: number;
}

interface AuthContextType {
  isAuthenticated: boolean;
  token: string | null;
  tipo: TokenData["tipo"] | null;
  loginUser: (email: string, senha: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType>({} as AuthContextType);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState<string | null>(() =>
    localStorage.getItem("token")
  );
  const [tipo, setTipo] = useState<TokenData["tipo"] | null>(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) {
      const decoded = jwtDecode<TokenData>(storedToken);
      return decoded.tipo;
    }
    return null;
  });

  const navigate = useNavigate();
  const isAuthenticated = !!token;

  async function loginUser(email: string, senha: string) {
    const data = await login(email, senha);
    setToken(data.token);
    localStorage.setItem("token", data.token);

    const decoded = jwtDecode<TokenData>(data.token);
    setTipo(decoded.tipo);

    // Redirecionamento automático baseado no tipo
    if (decoded.tipo === "ALUNO") navigate("/dashboard-aluno");
    if (decoded.tipo === "PROFESSOR") navigate("/dashboard-professor");
    if (decoded.tipo === "PARCEIRO") navigate("/dashboard-parceiro");
  }

  function logout() {
    setToken(null);
    setTipo(null);
    localStorage.removeItem("token");
    navigate("/");
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, token, tipo, loginUser, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}