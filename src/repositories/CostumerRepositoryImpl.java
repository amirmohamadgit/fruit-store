package repositories;

import entities.BaseEntity;
import entities.Costumer;
import repositories.base.AbstractCrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CostumerRepositoryImpl extends AbstractCrudRepository {

    protected CostumerRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void update(BaseEntity baseEntity) {
        try (PreparedStatement statement = getUpdateStatement()) {
            statement.setString(1, ((Costumer) baseEntity).getName());
            statement.setString(2, ((Costumer) baseEntity).getPhoneNumber());
            statement.setString(3, ((Costumer) baseEntity).getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<String> findAll() {
        PreparedStatement statement = getFindAllStatement();
        try {
            List<String> costumers = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString(Costumer.NAME_COLUMN);
                    String phoneNumber = rs.getString(Costumer.PHONE_NUMBER_COLUMN);
                    String address = rs.getString(Costumer.ADDRESS_COLUMN);

                    String costumer = MessageFormat.format("ID: {0}, name: {1}, phone number: {2}, address: {3}",
                            id, name, phoneNumber, address);

                    costumers.add(costumer);
                }
                return costumers;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public String[] getInsertColumns() {
        return new String[]{
                Costumer.NAME_COLUMN,
                Costumer.PHONE_NUMBER_COLUMN,
                Costumer.ADDRESS_COLUMN
        };
    }

    @Override
    public String getTableName() {
        return Costumer.TABLE_NAME;
    }

    @Override
    protected void fillPreparedStatementForInsert(BaseEntity baseEntity) {
        try {
            insertStatement.setString(1, ((Costumer) baseEntity).getName());
            insertStatement.setString(2, ((Costumer) baseEntity).getPhoneNumber());
            insertStatement.setString(3, ((Costumer) baseEntity).getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
