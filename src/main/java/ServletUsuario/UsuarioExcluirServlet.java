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
@WebServlet(name = "UsuarioExcluirServlet", urlPatterns = {"/ti/excluir_usuario"})
public class UsuarioExcluirServlet extends HttpServlet {

    protected void processaRequisicao(String HttpMethod, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cCodigo = request.getParameter("excluirID");

        boolean error = false;
        if (cCodigo == null) {
            error = true;
            request.setAttribute("codigoErro", "Codigo não informado");
        } else if (cCodigo.equalsIgnoreCase("0")) {
            error = true;
            request.setAttribute("codigoErro", "Codigo invalido");
        }

        if (error) {
            ArrayList<Usuario> usuarios = UsuarioDAO.getUsuarios();
            request.setAttribute("listaUsuarios", usuarios);

            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Erro ao excluir o usuário, verifique os campos e tente novamente.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_usuarios.jsp");
            dispatcher.forward(request, response);
        } else {
            boolean httpOk = UsuarioDAO.excluirUsuario(Integer.parseInt(cCodigo));
            if (httpOk) {
                ArrayList<Usuario> usuarios = UsuarioDAO.getUsuarios();
                request.setAttribute("listaUsuarios", usuarios);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Usuário excluido com sucesso.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_usuarios.jsp");
                dispatcher.forward(request, response);
            } else {
                ArrayList<Usuario> usuarios = UsuarioDAO.getUsuarios();
                request.setAttribute("listaUsuarios", usuarios);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Erro ao excluir usuário.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/listagem_filiais.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processaRequisicao("GET", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processaRequisicao("POST", request, response);
    }

}
