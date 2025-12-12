package repositories;

import entities.BaseEntity;
import entities.Costumer;
import entities.Fruit;
import repositories.base.AbstractCrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class FruitRepositoryImpl extends AbstractCrudRepository implements FruitRepository{

    public FruitRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void update(BaseEntity baseEntity) {
        try (PreparedStatement statement = getUpdateStatement()) {
            statement.setString(1, ((Fruit) baseEntity).getName());
            statement.setString(2, ((Fruit) baseEntity).getDescription());
            statement.setInt(3, ((Fruit) baseEntity).getInventory());
            statement.setLong(4, ((Fruit) baseEntity).getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<String> findAll() {

        try (PreparedStatement statement = getFindAllStatement()){
            List<String> fruits = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery()){
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString(Fruit.NAME_COLUMN);
                    String description = rs.getString(Fruit.DESCRIPTION_COLUMN);
                    String inventory = rs.getString(Fruit.INVENTORY_COLUMN);
                    Long price = rs.getLong(Fruit.PRICE_COLUMN);

                    String fruit = MessageFormat.format("ID: {0}, name: {1}, description: {2}, inventory: {3}, price: {4}",
                            id, name, description, inventory, price);

                    fruits.add(fruit);
                }
                return fruits;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String[] getInsertColumns() {
        return new String[]{
                Fruit.NAME_COLUMN,
                Fruit.DESCRIPTION_COLUMN,
                Fruit.INVENTORY_COLUMN,
                Fruit.PRICE_COLUMN
        };
    }

    @Override
    public String getTableName() {
        return Fruit.TABLE_NAME;
    }

    @Override
    protected void fillPreparedStatementForInsert(BaseEntity baseEntity) {
        try {
            insertStatement.setString(1, ((Fruit) baseEntity).getName());
            insertStatement.setString(2, ((Fruit) baseEntity).getDescription());
            insertStatement.setInt(3, ((Fruit) baseEntity).getInventory());
            insertStatement.setLong(4, ((Fruit) baseEntity).getPrice());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
