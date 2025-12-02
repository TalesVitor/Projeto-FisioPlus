package Service;

import DAO.PacienteDAO;
import DAO.FisioterapeutaDAO;
import DAO.PacoteDAO;
import DAO.FinanceiroDAO;
import DAO.AgendamentoDAO;

import Model.Pacote;
import Model.TransacaoFinanceira;
import Model.Agendamento;

import Enums.MetodoPagamento;
import Enums.PacoteStatus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RelatorioService {

    private PacienteDAO pacienteDao = new PacienteDAO();
    private FisioterapeutaDAO fisioDao = new FisioterapeutaDAO();
    private PacoteDAO pacoteDao = new PacoteDAO();
    private FinanceiroDAO financeiroDao = new FinanceiroDAO();
    private AgendamentoDAO agendamentoDao = new AgendamentoDAO();

    // CONTAGEM SIMPLES
    public int totalPacientes() {
        return pacienteDao.listar().size();
    }

    public int totalFisioterapeutas() {
        return fisioDao.listar().size();
    }

    // PACOTES: quantos ativos, encerrados, sessões restantes total
    public Map<String, Object> resumoPacotes() {
        List<Pacote> pacotes = pacoteDao.listar();
        long ativos = pacotes.stream().filter(p -> p.getStatus() == PacoteStatus.ATIVO).count();
        long encerrados = pacotes.stream().filter(p -> p.getStatus() == PacoteStatus.ENCERRADO).count();
        int sessoesRestantes = pacotes.stream()
                .mapToInt(p -> Math.max(0, p.getTotalSessoes() - p.getSessoesUsadas()))
                .sum();

        Map<String, Object> m = new HashMap<>();
        m.put("totalPacotes", pacotes.size());
        m.put("ativos", (int) ativos);
        m.put("encerrados", (int) encerrados);
        m.put("sessoesRestantes", sessoesRestantes);
        return m;
    }

    // FINANCEIRO: totals
    public double totalEntradas() {
        return financeiroDao.listar().stream()
                .filter(t -> t.getTipo().name().equals("ENTRADA"))
                .mapToDouble(t -> t.getValor())
                .sum();
    }

    public double totalSaidas() {
        return financeiroDao.listar().stream()
                .filter(t -> t.getTipo().name().equals("SAIDA"))
                .mapToDouble(t -> t.getValor())
                .sum();
    }

    // total por método de pagamento
    public Map<MetodoPagamento, Double> totalPorMetodo() {
        List<TransacaoFinanceira> lista = financeiroDao.listar();
        Map<MetodoPagamento, Double> mapa = new EnumMap<>(MetodoPagamento.class);
        for (MetodoPagamento m : MetodoPagamento.values()) mapa.put(m, 0.0);

        for (TransacaoFinanceira t : lista) {
            mapa.put(t.getMetodo(), mapa.getOrDefault(t.getMetodo(), 0.0) + t.getValor());
        }
        return mapa;
    }

    // Totais por mês (yyyy-MM)
    public Map<String, Double> totalEntradasPorMes() {
        return agruparPorMes(true);
    }

    public Map<String, Double> totalSaidasPorMes() {
        return agruparPorMes(false);
    }

    private Map<String, Double> agruparPorMes(boolean entradas) {
        List<TransacaoFinanceira> lista = financeiroDao.listar();

        Map<String, Double> mapa = new TreeMap<>(); // ordenado por chave (yyyy-MM)
        for (TransacaoFinanceira t : lista) {
            boolean isEntrada = t.getTipo().name().equals("ENTRADA");
            if (isEntrada != entradas) continue;

            String data = t.getData();
            try {
                LocalDate ld = LocalDate.parse(data);
                String chave = String.format("%04d-%02d", ld.getYear(), ld.getMonthValue());
                mapa.put(chave, mapa.getOrDefault(chave, 0.0) + t.getValor());
            } catch (DateTimeParseException ex) {
                // pula datas inválidas
            }
        }
        return mapa;
    }

    // AGENDAMENTOS: próximas sessões, faltas, concluídas
    public Map<String, Object> resumoAgendamentos() {
        List<Agendamento> lista = agendamentoDao.listar();
        LocalDate hoje = LocalDate.now();

        long futuras = lista.stream()
                .filter(a -> {
                    try {
                        LocalDate d = LocalDate.parse(a.getData());
                        return d.isAfter(hoje) || d.isEqual(hoje);
                    } catch (DateTimeParseException ex) {
                        return false;
                    }
                }).count();

        long concluido = lista.stream().filter(a -> a.getStatus().equalsIgnoreCase("Concluído") || a.getStatus().equalsIgnoreCase("Concluido")).count();
        long faltas = lista.stream().filter(a -> a.getStatusPresenca() != null && a.getStatusPresenca().toString().equalsIgnoreCase("FALTOU")).count();

        Map<String, Object> m = new HashMap<>();
        m.put("totalAgendamentos", lista.size());
        m.put("futurasOuHoje", (int) futuras);
        m.put("concluidas", (int) concluido);
        m.put("faltas", (int) faltas);
        return m;
    }

    // lista transações (bruto)
    public List<TransacaoFinanceira> todasTransacoes() {
        return financeiroDao.listar();
    }

    // lista pacotes
    public List<Pacote> todosPacotes() {
        return pacoteDao.listar();
    }
}
