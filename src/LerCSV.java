import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LerCSV {

    static class Pessoa {
        String codigo;
        String nome;
        List<Endereco> enderecos;

        public Pessoa(String codigo, String nome) {
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

    static class Endereco {
        String nomeRua;
        String cidade;
        Pessoa pessoa;

        public Endereco(String nomeRua, String cidade, Pessoa pessoa) {
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

        public Pessoa getPessoa() {
            return pessoa;
        }
    }

    public static void main(String[] args) {
        LerCSV lerCSV = new LerCSV();
        lerCSV.executar();
    }

    public void executar() {
        Map<String, Pessoa> mapaPessoas = new HashMap<>();
        List<Endereco> listaEnderecos = new ArrayList<>();

        // Leitura do arquivo pessoas.csv
        String arquivoPessoas = "C:\\_ws\\Java\\Simulado\\Simulado\\src\\pessoas.csv";
        String linhaPessoa;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoPessoas))) {
            while ((linhaPessoa = br.readLine()) != null) {
                String[] dadosPessoa = linhaPessoa.split(";");
                String codigoPessoa = dadosPessoa[0];
                String nomePessoa = dadosPessoa[1];

                // Criando pessoa se ainda não existe no mapa
                Pessoa pessoa = mapaPessoas.get(codigoPessoa);
                if (pessoa == null) {
                    pessoa = new Pessoa(codigoPessoa, nomePessoa);
                    mapaPessoas.put(codigoPessoa, pessoa);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + arquivoPessoas + ": " + e.getMessage());
        }

        // Leitura do arquivo enderecos.csv
        String arquivoEnderecos = "C:\\_ws\\Java\\Simulado\\Simulado\\src\\enderecos.csv";
        String linhaEndereco;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoEnderecos))) {
            while ((linhaEndereco = br.readLine()) != null) {
                String[] dadosEndereco = linhaEndereco.split(";");
                String nomeRua = dadosEndereco[0];
                String cidade = dadosEndereco[1];
                String codigoPessoa = dadosEndereco[2];

                // Buscando pessoa no mapa
                Pessoa pessoa = mapaPessoas.get(codigoPessoa);
                if (pessoa != null) {
                    Endereco endereco = new Endereco(nomeRua, cidade, pessoa);
                    pessoa.addEndereco(endereco);
                    listaEnderecos.add(endereco);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + arquivoEnderecos + ": " + e.getMessage());
        }

        // Agora você tem as pessoas com seus endereços associados
        // Vamos escrever os dados no arquivo de saída
        String arquivoSaida = "saida.csv";

        try (FileWriter fw = new FileWriter(arquivoSaida)) {
            fw.append("codigo_pessoa;nome;nome_rua;cidade\n");

            for (Endereco endereco : listaEnderecos) {
                String codigoPessoa = endereco.getPessoa().getCodigo();
                String nomePessoa = endereco.getPessoa().getNome();
                String nomeRua = endereco.getNomeRua();
                String cidade = endereco.getCidade();

                fw.append(codigoPessoa)
                        .append(";")
                        .append(nomePessoa)
                        .append(";")
                        .append(nomeRua)
                        .append(";")
                        .append(cidade)
                        .append("\n");
            }

            System.out.println("Arquivo CSV de saída criado com sucesso: " + arquivoSaida);
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo " + arquivoSaida + ": " + e.getMessage());
        }
    }
}
