package Service;

import DAO.FisioterapeutaDAO;
import Model.Fisioterapeuta;

public class FisioterapeutaService {

    private FisioterapeutaDAO dao = new FisioterapeutaDAO();

    public void cadastrar(Fisioterapeuta f) throws Exception {

        if (dao.buscarPorCPF(f.getCpf()) != null) {
            throw new Exception("CPF já cadastrado para outro fisioterapeuta.");
        }

        if (f.getCrefito().isEmpty()) {
            throw new Exception("CREFITO não pode ser vazio.");
        }

        dao.salvar(f);
    }
}

