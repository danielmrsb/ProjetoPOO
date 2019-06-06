package Model;

/**
 *
 * @author daniel.mbarbosa1
 */
public class Produto {

    private int codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private int quantidadeEstoque;
    private double valorUnitario;

    public Produto() {
    }

    public Produto(String nome,String tipo,  int quantidadeEstoque, double valorUnitario) {
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }
    
    public Produto(String nome, String descricao, String tipo, int quantidadeEstoque, double valorUnitario) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }

    public Produto(int codigo, String nome, String descricao, String tipo, int quantidadeEstoque, double valorUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
