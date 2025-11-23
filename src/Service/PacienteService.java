package Service;

import DAO.PacienteDAO;
import Model.Paciente;

public class PacienteService {

    private PacienteDAO dao = new PacienteDAO();

    public void cadastrar(Paciente p) throws Exception {

        if (dao.buscarPorCPF(p.getCpf()) != null) {
            throw new Exception("Paciente já cadastrado com esse CPF.");
        }

        if (p.getNome().isEmpty()) {
            throw new Exception("Nome não pode ser vazio.");
        }

        dao.salvar(p);
    }
}

