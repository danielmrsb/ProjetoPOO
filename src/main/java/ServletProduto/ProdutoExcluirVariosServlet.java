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
@WebServlet(name = "ProdutoExcluirVariosServlet", urlPatterns = {"/produtos/excluir_produtos"})
public class ProdutoExcluirVariosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] produtosSelecionados = request.getParameterValues("selected");

        boolean httpOK = ProdutoDAO.excluirProdutos(produtosSelecionados);

        if (httpOK) {
            ArrayList<Produto> produtos = ProdutoDAO.getProdutos();
            request.setAttribute("listaProdutos", produtos);

            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Produtos excluidos com sucesso.");

        } else {
            ArrayList<Produto> produtos = ProdutoDAO.getProdutos();
            request.setAttribute("listaProdutos", produtos);

            request.setAttribute("varMsgErro", true);
            request.setAttribute("msg", "Erro ao excluir Produtos.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/listagem_produtos.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
