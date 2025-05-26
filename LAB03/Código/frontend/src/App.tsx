import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import LoginPage from "./pages/LoginPage";
import RegistroAluno from "./pages/RegistroAlunoPage";
import RegistroParceiro from "./pages/RegistroParceiroPage";
import HomeAluno from "./pages/HomeAlunoPage";
import ProtectedRoute from "./routes/ProtectedRoute";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/registro/aluno" element={<RegistroAluno />} />
        <Route path="/registro/parceiro" element={<RegistroParceiro />} />

        <Route
          path="/aluno"
          element={
            <ProtectedRoute requiredRole="aluno">
              <HomeAluno />
            </ProtectedRoute>
          }
        />

        {/* Adicione mais rotas aqui conforme for criando */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;