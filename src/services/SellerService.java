package services;

import entities.Fruit;
import entities.Seller;

import java.util.List;
import java.util.Objects;

public class SellerService {
    public static boolean login(String phoneNumber, String password) {
        Seller seller = BaseService.sellerRepository.findPasswordByName(phoneNumber);
        if (Objects.isNull(seller)) return false;
        return seller.getPassword().equals(password);
    }

    public static void addFruit(Fruit fruit) {
        BaseService.fruitRepository.save(fruit);
    }
    public static void editFruit(Integer ID, int newInventory, long newPrice) {
        Fruit fruit = (Fruit) BaseService.fruitRepository.findById(ID);
        fruit.setInventory(newInventory);
        fruit.setPrice(newPrice);
        BaseService.fruitRepository.save(fruit);
    }

    public static void removeFruit(Integer ID) {
        BaseService.fruitRepository.deleteById(ID);
    }

    public static List<String> allFruits() {
        return BaseService.fruitRepository.findAll();
    }
}
