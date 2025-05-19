import { Navigate } from "react-router-dom";

interface ProtectedRouteProps {
  children: React.ReactNode;
  requiredRole?: "aluno" | "professor" | "empresa";
}

function ProtectedRoute({ children, requiredRole }: ProtectedRouteProps) {
  const token = localStorage.getItem("token");
  const tipoUsuario = localStorage.getItem("tipoUsuario");

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  if (requiredRole && tipoUsuario !== requiredRole) {
    return <Navigate to="/login" replace />;
  }

  return <>{children}</>;
}

export default ProtectedRoute;
