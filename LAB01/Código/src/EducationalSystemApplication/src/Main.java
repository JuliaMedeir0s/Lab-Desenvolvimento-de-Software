//import DAO.UsuarioDAO;
import controller.SessaoController;
import views.LoginView;
import utils.Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        Utils.limparTela();
        //cria usuario padrão
        SessaoController.verificarUsuarioPadrao();
        //UsuarioDAO.getInstance().imprimirUsuarios();
        System.out.println("===== SISTEMA DE MATRÍCULAS =====");
        //abrir tela de login
        LoginView.mostrarLogin();
        System.out.println("Sistema encerrado.");
    }
}