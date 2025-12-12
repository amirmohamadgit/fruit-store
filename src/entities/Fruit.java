package entities;

public class Fruit extends BaseEntity{

    public static final String TABLE_NAME = "fruits";

    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String INVENTORY_COLUMN = "inventory";
    public static final String PRICE_COLUMN = "price";

    private String name;
    private String description;
    private int inventory;
    private long price;

    public Fruit(String name, String description, int inventory, long price) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getInventory() {
        return inventory;
    }

    public long getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", inventory=" + inventory +
                ", price=" + price +
                '}';
    }
}
