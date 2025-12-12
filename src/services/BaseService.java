package services;

import repositories.CostumerRepository;
import repositories.FruitRepository;
import util.ApplicationContext;

public class BaseService {
    static ApplicationContext CTX = ApplicationContext.getInstance();
    static CostumerRepository costumerRepository = CTX.getCostumerRepository();
    static FruitRepository fruitRepository = CTX.getFruitRepository();
}
