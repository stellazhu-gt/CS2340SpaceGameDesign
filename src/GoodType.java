public enum GoodType {
    FOOD("Food", 5, false),
    CLOTHES("Clothes", 10, true),
    FUEL("Fuel", 5, false),
    MINE("Mine", 10, true),
    SPECIAL("Special", 1, false);

    private final String typeName;
    private final int variance;
    private final boolean upgrade; //flag for upgrade

    private GoodType(String typeName, int variance, boolean upgrade) {
        this.typeName = typeName;
        this.variance = variance;
        this.upgrade = upgrade;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getVariance() {
        return  variance;
    }

    public boolean getUpgrade() {
        return upgrade;
    }
}
