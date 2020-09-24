import java.util.*;

public class Ship {
    private String name;
    private int cargoCapacity;
    private HashMap<Good, Integer> itemInventory;
    private double fuelCapacity;
    private int health;
    private int healthCapacity;
    private String imageUrl;
    private int fuelLeft;

    public Ship(String name, int cargo, double fuel, int health, String imageUrl) {
        this.name = name;
        this.cargoCapacity = cargo;
        this.itemInventory = new HashMap<Good, Integer>();
        this.fuelCapacity = fuel;
        this.health = health;
        this.healthCapacity = health;
        this.imageUrl = imageUrl;
        this.fuelLeft = 100;
    }

    public String getName() {
        return name;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public Map<Good, Integer> getItemInventory() {
        return itemInventory;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }


    public int getHealth() {
        return health;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public int getFuelLeft() {
        return fuelLeft;
    }

    public int getCurrItemsNum() {
        int total = 0;
        for (Map.Entry element: itemInventory.entrySet()) {
            total += (int) element.getValue();
        }
        return total;
    }

    public void damage(int damage) {
        this.health -= damage;
    }

    public boolean buyGoods(Good goods, int quantity) {
        if (getCurrItemsNum() + quantity > cargoCapacity) {
            return false;
        } else {
            if (!itemInventory.containsKey(goods)) {
                itemInventory.put(goods, quantity);
            } else {
                itemInventory.replace(goods, quantity + itemInventory.get(goods));
            }
            return true;
        }
    }
    public void useFuel(int n) {
        this.fuelLeft = fuelLeft - n;
    }

    public String repair(AppState state) {
        if (this.health == this.healthCapacity) {
            return "No need to repair";
        } else {
            int healthToRepair = this.healthCapacity - this.health;
            if (state.getCredit() - healthToRepair / state.getEngineerPoint() < 0) {
                return "No enough credits to support ship repair.";
            }
            state.setCredit(state.getCredit() - healthToRepair / state.getEngineerPoint());
            this.health = this.healthCapacity;
            return "Your ship has been repaired!";
        }
    }

    public int numOfGood(Good good) {
        if (!itemInventory.containsKey(good)) {
            return 0;
        } else {
            return itemInventory.get(good);
        }
    }

    public boolean sellGoods(Good goods, int quantity) {
        if (!itemInventory.containsKey(goods) || itemInventory.get(goods) < quantity) {
            return false;
        } else {
            itemInventory.replace(goods,  itemInventory.get(goods) - quantity);
            return true;
        }
    }
    public void addFuel(int amount) {
        this.fuelLeft = fuelLeft + amount;
    }
    public void useItem(Good good, int amount) {
        itemInventory.replace(good, itemInventory.get(good) - amount);
    }

    public void clearCargo() {
        this.itemInventory = new HashMap<Good, Integer>();
    }
}

