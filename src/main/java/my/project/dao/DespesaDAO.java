package my.project.dao;

import my.project.infra.ConnectionFactory;
import my.project.model.Categoria;
import my.project.model.Despesa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE Despesas SET descricao = ?, valor = ?, data = ?, categoria = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, despesa.getDescricao());
            preparedStatement.setDouble(2, despesa.getValor());
            preparedStatement.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            preparedStatement.setString(4, despesa.getCategoria().toString());
            preparedStatement.setLong(5, despesa.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return despesa;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Despesa> findAll() {
        String sql = "SELECT id, descricao,data, valor, categoria FROM Despesas";
        List<Despesa> despesas = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong(1);
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                Despesa despesa = new Despesa(id, descricao, data, valor, categoria);
                despesas.add(despesa);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return despesas;
    }

    @Override
    public Optional<Despesa> findById(Long id) {

        String sql = "SELECT id, descricao,data, valor, categoria FROM Despesas WHERE id = ?";

        Despesa despesa = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong(1);
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria categoria = Categoria.valueOf(rs.getString("categoria"));

                despesa = new Despesa(pKey, descricao, data, valor, categoria);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(despesa);


    }


    @Override
    public List<Despesa> findByCategoria(Categoria categoria) {

        String sql = "SELECT id, descricao,data, valor, categoria FROM Despesas WHERE categoria = ?";
        List<Despesa> despesas = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong(1);
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                double valor = rs.getDouble("valor");
                Categoria ca = Categoria.valueOf(rs.getString("categoria"));

                Despesa despesa = new Despesa(id, descricao, data, valor, ca);
                despesas.add(despesa);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return despesas;
    }
}
