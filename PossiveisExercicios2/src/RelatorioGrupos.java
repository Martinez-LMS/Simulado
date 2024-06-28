import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RelatorioGrupos {

    public static void main(String[] args) {
        String caminhoEntrada = "C:\\Users\\Marti\\Desktop\\javaRevisao\\PossiveisExercicios2\\grupos-tabulados2.txt";
        String caminhoSaida = "C:\\Users\\Marti\\Desktop\\javaRevisao\\PossiveisExercicios2\\relatorio-de-grupos.txt";

        // Mapa para armazenar os totais por grupo
        Map<String, Integer> totaisPorGrupo = new HashMap<>();
        // Variável para armazenar o total geral de todos os registros
        int totalGeral = 0;

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoEntrada))) {
            String linha;

            // Ler cada linha do arquivo
            while ((linha = leitor.readLine()) != null) {
                // Remover espaços em branco no início e fim da linha
                linha = linha.trim();

                // Verificar se a linha não está vazia
                if (!linha.isEmpty()) {
                    // Usar StringTokenizer para dividir a linha usando tabulação como delimitador
                    StringTokenizer tokenizer = new StringTokenizer(linha, "\t");

                    // Verificar se conseguimos obter exatamente dois tokens
                    if (tokenizer.countTokens() == 2) {
                        String grupo = tokenizer.nextToken().trim();
                        int valor;

                        try {
                            valor = Integer.parseInt(tokenizer.nextToken().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Formato de número inválido: " + linha);
                            continue; // Pular essa linha e continuar com a próxima
                        }

                        // Adicionar o valor ao total do grupo
                        totaisPorGrupo.put(grupo, totaisPorGrupo.getOrDefault(grupo, 0) + valor);

                        // Adicionar o valor ao total geral
                        totalGeral += valor;
                    } else {
                        System.out.println("Formato de linha inválido: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Houve um problema ao ler o arquivo.");
            System.out.println(e.getMessage());
        }

        // Imprimir os totais por grupo no console
        System.out.println("Totais por grupo:");
        for (Map.Entry<String, Integer> entrada : totaisPorGrupo.entrySet()) {
            System.out.println("Grupo: " + entrada.getKey() + ", Total: " + entrada.getValue());
        }

        // Imprimir o total geral no console
        System.out.println("Total geral de todos os registros: " + totalGeral);

        // Gravar os totais em um arquivo de relatório
        gravarRelatorio(caminhoSaida, totaisPorGrupo, totalGeral);
    }

    // Método para gravar o relatório em arquivo
    private static void gravarRelatorio(String caminhoArquivo, Map<String, Integer> totaisPorGrupo, int totalGeral) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Escrever os totais por grupo
            for (Map.Entry<String, Integer> entrada : totaisPorGrupo.entrySet()) {
                escritor.write("Grupo: " + entrada.getKey() + ", Total: " + entrada.getValue());
                escritor.newLine();
            }

            // Escrever o total geral de todos os registros
            escritor.write("Total geral de todos os registros: " + totalGeral);
        } catch (IOException e) {
            System.out.println("Houve um problema ao gravar o relatório.");
            System.out.println(e.getMessage());
        }
    }
}
