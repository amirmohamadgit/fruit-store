package services;

import entities.Costumer;
import entities.Seller;

import java.util.List;
import java.util.Objects;

public class CostumerService {

    public static void register(Costumer costumer) {
        BaseService.costumerRepository.save(costumer);
    }

    public static boolean login(String phoneNumber) {
        return Objects.nonNull(BaseService.costumerRepository.findByPhoneNumber(phoneNumber));
    }

    public static List<String> allFruits() {
        return BaseService.fruitRepository.findAll();
    }
}
