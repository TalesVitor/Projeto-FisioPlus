package Service;

import DAO.PacoteDAO;
import Model.Pacote;
import Enums.PacoteStatus;

import java.util.List;
import java.util.UUID;

public class PacoteService {

    private PacoteDAO dao = new PacoteDAO();

    public void cadastrar(String pacienteCpf, String tratamentoNome, int totalSessoes, double valorTotal) throws Exception {
        if (pacienteCpf == null || pacienteCpf.isEmpty()) throw new Exception("CPF do paciente obrigatório.");
        if (totalSessoes <= 0) throw new Exception("Total de sessões inválido.");
        String id = UUID.randomUUID().toString();
        Pacote p = new Pacote(id, pacienteCpf, tratamentoNome, totalSessoes, 0, valorTotal, PacoteStatus.ATIVO);
        dao.salvar(p);
    }

    public List<Pacote> listar() { return dao.listar(); }

    public List<Pacote> listarPorPaciente(String cpf) { return dao.buscarPorPaciente(cpf); }

    public boolean utilizarSessaoDoPaciente(String cpf) {
        List<Pacote> pacotes = dao.buscarPorPaciente(cpf);
        for (Pacote p : pacotes) {
            if (p.getStatus() == PacoteStatus.ATIVO) {
                p.usarSessao();
                dao.atualizar(p);
                return true;
            }
        }
        return false;
    }
}
