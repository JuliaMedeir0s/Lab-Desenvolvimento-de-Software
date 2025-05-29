import { Routes, Route } from "react-router-dom";
import { Toaster } from "sonner";
import { AuthProvider } from "./contexts/AuthContext";
import ProtectedRoute from "./routes/ProtectedRoute";

// Páginas
import Login from "./pages/LoginPage";
import DashboardAluno from "./pages/HomeAlunoPage";
import DashboardProfessor from "./pages/HomeProfessorPage";
import DashboardParceiro from "./pages/HomeParceiroPage";
import ExtratoAluno from "./pages/ExtratoAlunoPage";
import ExtratoProfessor from "./pages/ExtratoProfessorPage";
import EnvioMoedas from "./pages/EnvioMoedasPage";
import ResgateVantagem from "./pages/ResgateVantagemPage";
import CadastroVantagem from "./pages/CadastroVantagemPage";
import RegistroAlunoPage from "./pages/RegistroAlunoPage";
import RegistroParceiroPage from "./pages/RegistroParceiroPage";

function App() {
  return (
    <AuthProvider>
      <Toaster richColors position="top-right" />
      <Routes>
        {/* Rota pública */}
        <Route path="/" element={<Login />} />
        <Route path="/registro-aluno" element={<RegistroAlunoPage />} />
        <Route path="/registro-parceiro" element={<RegistroParceiroPage />} />

        {/* Rotas protegidas - ALUNO */}
        <Route
          path="/dashboard-aluno"
          element={
            <ProtectedRoute requiredRole="ALUNO">
              <DashboardAluno />
            </ProtectedRoute>
          }
        />
        <Route
          path="/extrato-aluno"
          element={
            <ProtectedRoute requiredRole="ALUNO">
              <ExtratoAluno />
            </ProtectedRoute>
          }
        />
        <Route
          path="/resgate-vantagem"
          element={
            <ProtectedRoute requiredRole="ALUNO">
              <ResgateVantagem />
            </ProtectedRoute>
          }
        />

        {/* Rotas protegidas - PROFESSOR */}
        <Route
          path="/dashboard-professor"
          element={
            <ProtectedRoute requiredRole="PROFESSOR">
              <DashboardProfessor />
            </ProtectedRoute>
          }
        />
        <Route
          path="/envio-moedas"
          element={
            <ProtectedRoute requiredRole="PROFESSOR">
              <EnvioMoedas />
            </ProtectedRoute>
          }
        />
        <Route
          path="/extrato-professor"
          element={
            <ProtectedRoute requiredRole="PROFESSOR">
              <ExtratoProfessor />
            </ProtectedRoute>
          }
        />

        {/* Rotas protegidas - PARCEIRO */}
        <Route
          path="/dashboard-parceiro"
          element={
            <ProtectedRoute requiredRole="PARCEIRO">
              <DashboardParceiro />
            </ProtectedRoute>
          }
        />
        <Route
          path="/cadastro-vantagem"
          element={
            <ProtectedRoute requiredRole="PARCEIRO">
              <CadastroVantagem />
            </ProtectedRoute>
          }
        />
      </Routes>
    </AuthProvider>
  );
}

export default App;