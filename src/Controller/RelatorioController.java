package Controller;

import Service.RelatorioService;
import Model.TransacaoFinanceira;
import Model.Pacote;

import java.util.List;
import java.util.Map;

public class RelatorioController {

    private RelatorioService service = new RelatorioService();

    public int totalPacientes() { return service.totalPacientes(); }
    public int totalFisioterapeutas() { return service.totalFisioterapeutas(); }
    public Map<String,Object> resumoPacotes() { return service.resumoPacotes(); }

    public double totalEntradas() { return service.totalEntradas(); }
    public double totalSaidas() { return service.totalSaidas(); }
    public Map<?,Double> totalPorMetodo() { return service.totalPorMetodo(); }
    public Map<String,Double> entradasPorMes() { return service.totalEntradasPorMes(); }
    public Map<String,Double> saidasPorMes() { return service.totalSaidasPorMes(); }

    public Map<String,Object> resumoAgendamentos() { return service.resumoAgendamentos(); }

    public List<TransacaoFinanceira> todasTransacoes() { return service.todasTransacoes(); }

    public List<Pacote> todosPacotes() { return service.todosPacotes(); }
}
