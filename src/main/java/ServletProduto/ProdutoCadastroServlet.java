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
@WebServlet(name = "ProdutoCadastroServlet", urlPatterns = {"/produtos/cadastro_produto"})
public class ProdutoCadastroServlet extends HttpServlet {

    ProdutoDAO ProDAO = new ProdutoDAO();
    private void processaRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String fNome = request.getParameter("nome");
        String fDescricao = request.getParameter("descricao");
        String fTipo = request.getParameter("tipo");
        String fQuantidadeEstoque = request.getParameter("quantidadeEstoque");
        String fValorUnitario = request.getParameter("valorUnitario");

        boolean error = false;
        if (fNome.length() == 0) {
            error = true;
            request.setAttribute("nomeErro", "Nome não informado");
        }
        if (fTipo == null) {
            error = true;
            request.setAttribute("tipoErro", "Tipo não informado");
        }
        if (fQuantidadeEstoque.length() == 0) {
            error = true;
            request.setAttribute("quantidadeEstoqueErro", "Quantidade em estoque não informada");
        }
        if (fValorUnitario.length() == 0) {
            error = true;
            request.setAttribute("valorUnitarioErro", "Valor unitário não informado");
        } else {
            String valorReplace;
            valorReplace = fValorUnitario.replace("R$", "");
            valorReplace = valorReplace.replace(",", ".");

            fValorUnitario = valorReplace;
        }

        if (error) {
            
            request.setAttribute("varMsg", true);
            request.setAttribute("msg", "Erro ao realizar o cadastro, verifique os campos e tente novamente.");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/cadastro_produtos.jsp");
            dispatcher.forward(request, response);
        } else {
            Produto produtos = new Produto(fNome, fTipo, Integer.parseInt(fQuantidadeEstoque), Double.parseDouble(fValorUnitario));
            if (fDescricao.length() != 0) {
                produtos.setDescricao(fDescricao);
            }
            boolean httpOK = ProDAO.salvar(produtos);

            if (httpOK) {
                ArrayList<Produto> produto = ProDAO.getVarios();
                request.setAttribute("listaProdutos", produto);

                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Cadastro realizado com sucesso.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/listagem_produtos.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("varMsg", true);
                request.setAttribute("msg", "Erro ao realizar o cadastro no banco de dados, verifique os campos e tente novamente.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/produtos/cadastro_produtos.jsp");
                dispatcher.forward(request, response);
            }
        }
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
