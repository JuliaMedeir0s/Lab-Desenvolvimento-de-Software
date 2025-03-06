//import DAO.UsuarioDAO;
import controller.SessaoController;
import views.LoginView;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("===== SISTEMA DE MATRÍCULAS =====");
        //cria usuario padrão
        SessaoController.verificarUsuarioPadrao();
        //UsuarioDAO.getInstance().imprimirUsuarios();
        //abrir tela de login
        LoginView.mostrarLogin();
        System.out.println("Sistema encerrado.");
    }
}