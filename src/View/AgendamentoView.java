package View;

import Controller.AgendamentoController;

import java.util.Scanner;

public class AgendamentoView {

    private Scanner sc = new Scanner(System.in);
    private AgendamentoController controller = new AgendamentoController();

    public void menuCadastro() {
        System.out.println("=== AGENDAMENTO DE SESSÃO ===");

        System.out.print("CPF do paciente: ");
        String cpfPac = sc.nextLine();

        System.out.print("CPF do fisioterapeuta: ");
        String cpfFisio = sc.nextLine();

        System.out.print("Nome do tratamento: ");
        String tratamento = sc.nextLine();

        System.out.print("Data (yyyy-MM-dd): ");
        String data = sc.nextLine();

        System.out.print("Hora (HH:mm): ");
        String hora = sc.nextLine();

        controller.agendar(cpfPac, cpfFisio, tratamento, data, hora);
    }

    public void menuListar() {
        controller.listar();
    }
}

