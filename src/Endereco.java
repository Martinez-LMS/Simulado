public class Endereco {
    private String nomeRua;
    private String cidade;
    private pessoas pessoa;

    public Endereco(String nomeRua, String cidade, pessoas pessoa) {
        this.nomeRua = nomeRua;
        this.cidade = cidade;
        this.pessoa = pessoa;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public String getCidade() {
        return cidade;
    }

    public pessoas getPessoa() {
        return pessoa;
    }
}
