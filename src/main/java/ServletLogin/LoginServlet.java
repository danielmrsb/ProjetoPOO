package ServletLogin;

import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author daniel.mbarbosa1
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login/valida_usuario"})
public class LoginServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String uEmail = request.getParameter("email");
        String uSenha = request.getParameter("password");

        boolean error = false;
        if (uEmail.length() == 0) {
            error = true;
            request.setAttribute("emailError", "O e-mail é obrigatório");
        }
        if (uSenha.length() == 0) {
            error = true;
            request.setAttribute("loginError", uEmail);
            request.setAttribute("senhaError", "A senha é obrigatória");
        }

        if (error) {
            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Campo de E-mail ou Senha inválido");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/index.jsp");
            dispatcher.forward(request, response);
        } else {
            boolean httpOK = UsuarioDAO.getLogin(uEmail, uSenha);

            if (httpOK) {
                Usuario infoSessao = UsuarioDAO.getInfoSessao(uEmail);
                HttpSession sessao = request.getSession();
                //sessao.setAttribute("nomeUsuario", infoSessao.getNome());

                response.sendRedirect("../ti/listagem_usuarios.jsp");
            } else {
                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Usuário ou Senha não existem.");

                request.setAttribute("loginError", uEmail);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/index.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        processaRequisicao("GET", req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        processaRequisicao("POST", req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
