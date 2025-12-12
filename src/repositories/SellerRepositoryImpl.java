package repositories;

import entities.Seller;

import java.security.Permissions;
import java.security.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SellerRepositoryImpl {

    private final Connection connection;
    private PreparedStatement findPasswordByName;

    public SellerRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public Seller findPasswordByName(String name) {
        try (PreparedStatement statement = getFindPasswordByName()){
            statement.setString(1, name);

            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()) {
                    String password = rs.getString("password");
                    String sellerName = rs.getString("name");

                    return new Seller(sellerName, password);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    private PreparedStatement getFindPasswordByName() {
        if (Objects.isNull(findPasswordByName)) {
            try {
                findPasswordByName = connection.prepareStatement("SELECT password FROM admins WHERE name = ?");

            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        return findPasswordByName;
    }
}
