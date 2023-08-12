package my.project.dao;

import my.project.infra.ConnectionFactory;
import my.project.model.Categoria;
import my.project.model.Despesa;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DespesaDAO implements IDespesaDAO {


    @Override
    public Despesa save(Despesa despesa) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Despesas (descricao, valor, data, categoria) VALUES (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setString(4, despesa.getCategoria().toString());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                Long generatedId = resultSet.getLong(1);
                despesa.setId(generatedId);
            } else {
                throw new SQLException("Failed to retrieve generated ID.");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return despesa;
    }


    @Override
    public Despesa update(Despesa despesa) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Despesa> findAll() {
        return null;
    }

    @Override
    public Optional<Despesa> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {
        return null;
    }
}
