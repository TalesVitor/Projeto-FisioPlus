import View.PacienteView;
import View.FisioterapeutaView;
import View.TratamentoView;
import View.AgendamentoView;
import View.RegistroPresencaView;
import View.EvolucaoView;
import View.PacoteView;
import View.FinanceiroView;
import View.RelatorioView;

import Service.PacienteService;
import Service.FisioterapeutaService;
import Service.TratamentoService;
import Service.PacoteService;
import Service.FinanceiroService;
import Service.AgendamentoService;
import Service.RegistroPresencaService;
import Service.EvolucaoService;

import Model.Tratamento;

import Enums.PacoteStatus;
import Enums.MetodoPagamento;

import java.time.LocalDate;
import java.util.UUID;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PacienteView pacienteView = new PacienteView();
        FisioterapeutaView fisioView = new FisioterapeutaView();
        TratamentoView tratamentoView = new TratamentoView();
        AgendamentoView agendamentoView = new AgendamentoView();
        RegistroPresencaView presencaView = new RegistroPresencaView();
        EvolucaoView evolucaoView = new EvolucaoView();
        PacoteView pacoteView = new PacoteView();
        FinanceiroView financeiroView = new FinanceiroView();
        RelatorioView relatorioView = new RelatorioView();

        int opcao;

        do {
            System.out.println("=======================================");
            System.out.println("      SISTEMA DE FISIOPLUS");
            System.out.println("=======================================");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Cadastrar Fisioterapeuta");
            System.out.println("3. Cadastrar Tratamento");
            System.out.println("4. Agendar Sessão");
            System.out.println("5. Registrar Presença/Falta");
            System.out.println("6. Registrar Evolução de Paciente");
            System.out.println("7. Listar Evoluções de Paciente");
            System.out.println("8. Gerenciar Pacotes de Sessões");
            System.out.println("9. Controle Financeiro");
            System.out.println("10. Relatórios Gerenciais");
            System.out.println("11. Executar Simulação automática");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    pacienteView.menuCadastro();
                    break;
                case 2:
                    fisioView.menuCadastro();
                    break;
                case 3:
                    tratamentoView.menuCadastro();
                    break;
                case 4:
                    agendamentoView.menuCadastro();
                    break;
                case 5:
                    presencaView.menuRegistro();
                    break;
                case 6:
                    evolucaoView.menuCadastro();
                    break;
                case 7:
                    evolucaoView.menuListar();
                    break;
                case 8:
                    // reutiliza submenu já usado antes (PacoteView)
                    int op;
                    do {
                        System.out.println("\n--- PACOTES ---");
                        System.out.println("1 - Cadastrar pacote");
                        System.out.println("2 - Listar todos pacotes");
                        System.out.println("3 - Listar pacotes por paciente");
                        System.out.println("0 - Voltar");
                        System.out.print("Opção: ");
                        op = Integer.parseInt(sc.nextLine());
                        switch (op) {
                            case 1:
                                pacoteView.menuCadastro();
                                break;
                            case 2:
                                pacoteView.menuListar();
                                break;
                            case 3:
                                pacoteView.menuListarPorPaciente();
                                break;
                        }
                    } while (op != 0);
                    break;
                case 9:
                    financeiroView.menuFinanceiro();
                    break;
                case 10:
                    relatorioView.menuRelatorios();
                    break;
                case 11:
                    runSimulation();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    // Simulação que cria dados automaticamente e gera relatórios (para entrega/demo)
    private static void runSimulation() {
        try {
            System.out.println("\n=== INICIANDO SIMULAÇÃO AUTOMÁTICA ===");

            // serviços
            PacienteService pService = new PacienteService();
            FisioterapeutaService fService = new FisioterapeutaService();
            TratamentoService tService = new TratamentoService();
            PacoteService pacoteService = new PacoteService();
            FinanceiroService finService = new FinanceiroService();
            AgendamentoService agService = new AgendamentoService();
            RegistroPresencaService presencaService = new RegistroPresencaService();
            EvolucaoService evolService = new EvolucaoService();

            // 1) cria paciente
            String cpfPaciente = "11122233344";
            pService.cadastrar(new Model.Paciente("João Silva", cpfPaciente, "11999990000", "1990-01-01"));
            System.out.println("Paciente criado: " + cpfPaciente);

            // 2) cria fisioterapeuta
            String cpfFisio = "55566677788";
            fService.cadastrar(new Model.Fisioterapeuta("Dra. Ana", cpfFisio, "11988880000", "CREF1234"));
            System.out.println("Fisioterapeuta criado: " + cpfFisio);

            // 3) cria tratamento
            tService.cadastrar(new Tratamento("Fisio Motora", "Tratamento motor", 60, 80.0));
            System.out.println("Tratamento criado: Fisio Motora");

            // 4) cria pacote para paciente
            pacoteService.cadastrar(cpfPaciente, "Fisio Motora", 5, 300.0);
            System.out.println("Pacote de 5 sessões criado para " + cpfPaciente);

            // 5) registra pagamento do pacote
            finService.registrarEntrada(Enums.MetodoPagamento.PIX, 300.0, LocalDate.now().toString(), "Compra pacote 5 sessões", cpfPaciente);
            System.out.println("Pagamento registrado (PIX) 300.0");

            // 6) agendar sessão (hoje)
            String hoje = LocalDate.now().toString();
            agService.agendar(cpfPaciente, cpfFisio, "Fisio Motora", hoje, "14:00");
            System.out.println("Agendamento criado para hoje 14:00");

            // 7) marcar presença (usa PacoteService automaticamente)
            // buscar agendamento id (pega último)
            String idAg = agService.listar().get(agService.listar().size()-1).getId();
            presencaService.registrarPresenca(idAg, "PRESENTE");
            System.out.println("Presença registrada e sessão descontada do pacote (se ativo).");

            // 8) registrar evolução
            evolService.cadastrar(cpfPaciente, hoje, "Paciente apresentou melhora de amplitude articular.");
            System.out.println("Evolução registrada.");

            // 9) gerar relatórios
            System.out.println("\n--- RELATÓRIOS PÓS-SIMULAÇÃO ---");
            RelatorioView rv = new RelatorioView();
            rv.menuRelatorios();

            System.out.println("\n=== SIMULAÇÃO FINALIZADA ===\n");

        } catch (Exception e) {
            System.out.println("Erro durante a simulação: " + e.getMessage());
        }
    }
}
