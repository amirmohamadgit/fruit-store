package repositories.base;

import entities.BaseEntity;

import java.sql.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractCrudRepository implements CrudRepository {

    private static final CharSequence QUERY_DELIMITER = ",";
    protected final Connection connection;
    protected PreparedStatement findAllStatement;
    protected PreparedStatement insertStatement;
    protected PreparedStatement updateStatement;
    protected PreparedStatement findByIdStatement;
    protected PreparedStatement deleteByIdStatement;

    protected AbstractCrudRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(BaseEntity baseEntity) {
        if (Objects.isNull(baseEntity.getId())) {
            insert(baseEntity);
        } else update(baseEntity);
    }

    private void insert(BaseEntity baseEntity) {
        PreparedStatement statement = getInsertStatement();
        try {
            fillPreparedStatementForInsert(baseEntity);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                baseEntity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void update(BaseEntity baseEntity);

    @Override
    public abstract BaseEntity findById(Integer id);

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public abstract List<String> findAll();

    private PreparedStatement getInsertStatement() {
        if (Objects.isNull(insertStatement)) {
            try {
                String[] insertColumns = getInsertColumns();
                String[] questionMarks = new String[insertColumns.length];
                Arrays.fill(questionMarks, "?");
                insertStatement = connection.prepareStatement(
                        MessageFormat.format("insert into {0} ({1}) values ({2})",
                                getTableName(),
                                String.join(QUERY_DELIMITER, insertColumns),
                                String.join(QUERY_DELIMITER, questionMarks)),
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return insertStatement;
    }



    protected PreparedStatement getUpdateStatement() {
        if (Objects.isNull(updateStatement)) {
            String[] columns = getInsertColumns();

            StringBuilder setClause = new StringBuilder();
            for (int i = 0; i < columns.length; i++) {
                setClause.append(columns[i]).append(" = ?");
                if (i < columns.length - 1) {
                    setClause.append(", ");
                }
            }

            String sql = "UPDATE " + getTableName() + " SET " + setClause + " WHERE id = ?";

            try {
                updateStatement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return updateStatement;
    }

    protected PreparedStatement getFindAllStatement(){
        if (Objects.isNull(findAllStatement)) {
            try {
                findAllStatement = connection.prepareStatement("SELECT * FROM " + getTableName());

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return findAllStatement;
    }

    protected PreparedStatement getFindByIdStatement(){
        if (Objects.isNull(findByIdStatement)) {
            try {
                findByIdStatement = connection.prepareStatement("SELECT * FROM " + getTableName() + "WHERE id = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return findByIdStatement;
    }

    protected PreparedStatement getDeleteByIdStatement(){
        if (Objects.isNull(deleteByIdStatement)) {
            try {
                deleteByIdStatement = connection.prepareStatement("DELETE FROM " + getTableName() + "WHERE id = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return deleteByIdStatement;
    }

    public abstract String[] getInsertColumns();

    public abstract String getTableName();

    protected abstract void fillPreparedStatementForInsert(BaseEntity baseEntity);


}
