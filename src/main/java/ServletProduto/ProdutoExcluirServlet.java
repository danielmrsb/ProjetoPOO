package ServletProduto;

import DAO.ProdutoDAO;
import Model.Produto;
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
@WebServlet(name = "ProdutoExcluirServlet", urlPatterns = {"/produtos/excluir_produto"})
public class ProdutoExcluirServlet extends HttpServlet {

    ProdutoDAO ProDAO = new ProdutoDAO();
    protected void processaRequisicao(String HttpMethod, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pCodigo = request.getParameter("excluirID");

        boolean error = false;
        if (pCodigo == null) {
            error = true;
            request.setAttribute("codigoErro", "Codigo não informado");
        } else if (pCodigo.equalsIgnoreCase("0")) {
            error = true;
            request.setAttribute("codigoErro", "Codigo invalido");
        }

        if (error) {
            ArrayList<Produto> produtos = ProDAO.getVarios();
            request.setAttribute("listaProdutos", produtos);

            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Erro ao excluir o produto.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/listagem_produtos.jsp");
            dispatcher.forward(request, response);
            
        } else {
            
            boolean httpOk = ProDAO.excluir(Integer.parseInt(pCodigo));
            if (httpOk) {
                ArrayList<Produto> produtos = ProDAO.getVarios();
                request.setAttribute("listaProdutos", produtos);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Produto excluido com sucesso.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/listagem_produtos.jsp");
                dispatcher.forward(request, response);
            } else {
                ArrayList<Produto> produtos = ProDAO.getVarios();
                request.setAttribute("listaProdutos", produtos);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Erro ao excluir produto.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/listagem_produtos.jsp");
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
