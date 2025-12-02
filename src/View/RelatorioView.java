package View;

import Controller.RelatorioController;
import Enums.MetodoPagamento;

import java.util.Map;

public class RelatorioView {

    private RelatorioController controller = new RelatorioController();

    public void menuRelatorios() {
        System.out.println("\n=== RELATÓRIOS GERENCIAIS (Com totais) ===");

        System.out.println("\n--- Clientes e Profissionais ---");
        System.out.println("Total pacientes: " + controller.totalPacientes());
        System.out.println("Total fisioterapeutas: " + controller.totalFisioterapeutas());

        System.out.println("\n--- Pacotes ---");
        Map<String,Object> rp = controller.resumoPacotes();
        System.out.println("Total pacotes: " + rp.get("totalPacotes"));
        System.out.println("Ativos: " + rp.get("ativos"));
        System.out.println("Encerrados: " + rp.get("encerrados"));
        System.out.println("Sessões restantes (total): " + rp.get("sessoesRestantes"));

        System.out.println("\n--- Agendamentos ---");
        Map<String,Object> ra = controller.resumoAgendamentos();
        System.out.println("Total agendamentos: " + ra.get("totalAgendamentos"));
        System.out.println("Futuras/Hoje: " + ra.get("futurasOuHoje"));
        System.out.println("Concluídas: " + ra.get("concluidas"));
        System.out.println("Faltas: " + ra.get("faltas"));

        System.out.println("\n--- Financeiro (totais) ---");
        System.out.println("Total Entradas: " + controller.totalEntradas());
        System.out.println("Total Saídas: " + controller.totalSaidas());
        System.out.println("Saldo: " + (controller.totalEntradas() - controller.totalSaidas()));

        System.out.println("\nTotal por método:");
        Map<?,Double> porMetodo = controller.totalPorMetodo();
        for (MetodoPagamento m : MetodoPagamento.values()) {
            Double v = porMetodo.getOrDefault(m, 0.0);
            System.out.println("  " + m + ": " + v);
        }

        System.out.println("\nEntradas por mês:");
        controller.entradasPorMes().forEach((k,v) -> System.out.println(k + " -> " + v));

        System.out.println("\nSaídas por mês:");
        controller.saidasPorMes().forEach((k,v) -> System.out.println(k + " -> " + v));

        System.out.println("\n--- Relatório completo de transações ---");
        controller.todasTransacoes().forEach(t -> System.out.println(t + "\n----------------"));
    }
}
