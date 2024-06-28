import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Grupos {

    public static void main(String[] args) {
        // Caminho para o arquivo de entrada
        String caminhoArquivo = "C:\\Users\\Marti\\Desktop\\javaRevisao\\PossiveisExercicios1\\grupos-tabulados.txt";

        // Mapa para armazenar os totais por grupo
        Map<String, Integer> totaisPorGrupo = new HashMap<>();
        int totalGeral = 0;

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
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

        // Imprimir os totais por grupo
        System.out.println("Totais por grupo:");
        for (Map.Entry<String, Integer> entrada : totaisPorGrupo.entrySet()) {
            System.out.println("Grupo: " + entrada.getKey() + ", Total: " + entrada.getValue());
        }

        // Imprimir o total geral
        System.out.println("Total geral de todos os registros: " + totalGeral);
    }
}
