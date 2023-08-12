package my.project;

import my.project.dao.DespesaDAO;
import my.project.model.Categoria;
import my.project.model.Despesa;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {

        DespesaDAO dao = new DespesaDAO();
        Despesa despesa = new Despesa();
        despesa.setDescricao("Pagamento de aluguel");
        despesa.setCategoria(Categoria.MORADIA);
        despesa.setValor(1200);
        despesa.setData(LocalDate.of(2023, 8, 12));

        dao.save(despesa);


    }
}
