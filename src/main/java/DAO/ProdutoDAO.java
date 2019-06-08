package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author daniel.mbarbosa1
 */
public class ProdutoDAO implements Editavel{

    private static final Database db = new Database();

    @Override
    public boolean salvar(Object x) {
        Produto p = (Produto) x;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("INSERT INTO"
                    + " tbl_produtos(nome, descricao, tipo, qtd_estoque, valor_unidade, status)"
                    + "VALUES (?, ?, ?, ?, ?, ?);");
            query.setString(1, p.getNome());
            query.setString(2, p.getDescricao());
            query.setString(3, p.getTipo());
            query.setInt(4, p.getQuantidadeEstoque());
            query.setDouble(5, p.getValorUnitario());
            query.setInt(6, 0);

            int rs = query.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Object x) {
        Produto produto = (Produto) x;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE"
                    + " tbl_produtos SET "
                    + " nome = ?,"
                    + " descricao = ?,"
                    + " tipo = ?,"
                    + " qtd_estoque = ?,"
                    + " valor_unidade = ? WHERE id_produto = ?;");

            query.setString(1, produto.getNome());
            query.setString(2, produto.getDescricao());
            query.setString(3, produto.getTipo());
            query.setInt(4, produto.getQuantidadeEstoque());
            query.setDouble(5, produto.getValorUnitario());
            query.setInt(6, produto.getCodigo());

            int linhasAfetadas = query.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(Object x) {
        int pCodigo = (int) x;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE tbl_produtos SET status = 1 WHERE id_produto = ?");

            query.setInt(1, pCodigo);

            query.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public Produto get(Object x) {
        int codigo = (int) x;
        Produto produto = null;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT id_produto, nome, descricao,"
                    + " tipo, qtd_estoque, valor_unidade"
                    + " FROM tbl_produtos WHERE id_produto = ? AND status = 0;");

            query.setInt(1, codigo);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Produto prod = new Produto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getDouble(6)
                    );
                    produto = prod;
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return produto;
    }
    
    @Override
    public ArrayList<Produto> getVarios() {
        ArrayList<Produto> produtos = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("select p.id_produto, p.nome, p.descricao, p.tipo,"
                    + " p.qtd_estoque, p.valor_unidade"
                    + " from tbl_produtos as p where p.status = 0;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Produto produto = new Produto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getDouble(6));
                    produtos.add(produto);

                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return produtos;
    }
}