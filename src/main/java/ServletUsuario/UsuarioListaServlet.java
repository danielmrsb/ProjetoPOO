package ServletUsuario;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel.mbarbosa1
 */
@WebServlet(name = "UsuarioListaServlet", urlPatterns = {"/ti/listagem_usuarios"})
public class UsuarioListaServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Usuario> usuarios = UsuarioDAO.getUsuarios();
        request.setAttribute("listaUsuarios", usuarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_usuarios.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("GET", req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("POST", req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
