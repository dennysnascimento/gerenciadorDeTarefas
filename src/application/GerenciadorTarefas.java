package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Um sistema simples de gerenciamento de tarefas.
 */
public class GerenciadorTarefas {

    private final List<String> listaDeTarefas;

    /**
     * Cria um novo GerenciadorTarefas com uma lista vazia de tarefas.
     */
    public GerenciadorTarefas() {
        listaDeTarefas = new ArrayList<>();
    }

    /**
     * Adiciona uma nova tarefa à lista de tarefas.
     *
     * @param tarefa A descrição da tarefa a ser adicionada.
     */
    public void adicionarTarefa(String tarefa) {
        listaDeTarefas.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    /**
     * Lista todas as tarefas armazenadas.
     * Se não houver tarefas, imprime uma mensagem informando.
     */
    public void listarTarefas() {
        if (listaDeTarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            System.out.println("Lista de Tarefas:");
            for (Integer i = 0; i < listaDeTarefas.size(); i++) {
                System.out.println(i + ". " + listaDeTarefas.get(i));
            }
        }
    }

    /**
     * Marca uma tarefa como concluída e a remove da lista de tarefas.
     *
     * @param indice O índice da tarefa a ser concluída e removida.
     */
    public void concluirTarefa(Integer indice) {
        if (indice >= 0 && indice < listaDeTarefas.size()) {
            listaDeTarefas.remove(indice);
            System.out.println("Tarefa concluída e removida da lista.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Concluir tarefa");
            System.out.println("9. Sair do programa.");

            try {
                Integer escolha = scanner.nextInt();

                if (escolha == 1) {
                    System.out.print("Digite a descrição da tarefa: ");
                    String descricaoTarefa = scanner.next();
                    gerenciador.adicionarTarefa(descricaoTarefa);
                } else if (escolha == 2) {
                    gerenciador.listarTarefas();
                } else if (escolha == 3) {
                    System.out.print("Digite o índice da tarefa concluída: ");
                    Integer indiceTarefaConcluida = scanner.nextInt();
                    gerenciador.concluirTarefa(indiceTarefaConcluida);
                } else if (escolha == 9) {
                    System.out.print("Saindo do programa.");
                    return;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Digite um número.");
                scanner.next();
            }
        }
    }
}