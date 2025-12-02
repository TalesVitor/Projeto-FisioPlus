package Service;

import DAO.TratamentoDAO;
import Model.Tratamento;

import java.util.List;

public class TratamentoService {

    private TratamentoDAO dao = new TratamentoDAO();

    public void cadastrar(Tratamento t) throws Exception {
        if (t.getNome() == null || t.getNome().isEmpty()) {
            throw new Exception("Nome do tratamento não pode ser vazio.");
        }
        if (dao.buscarPorNome(t.getNome()) != null) {
            throw new Exception("Já existe tratamento com esse nome.");
        }
        dao.salvar(t);
    }

    public List<Tratamento> listar() {
        return dao.listar();
    }

    public Tratamento buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }
}

