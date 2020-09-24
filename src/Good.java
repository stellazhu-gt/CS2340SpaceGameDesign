import java.util.Random;

/** enum of goods:
 * (nested enum GoodsType: specific Goods)
 * FOOD: WATER, VEGETABLE,DAIRY, EGG, DAIRY, MEAT, WINE
 * CLOTHES: SUIT, COAT, ARMOR, INVISIBLE_CLOAK
 * FUEL: COAL, OIL, GAS, SOLAR_PANEL
 * MINE: IRON, COPPER, SILVER, GOLD
 * @author Xiaoran (Stella) Zhu
 * @version 2.0
 * @since 1.0
 */
public enum Good {
    //FOOD
    WATER("WATER", GoodType.FOOD, 1, 25, 45, 30, 500),
    VEGETABLE("VEGETABLE", GoodType.FOOD, 2, 60, 80, 65, 400),
    EGG("EGG", GoodType.FOOD, 3, 75, 100, 80, 300),
    DAIRY("DAIRY", GoodType.FOOD, 4, 70, 120, 80, 300),
    MEAT("MEAT", GoodType.FOOD, 6, 120, 180, 156, 200),
    WINE("WINE", GoodType.FOOD, 8, 180, 250, 220, 100),
    //CLOTHES
    SUIT("SUIT", GoodType.CLOTHES, 2, 100, 150, 120, 300),
    COAT("COAT", GoodType.CLOTHES, 3, 150, 200, 170, 200),
    ARMOR("ARMOR", GoodType.CLOTHES, 5, 250, 320, 280, 100),
    INVISIBLE_CLOAK("INVISIBLE_CLOAK", GoodType.CLOTHES, 10, 400, 500, 480, 50),
    //FUEL
    COAL("COAL", GoodType.FUEL, 2, 50, 80, 60, 400),
    OIL("OIL", GoodType.FUEL, 4, 70, 100, 80, 300),
    GAS("GAS", GoodType.FUEL, 6, 70, 150, 80, 200),
    SOLAR_PANEL("SOLAR_PANEL", GoodType.FUEL, 8, 250, 300, 280, 100),
    //MINE
    IRON("IRON", GoodType.MINE, 3, 120, 180, 150, 300),
    COPPER("COPPER", GoodType.MINE, 5, 180, 230, 200, 200),
    SILVER("SILVER", GoodType.MINE, 7, 220, 280, 250, 150),
    GOLD("GOLD", GoodType.MINE, 9, 300, 400, 350, 70),
    //Winning
    INFINITYGAUNTLET("INFINITYGAUNTLET", GoodType.SPECIAL, 20, 800, 900, 850, 1);


    private final GoodType goodsType;
    private final String typeName; //name of the goodType
    private final String name; //name of the good
    private final int minTechSell; //minimum region technologyLevel to sell the goods
    private final int minTraderPrice;
    private final int maxTraderPrice;
    private int basePrice;
    private final int baseQuantity; //default basic storage quantity of each goods in the market


    Good(String goodName, final GoodType goodsType, int minTechSell, int minTraderPrice,
         int maxTraderPrice, int basePrice, int baseQuantity) {
        Random rand = new Random();
        this.name = goodName;
        this.typeName = goodsType.getTypeName();
        this.basePrice = basePrice;
        this.goodsType = goodsType;
        this.minTechSell = minTechSell;
        this.minTraderPrice = minTraderPrice;
        this.maxTraderPrice = maxTraderPrice;
        this.baseQuantity = baseQuantity;
    }

    public String getName() {
        return name;
    }

    public GoodType getGoodsType() {
        return goodsType;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getMinTechSell() {
        return minTechSell;
    }

    public int getMinTraderPrice() {
        return minTraderPrice;
    }

    public int getMaxTraderPrice() {
        return maxTraderPrice;
    }

    public int getBaseQuantity() {
        return baseQuantity;
    }

}

