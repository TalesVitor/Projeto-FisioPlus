package Controller;

import Service.RegistroPresencaService;
import DAO.AgendamentoDAO;
import Model.Agendamento;

import java.util.List;

public class RegistroPresencaController {

    private RegistroPresencaService service = new RegistroPresencaService();
    private AgendamentoDAO agDao = new AgendamentoDAO();

    public void listarAgendamentos() {
        List<Agendamento> lista = agDao.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
            return;
        }
        for (Agendamento a : lista) {
            System.out.println("----------------------");
            System.out.println(a);
        }
    }

    public void registrarPresenca(String idAgendamento, String statusPresenca) {
        try {
            service.registrarPresenca(idAgendamento, statusPresenca);
            System.out.println("Presença registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao registrar presença: " + e.getMessage());
        }
    }
}

