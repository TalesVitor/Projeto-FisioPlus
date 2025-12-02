package Service;

import DAO.AgendamentoDAO;
import Model.Agendamento;
import Enums.StatusPresenca;
import Service.PacoteService;

public class RegistroPresencaService {

    private AgendamentoDAO agDao = new AgendamentoDAO();
    private PacoteService pacoteService = new PacoteService();

    public void registrarPresenca(String idAgendamento, String novoStatusPresenca) throws Exception {
        Agendamento a = agDao.buscarPorId(idAgendamento);
        if (a == null) throw new Exception("Agendamento não encontrado (ID inválido).");

        try {
            StatusPresenca status = StatusPresenca.valueOf(novoStatusPresenca.toUpperCase());
            a.setStatusPresenca(status);
            agDao.atualizar(a);

            if (status == StatusPresenca.PRESENTE) {
                boolean descontou = pacoteService.utilizarSessaoDoPaciente(a.getPacienteCpf());
                if (descontou) {
                    System.out.println("Sessão descontada do pacote do paciente (se existia um pacote ativo).");
                } else {
                    System.out.println("Paciente não possui pacote ativo; não foi possível descontar sessão.");
                }
            }

        } catch (IllegalArgumentException e) {
            throw new Exception("Status inválido. Use: PENDENTE, PRESENTE, FALTOU");
        }
    }
}
