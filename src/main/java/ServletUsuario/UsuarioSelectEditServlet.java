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
@WebServlet(name = "UsuarioSelectEditServlet", urlPatterns = {"/ti/dados_usuario"})
public class UsuarioSelectEditServlet extends HttpServlet {

    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cCodigo = request.getParameter("editarID");
        Usuario usuario = UsuarioDAO.getUsuario(Integer.parseInt(cCodigo));
       
        ArrayList<Usuario> setores = UsuarioDAO.getSetoresCadastro();   
        
        request.setAttribute("acao", "editar");
        request.setAttribute("codigo", usuario.getCodigo());
        request.setAttribute("nome", usuario.getNome());
        request.setAttribute("email", usuario.getEmail());
        request.setAttribute("senha", usuario.getSenha());
        request.setAttribute("setor", usuario.getSetor());
        request.setAttribute("nomeSetor", usuario.getNomeSetor());
        request.setAttribute("listaSetores", setores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ti/cadastro_usuarios.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processaRequisicao("POST", req, resp);
    }

}
