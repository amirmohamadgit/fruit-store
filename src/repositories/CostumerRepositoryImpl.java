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
import java.util.Objects;

public class CostumerRepositoryImpl extends AbstractCrudRepository implements CostumerRepository{

    private PreparedStatement findByPhoneNumberStatement;

    public CostumerRepositoryImpl(Connection connection) {
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

    public Costumer findByPhoneNumber(String phoneNumber) {
        try (PreparedStatement statement = getFindByPhoneNumberStatement()) {
            statement.setString(1, phoneNumber);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString(Costumer.NAME_COLUMN);
                    String phonNumber = rs.getString(Costumer.PHONE_NUMBER_COLUMN);
                    String address = rs.getString(Costumer.ADDRESS_COLUMN);

                    Costumer costumer = new Costumer(name, phonNumber, address);
                    costumer.setId(id);
                    return costumer;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    private PreparedStatement getFindByPhoneNumberStatement() {
        if (Objects.isNull(findByPhoneNumberStatement)) {
            try {
                findByPhoneNumberStatement = connection.prepareStatement("SELECT * FROM costumers WHERE phone_number = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return findByPhoneNumberStatement;
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
