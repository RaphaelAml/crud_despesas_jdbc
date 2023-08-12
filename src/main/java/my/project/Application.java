package my.project;

import my.project.dao.DespesaDAO;
import my.project.model.Categoria;
import my.project.model.Despesa;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {

        DespesaDAO dao = new DespesaDAO();
        Despesa despesa = new Despesa();
        despesa.setDescricao("Barzinho");
        despesa.setCategoria(Categoria.OUTROS);
        despesa.setValor(95);
        despesa.setData(LocalDate.of(2023, 8, 15));


        Despesa despesaInserida = dao.save(despesa);
        System.out.println("Foi adicionada a despesa com id: " + despesaInserida.getId());



    }
}
