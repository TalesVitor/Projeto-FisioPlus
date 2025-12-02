package View;

import Controller.EvolucaoController;

import java.util.Scanner;

public class EvolucaoView {

    private Scanner sc = new Scanner(System.in);
    private EvolucaoController controller = new EvolucaoController();

    public void menuCadastro() {
        System.out.println("=== CADASTRO DE EVOLUÇÃO ===");

        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();

        System.out.print("Data (yyyy-MM-dd): ");
        String data = sc.nextLine();

        System.out.print("Descrição da evolução: ");
        String descricao = sc.nextLine();

        controller.cadastrar(cpf, data, descricao);
    }

    public void menuListar() {
        System.out.println("=== LISTAR EVOLUÇÕES POR PACIENTE ===");
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();

        controller.listarPorPaciente(cpf);
    }
}
