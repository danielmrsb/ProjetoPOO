package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daniel.mbarbosa1
 */
public class Database {

    private final String driver;
    private final String usuario;
    private final String senha;
    private final String nomeDB;
    private final String url;
    private Connection conexao;

    public Database() {
        driver = "com.mysql.cj.jdbc.Driver";
        usuario = "root";
        senha = "";
        nomeDB = "TADES_BCD";
        url = "jdbc:mysql://127.0.0.1:3307/" + nomeDB + "?useTimezone=true&serverTimezone=UTC";
    }

    public String getDriver() {
        return driver;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeDB() {
        return nomeDB;
    }

    public String getUrl() {
        return url;
    }

    public Connection getConexao() {
        return conexao;
    }

    public Connection obterConexao() {
        Database db = new Database();
        try {
            Class.forName(db.getDriver());
            conexao = DriverManager.getConnection(
                    db.getUrl(),
                    db.getUsuario(),
                    db.getSenha()
            );
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        return conexao;
    }

}
