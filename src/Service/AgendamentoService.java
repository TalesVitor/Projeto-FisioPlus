package Service;

import DAO.AgendamentoDAO;
import DAO.PacienteDAO;
import DAO.FisioterapeutaDAO;
import Model.Agendamento;

import java.util.List;
import java.util.UUID;

public class AgendamentoService {

    private AgendamentoDAO agDao = new AgendamentoDAO();
    private PacienteDAO pacienteDao = new PacienteDAO();
    private FisioterapeutaDAO fisioDao = new FisioterapeutaDAO();

    public void agendar(String pacienteCpf, String fisioterapeutaCpf, String tratamentoNome,
                        String data, String hora) throws Exception {

        // valida existência do paciente
        if (pacienteDao.buscarPorCPF(pacienteCpf) == null) {
            throw new Exception("Paciente não encontrado (CPF inválido).");
        }

        // valida existência do fisioterapeuta
        if (fisioDao.buscarPorCPF(fisioterapeutaCpf) == null) {
            throw new Exception("Fisioterapeuta não encontrado (CPF inválido).");
        }

        // checar conflito: mesmo fisioterapeuta, mesma data e hora, status != Cancelado
        List<Agendamento> todos = agDao.listar();
        for (Agendamento a : todos) {
            if (a.getFisioterapeutaCpf().equals(fisioterapeutaCpf)
                    && a.getData().equals(data)
                    && a.getHora().equals(hora)
                    && !a.getStatus().equalsIgnoreCase("Cancelado")) {
                throw new Exception("Conflito: fisioterapeuta já tem agendamento nesse horário.");
            }
        }

        // gerar id
        String id = UUID.randomUUID().toString();

        Agendamento novo = new Agendamento(id, pacienteCpf, fisioterapeutaCpf, tratamentoNome, data, hora, "Agendado");
        agDao.salvar(novo);
    }

    public List<Agendamento> listar() {
        return agDao.listar();
    }

    public Agendamento buscarPorId(String id) {
        return agDao.buscarPorId(id);
    }
}

