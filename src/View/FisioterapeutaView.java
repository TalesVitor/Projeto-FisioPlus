package View;

import Controller.FisioterapeutaController;

import java.util.Scanner;

public class FisioterapeutaView {

    private Scanner sc = new Scanner(System.in);
    private FisioterapeutaController controller = new FisioterapeutaController();

    public void menuCadastro() {

        System.out.println("=== CADASTRO DE FISIOTERAPEUTA ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("CREFITO: ");
        String crefito = sc.nextLine();

        controller.cadastrar(nome, cpf, telefone, crefito);
    }
}

