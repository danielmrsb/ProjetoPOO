package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author daniel.mbarbosa1
 */
public class UsuarioDAO implements Editavel {

    private static final Database db = new Database();

    @Override
    public boolean salvar(Object x) {
        Usuario u = (Usuario) x;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("INSERT INTO "
                    + " tbl_usuario(nome, email, senha, fk_setor, status) "
                    + "VALUES (?, ?, ?, ?, ?);");

            query.setString(1, u.getNome());
            query.setString(2, u.getEmail());
            query.setString(3, u.getSenha());
            query.setInt(4, u.getSetor());
            query.setInt(5, 0);

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
        Usuario u = (Usuario) x;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE"
                    + " tbl_usuario SET nome = ?, email = ?, senha = ?, fk_setor = ? WHERE id_usuario = ?;");

            query.setString(1, u.getNome());
            query.setString(2, u.getEmail());
            query.setString(3, u.getSenha());
            query.setInt(4, u.getSetor());
            query.setInt(5, u.getCodigo());

            int rs = query.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(Object x) {
        int uCodigo = (int) x;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("UPDATE tbl_usuario SET status = 1 WHERE id_usuario = ?");

            query.setInt(1, uCodigo);

            query.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    public Object get(Object x) {
        int codigoUsuario = (int) x;
        Usuario usuarios = null;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT u.id_usuario, u.nome, u.email, u.senha,"
                    + " u.fk_setor, s.nome_setor"
                    + " FROM tbl_usuario AS u INNER JOIN tbl_setor AS s ON "
                    + " u.fk_setor = s.id_setor "
                    + " where u.id_usuario = ? AND u.status = 0;");

            query.setInt(1, codigoUsuario);
            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Usuario user = new Usuario(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5));
                    user.setNomeSetor(rs.getString(6));
                    usuarios = user;
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return usuarios;
    }

    @Override
    public ArrayList<Usuario> getVarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT u.id_usuario, u.nome, u.email, u.senha, s.id_setor, s.nome_setor"
                    + "    FROM tbl_usuario AS u INNER JOIN tbl_setor AS s ON \n"
                    + "    u.fk_setor = s.id_setor \n"
                    + "    WHERE u.status = 0;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Usuario user = new Usuario(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6));
                    usuarios.add(user);
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return usuarios;
    }

    public static ArrayList<Usuario> getSetoresCadastro() {
        ArrayList<Usuario> setores = new ArrayList<>();
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT id_setor, nome_setor FROM tbl_setor;");

            ResultSet rs = query.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Usuario user = new Usuario();
                    user.setSetor(rs.getInt(1));
                    user.setNomeSetor(rs.getString(2));
                    setores.add(user);
                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return setores;
    }

    public static boolean getLogin(String email, String senha) {
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM tbl_usuario WHERE email = ? AND senha = ? AND status = 0;");
            query.setString(1, email);
            query.setString(2, senha);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                return true;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public static Usuario getInfoSessao(String uEmail) {
        Usuario sessao = null;
        Connection conn = db.obterConexao();
        try {
            PreparedStatement query = conn.prepareStatement(" select * from tbl_usuario where email = ?;");

            query.setString(1, uEmail);

            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                sessao = new Usuario();
                sessao.setCodigo(rs.getInt(1));
                sessao.setNome(rs.getString(2));
                sessao.setEmail(rs.getString(3));
                sessao.setSetor(rs.getInt(4));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return sessao;
    }
}
