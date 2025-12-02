package Service;

import DAO.EvolucaoDAO;
import Model.Evolucao;

import java.util.List;
import java.util.UUID;

public class EvolucaoService {

    private EvolucaoDAO dao = new EvolucaoDAO();

    public void cadastrar(String pacienteCpf, String data, String descricao) throws Exception {
        if (pacienteCpf == null || pacienteCpf.isEmpty()) throw new Exception("CPF do paciente é obrigatório.");
        if (descricao == null || descricao.isEmpty()) throw new Exception("Descrição da evolução é obrigatória.");

        String id = UUID.randomUUID().toString();
        Evolucao e = new Evolucao(id, pacienteCpf, data, descricao);
        dao.salvar(e);
    }

    public List<Model.Evolucao> listarPorPaciente(String cpf) {
        return dao.listarPorPaciente(cpf);
    }
}
