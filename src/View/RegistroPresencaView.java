package View;

import Controller.RegistroPresencaController;

import java.util.Scanner;

public class RegistroPresencaView {

    private Scanner sc = new Scanner(System.in);
    private RegistroPresencaController controller = new RegistroPresencaController();

    public void menuRegistro() {
        System.out.println("=== REGISTRO DE PRESENÇA / FALTAS ===");

        // listar agendamentos
        controller.listarAgendamentos();

        System.out.print("\nDigite o ID do agendamento para registrar presença (ou 0 para voltar): ");
        String id = sc.nextLine();
        if (id.equals("0")) return;

        System.out.println("1 - Marcar PRESENTE");
        System.out.println("2 - Marcar FALTOU");
        System.out.print("Escolha: ");
        String op = sc.nextLine();

        if (op.equals("1")) {
            controller.registrarPresenca(id, "PRESENTE");
        } else if (op.equals("2")) {
            controller.registrarPresenca(id, "FALTOU");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
