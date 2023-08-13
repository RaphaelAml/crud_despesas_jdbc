package my.project;

import my.project.dao.DespesaDAO;
import my.project.model.Categoria;
import my.project.model.Despesa;

import java.util.List;

public class ListarDespesas {

    public static void main(String[] args) {


        DespesaDAO dao = new DespesaDAO();
        /*
        Despesa despesa = new Despesa();
        despesa.setDescricao("Viagem de uber");
        despesa.setCategoria(Categoria.TRANSPORTE);
        despesa.setValor(32);
        despesa.setData(LocalDate.of(2023, 8, 16));


        Despesa despesaInserida = dao.save(despesa);
        System.out.println("Foi adicionada a despesa com id: " + despesaInserida.getId());
        */

        /*
        List<Despesa> despesas = dao.findAll();

        for (Despesa despesa : despesas) {
            System.out.println("Id: " + despesa.getId());
            System.out.println("Descricao: " + despesa.getDescricao());
            System.out.println("Valor: " + despesa.getValor());

            System.out.println("=============================================================");
        }
        */

        /*
        Optional<Despesa> despesaOptional = dao.findById(2L);
        despesaOptional.ifPresent(despesa -> {
            System.out.println("Id: " + despesa.getId());
            System.out.println("Descricao: " + despesa.getDescricao());
            System.out.println("Valor: " + despesa.getValor());
        });
        */

        List<Despesa> despesas = dao.findByCategoria(Categoria.TRANSPORTE);
        for (Despesa despesa : despesas) {
            System.out.println("Id: " + despesa.getId());
            System.out.println("Descricao: " + despesa.getDescricao());
            System.out.println("Categoria: " + despesa.getCategoria());
            System.out.println("Valor: " + despesa.getValor());

            System.out.println("=============================================================");
        }
    }
}
