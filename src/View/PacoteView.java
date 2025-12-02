package View;

import Controller.PacoteController;

import java.util.Scanner;

public class PacoteView {

    private Scanner sc = new Scanner(System.in);
    private PacoteController controller = new PacoteController();

    public void menuCadastro() {
        System.out.println("=== CADASTRO DE PACOTE DE SESSÕES ===");
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();

        System.out.print("Nome do tratamento (opcional): ");
        String tratamento = sc.nextLine();

        System.out.print("Total de sessões: ");
        int total = Integer.parseInt(sc.nextLine());

        System.out.print("Valor total do pacote: ");
        double valor = Double.parseDouble(sc.nextLine());

        controller.cadastrar(cpf, tratamento.isEmpty()?null:tratamento, total, valor);
    }

    public void menuListar() {
        controller.listar();
    }

    public void menuListarPorPaciente() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        controller.listarPorPaciente(cpf);
    }
}
