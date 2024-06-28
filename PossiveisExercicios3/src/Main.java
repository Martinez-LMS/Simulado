import models.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /*NOME: Leonardo Martinez dos santos
     * RGM: 34456007
     */


    public static void main(String[] args) {
        String caminhoArquivo = "C:\\Users\\Marti\\Desktop\\javaRevisao\\PossiveisExercicios3\\alunos.csv";
        String caminhoRelatorio = "C:\\Users\\Marti\\Desktop\\javaRevisao\\PossiveisExercicios3\\relatorio-alunos.csv";

        List<Aluno> alunos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            // Ler cabeçalho
            leitor.readLine(); // Pula a primeira linha que contém os nomes das colunas

            // Ler cada linha do arquivo CSV
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");

                // Supondo que os dados sejam matrícula, nome, nota
                int matricula = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                // Substituir vírgula por ponto para garantir a leitura correta da nota
                double nota = Double.parseDouble(dados[2].trim().replace(",", "."));

                Aluno aluno = new Aluno(matricula, nome, nota);
                alunos.add(aluno);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato numérico ao processar o arquivo: " + e.getMessage());
            return;
        }

        // Processamento dos dados
        int quantidadeAlunos = alunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        for (Aluno aluno : alunos) {
            double nota = aluno.getNota();
            somaNotas += nota;

            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }

            if (nota < menorNota) {
                menorNota = nota;
            }

            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }

        double mediaGeral = somaNotas / quantidadeAlunos;

        // Gravar o relatório em arquivo
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoRelatorio))) {
            escritor.write("Quantidade de alunos na turma: " + quantidadeAlunos);
            escritor.newLine();
            escritor.write("Quantidade de alunos aprovados: " + aprovados);
            escritor.newLine();
            escritor.write("Quantidade de alunos reprovados: " + reprovados);
            escritor.newLine();
            escritor.write("Menor nota da turma: " + menorNota);
            escritor.newLine();
            escritor.write("Maior nota da turma: " + maiorNota);
            escritor.newLine();
            escritor.write("Média geral da turma: " + mediaGeral);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo de relatório: " + e.getMessage());
        }

        System.out.println("Relatório gerado com sucesso em: " + caminhoRelatorio);
    }
}
