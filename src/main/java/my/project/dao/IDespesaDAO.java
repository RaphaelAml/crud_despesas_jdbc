package my.project.dao;

import my.project.model.Categoria;
import my.project.model.Despesa;

import java.util.List;
import java.util.Optional;

public interface IDespesaDAO {

    Despesa save(Despesa despesa);
    Despesa update(Despesa despesa);
    void delete(Long id);
    List<Despesa> findAll();
    Optional<Despesa> findByCategoria(Categoria categoria);

}
