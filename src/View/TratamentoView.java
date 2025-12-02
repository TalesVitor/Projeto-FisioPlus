package View;

import Controller.TratamentoController;

import java.util.Scanner;

public class TratamentoView {

    private Scanner sc = new Scanner(System.in);
    private TratamentoController controller = new TratamentoController();

    public void menuCadastro() {
        System.out.println("=== CADASTRO DE TRATAMENTO ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("Duração (minutos): ");
        int duracao = Integer.parseInt(sc.nextLine());

        System.out.print("Valor (por sessão): ");
        double valor = Double.parseDouble(sc.nextLine());

        controller.cadastrar(nome, descricao, duracao, valor);
    }

    public void menuListar() {
        controller.listar();
    }
}
