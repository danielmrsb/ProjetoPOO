package Model;

/**
 *
 * @author daniel.mbarbosa1
 */
public class Usuario extends Funcionario{

    private int codigo;
    private String senha;
    private int setor;
    private String nomeSetor;

    public Usuario() {

    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int codigo, String nome, String email, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha, int setor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
    }

    public Usuario(int codigo, String nome, String email, String senha, int setor, String nomeSetor) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
        this.nomeSetor = nomeSetor;
    }
    
    public Usuario(int codigo, String nome, String email, String senha, int setor) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

}
