//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import View.PacienteView;
import View.FisioterapeutaView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PacienteView pacienteView = new PacienteView();
        FisioterapeutaView fisioView = new FisioterapeutaView();

        int opcao;

        do {
            System.out.println("=======================================");
            System.out.println("      SISTEMA DE FISIOPLUS");
            System.out.println("=======================================");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Cadastrar Fisioterapeuta");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    pacienteView.menuCadastro();
                    break;

                case 2:
                    fisioView.menuCadastro();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }
}
