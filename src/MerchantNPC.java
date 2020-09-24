/** MerchantNPC is a class contained in each Market.
 * Each MerchantNPC has a name.
 * Each MerchantNPC belongs to only one unique market and region.
 * MerchantNPCs are responsible for selling Market Goods to players,
 * and purchase in Goods to enlarge market inventory.
 * @author Xiaoran (Stella) Zhu
 * @version 2.0
 * @since 1.0
 */
public class MerchantNPC {
    protected String name;
    protected Market market;

    /** Creates an MerchantNPC with the specified name.
     * @param name The MerchantNPC’s last name.
     */
    public MerchantNPC(String name) {
        this.name = name;
    }

    public boolean placeSellOrder(Good good, AppState player) {
        if (playerCanSell(good, player)) {
            player.setCredit(player.getCredit() + calculateSellPrice(good, player));
            return true;
        } else {
            return false;
        }
    }

    public boolean placeBuyOrder(Good good, AppState player) {
        if (playerCanBuy(good, player)) {
            player.setCredit(player.getCredit() - calculateSellPrice(good, player));
            return true;
        } else {
            return false;
        }
    }

    public int calculateBuyPrice(Good good, AppState player) {
        return (int) (good.getBasePrice() * (1 - player.getMerchantPoint() * 0.001));
    }

    public int calculateSellPrice(Good good, AppState player) {
        return (int) (good.getBasePrice() * (1 + player.getMerchantPoint() * 0.001));
    }

    public boolean playerCanBuy(Good good, AppState player) {
        int currPrice = calculateBuyPrice(good, player);
        System.out.println(good);
        return (player.getCredit() > currPrice) && player.getShip().buyGoods(good, 1)
                && (market.getAvailableGoods().get(good) > 0);
    }

    public boolean playerCanSell(Good good, AppState player) {
        int currPrice = calculateSellPrice(good, player);
        return player.getShip().sellGoods(good, 1);
    }


    /** Gets the MerchantNPC’s name.
     * @return A string representing the employee’s last name.
     */
    public String getName() {
        return name;
    }


    public Region getRegion() {
        return Region.valueOf(name);
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Market getMarket() {
        return market;
    }
}

