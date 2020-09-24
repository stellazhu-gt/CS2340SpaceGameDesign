import java.util.HashMap;

/** Market is a class contained in each region.
 * Each Market has a name and one unique MerchantNPC.
 * Each Market belongs to only one unique region.
 * Markets are responsible for keeping track of all goods inventory,
 * and manage price variations of goods based to trade and region techLevel.
 * @author Xiaoran (Stella) Zhu
 * @version 2.0
 * @since 1.0
 */
public class Market {
    protected String name;
    protected MerchantNPC m;
    protected boolean winningRegion = false;

    public Market(String marketName) {
        this.name = marketName;
        m = new MerchantNPC(marketName);
        m.setMarket(this);
    }

    public MerchantNPC getMerchantNPC() {
        return m;
    }

    public HashMap<Good, Integer> getAvailableGoods() {
        HashMap<Good, Integer> availableGoods = new HashMap();
        for (Good good : Good.values()) {
            if (good.getMinTechSell() <= Region.valueOf(name).getTechnologyLevel()) {
                availableGoods.put(good, good.getBaseQuantity());
            }
            if (winningRegion) {
                availableGoods.put(Good.INFINITYGAUNTLET, 1);
            }
        }
        return availableGoods;
    }

    public void setWinningLoc() {
        this.winningRegion = true;
    }

    public String getName() {
        return name;
    }
}