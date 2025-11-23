package View;

import Controller.PacienteController;

import java.util.Scanner;

public class PacienteView {

    private Scanner sc = new Scanner(System.in);
    private PacienteController controller = new PacienteController();

    public void menuCadastro() {

        System.out.println("=== CADASTRO DE PACIENTE ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Data de Nascimento: ");
        String data = sc.nextLine();

        controller.cadastrar(nome, cpf, telefone, data);
    }
}
