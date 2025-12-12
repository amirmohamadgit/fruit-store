package services;

import repositories.CostumerRepository;
import repositories.FruitRepository;
import repositories.SellerRepositoryImpl;
import util.ApplicationContext;

public class BaseService {
    static ApplicationContext CTX = ApplicationContext.getInstance();
    static CostumerRepository costumerRepository = CTX.getCostumerRepository();
    static FruitRepository fruitRepository = CTX.getFruitRepository();
    static SellerRepositoryImpl sellerRepository = CTX.getSellerRepository();
}
