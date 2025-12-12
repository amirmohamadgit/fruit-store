package util;

import repositories.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ApplicationContext {

    private static ApplicationContext CTX;
    private Connection connection;
    private CostumerRepository costumerRepository;
    private FruitRepository fruitRepository;
    private SellerRepositoryImpl sellerRepository;

    private ApplicationContext() {}

    public static ApplicationContext getInstance() {
        if (CTX == null) CTX = new ApplicationContext();
        return CTX;
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        ApplicationProperties.DATABASE_URL,
                        ApplicationProperties.DATABASE_USER,
                        ApplicationProperties.DATABASE_PASSWORD
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public CostumerRepository getCostumerRepository() {
        if (Objects.isNull(costumerRepository)) costumerRepository = new CostumerRepositoryImpl(getConnection());
        return costumerRepository;
    }

    public FruitRepository getFruitRepository() {
        if (Objects.isNull(fruitRepository)) fruitRepository = new FruitRepositoryImpl(getConnection());
        return fruitRepository;
    }

    public SellerRepositoryImpl getSellerRepository() {
        if (Objects.isNull(sellerRepository)) sellerRepository = new SellerRepositoryImpl(getConnection());
        return sellerRepository;
    }
}
