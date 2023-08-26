package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorTarefasComMySQL {

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/tbtarefas";
        String username = "root";
        String password = "123$";

        try (Connection conexao = DriverManager.getConnection(url, username, password)) {
            while (true) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Adicionar tarefa");
                System.out.println("2. Listar tarefas");
                System.out.println("3. Concluir tarefa");
                System.out.println("9. Sair do programa.");

                try {
                    int escolha = scanner.nextInt();

                    switch (escolha) {
                        case 1 -> {
                            System.out.print("Digite a descrição da tarefa: ");
                            String descricaoTarefa = scanner.next();
                            gerenciador.adicionarTarefa(descricaoTarefa);
                            String sql = "INSERT INTO tarefas (descricao) VALUES (?)";
                            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                                preparedStatement.setString(1, descricaoTarefa);
                                preparedStatement.executeUpdate();
                            }
                        }

                        case 2 -> gerenciador.listarTarefas();

                        case 3 -> {
                            System.out.print("Digite o índice da tarefa a concluir: ");
                            int indiceTarefaConcluida = scanner.nextInt();
                            gerenciador.concluirTarefa(indiceTarefaConcluida);
                            String deleteSql = "DELETE FROM tarefas WHERE id = ?";
                            try (PreparedStatement excluir = conexao.prepareStatement(deleteSql)) {
                                excluir.setInt(1, indiceTarefaConcluida + 1);
                                excluir.executeUpdate();
                            }
                        }

                        case 9 -> {
                            System.out.println("Saindo do programa.");
                            return;
                        }


                        default -> System.out.println("Opção inválida.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Digite um número.");
                    scanner.next();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados.");
            e.printStackTrace();
        }
    }
}
