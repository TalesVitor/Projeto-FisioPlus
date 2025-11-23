package Controller;

import Model.Fisioterapeuta;
import Service.FisioterapeutaService;

public class FisioterapeutaController {

    private FisioterapeutaService service = new FisioterapeutaService();

    public void cadastrar(String nome, String cpf, String telefone, String crefito) {

        Fisioterapeuta f = new Fisioterapeuta(nome, cpf, telefone, crefito);

        try {
            service.cadastrar(f);
            System.out.println("Fisioterapeuta cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

