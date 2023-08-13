package my.project.model;

import my.project.dao.DespesaDAO;

public class RemoverDespesa {

    public static void main(String[] args) {

        DespesaDAO dao = new DespesaDAO();
        dao.delete(12L);
    }

}
