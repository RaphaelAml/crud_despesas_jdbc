package my.project.model;

import my.project.dao.DespesaDAO;

import java.time.LocalDate;
import java.util.Optional;

public class AtualizarDespesa {

    public static void main(String[] args) {

        DespesaDAO dao = new DespesaDAO();
        Optional<Despesa> despesaOptional = dao.findById(11L);

        Despesa despesa = despesaOptional.get();
        System.out.println(despesa.getId());
        System.out.println(despesa.getDescricao());
        System.out.println(despesa.getData());
        System.out.println(despesa.getValor());
        System.out.println(despesa.getCategoria());

        /*
        despesa.setDescricao("Gasto com combustivel");
        despesa.setData(LocalDate.of(2023, 8, 1));
        despesa.setValor(185);
        despesa.setCategoria(Categoria.OUTROS);

        dao.update(despesa);
        */
    }
}
