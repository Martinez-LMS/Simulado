import java.util.ArrayList;
import java.util.List;

public class pessoas {
    private String codigo;
    private String nome;
    private List<Endereco> enderecos;

    public pessoas(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.enderecos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }
}
