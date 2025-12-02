package View;

import Controller.FinanceiroController;

import java.util.Scanner;

public class FinanceiroView {

    private Scanner sc = new Scanner(System.in);
    private FinanceiroController controller = new FinanceiroController();

    public void menuFinanceiro() {
        int op;
        do {
            System.out.println("\n=== FINANCEIRO ===");
            System.out.println("1 - Registrar Entrada (pagamento)");
            System.out.println("2 - Registrar Saída (despesa)");
            System.out.println("3 - Listar todas transações");
            System.out.println("4 - Resumo geral");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    System.out.print("Método (PIX, DEBITO, CREDITO, DINHEIRO, OUTRO): ");
                    String metodo = sc.nextLine();
                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(sc.nextLine());
                    System.out.print("Data (yyyy-MM-dd): ");
                    String data = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();
                    System.out.print("CPF do paciente (opcional): ");
                    String cpf = sc.nextLine();
                    controller.registrarEntrada(metodo, valor, data, desc, cpf);
                    break;
                case 2:
                    System.out.print("Método (PIX, DEBITO, CREDITO, DINHEIRO, OUTRO): ");
                    String metodo2 = sc.nextLine();
                    System.out.print("Valor: ");
                    double valor2 = Double.parseDouble(sc.nextLine());
                    System.out.print("Data (yyyy-MM-dd): ");
                    String data2 = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc2 = sc.nextLine();
                    controller.registrarSaida(metodo2, valor2, data2, desc2);
                    break;
                case 3:
                    controller.listarTodas();
                    break;
                case 4:
                    controller.resumoGeral();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (op != 0);
    }
}
