import java.util.*;

/**
 *  Trader class
 *  There will be four cases for player to choose
 *  (in section E)
 *  CASE 1. buy
 *  CASE 2. ignore
 *  CASE 3. rob
 *  CASE 4. negotiate
 *  @author Stella Zhu
 */

public class Trader extends NPC {
    // >>>> A. ATTRIBUTES >>>>
    private HashMap<Good, Integer> inventory = new HashMap<>();
    private Map<Good, Integer> goodsNumber = new HashMap<>();
    private Map<Good, Integer> goodsPrice = new HashMap<>();
    private Map<Good, Integer> goodsRobbed = new HashMap<>();
    private String imageUrl = "images/merchant.jpg";
    private boolean negotiated = false;
    private boolean robbed = false;
    private String name;
    private int numberRobbed;
    private Good goodRobbed;




    // >>>> B. GETTER FAMILY *>3<* >>>>
    public String getImageUrl() {
        return imageUrl;
    }
    public HashMap<Good, Integer> getInventory() {
        return inventory;
    }
    public boolean getNegotiated() {
        return negotiated;
    }
    public boolean getRobbed() {
        return robbed;
    }
    public String getName() {
        return name;
    }
    public String getRobbedGood() {
        return goodRobbed.getName();
    }
    public int getNumberRobbed() {
        return numberRobbed;
    }

    /**
     * Getter for the num of a specific good the trader has
     * @param good the specific good
     * @return the int quantity of good the trader owns
     */
    public int getGoodNum(Good good) {
        if (!inventory.keySet().contains(good)) {
            return 0;
        } else {
            return goodsNumber.get(good);
        }
    }

    /**
     * Getter for the price of a specific good the trader has
     * @param good the specific good
     * @return the int price of good the trader owns
     */
    public int getGoodPrice(Good good) {
        if (!inventory.keySet().contains(good)) {
            return 0;
        } else {
            return goodsPrice.get(good);
        }
    }

    /**
     * Getter for the price of a specific good the trader has
     * @param good the specific good
     * @return true for robbed; false for not robbed
     */
    public boolean getGoodsRobbed(Good good) {
        if (!inventory.keySet().contains(good)) {
            return false;
        } else {
            return goodsRobbed.get(good) == 1;
        }
    }


    // >>>> C. CONSTRUCTOR >>>>
    public Trader(String name) {
        super(name);
        this.inventory = generateInventory();
        this.goodsNumber = generateGoodsNumber();
        this.goodsPrice = generateGoodsPrice();
        this.goodsRobbed = generateGoodsRobbed();
    }

    /**
     * Methods to generate Inventory Info of the Trader
     * @return a container with information about the trader's goods
     */
    private HashMap<Good, Integer> generateInventory() {
        HashMap<Good, Integer> inventory = new HashMap();
        for (Good good : Good.values()) {
            Random rand = new Random();
            int randomChoice = rand.nextInt(5); //[0,5)
            if (good.ordinal() % 5 == randomChoice) {
                inventory.put(good, good.getBaseQuantity());
            }
        }
        return inventory;
    }

    private Map<Good, Integer> generateGoodsNumber() {
        Map<Good, Integer> goodsNumber = new HashMap<>();
        int baseQuantity = 50;
        for (Good good : inventory.keySet()) {
            goodsNumber.put(good, baseQuantity);
        }
        return goodsNumber;
    }

    private Map<Good, Integer> generateGoodsPrice() {
        Map<Good, Integer> goodsPrice = new HashMap<>();
        for (Good good : inventory.keySet()) {
            goodsPrice.put(good, good.getBasePrice());
        }
        return goodsPrice;
    }

    private Map<Good, Integer> generateGoodsRobbed() {
        Map<Good, Integer> goodsRobbed = new HashMap<>();
        for (Good good : inventory.keySet()) {
            goodsRobbed.put(good, 0);
        }
        return goodsRobbed;
    }


    // >>>> D. TEXT GETTER FAMILY  *><*  for front-end ;) >>>>
    /**
     * a number of textGetters for display
     * Note: welcome message will list out names of all of the goods the trader has
     * @return messages for front-end
     */
    @Override
    public String getWelcomeText() {
        StringBuilder message = new StringBuilder("Hey, wanna buy something? \n");
        message.append("    Take a look at my goods\n");

        return message.toString();
    }

    @Override
    public String getDeathText() {
        return "I'm  dead!!";
    }

    @Override
    public String getFleeSuccessText() {
        return "You've fleed successfully!";
    }

    @Override
    public String getFleeFailureText() {
        return "Never try to flee!";
    }

    @Override
    public String getCombatSuccessText() {
        return "You wicked! You stole my goods!";
    }

    @Override
    public String getCombatFailureText() {
        return ("Never try to rob me.");
    }

    public String getSuccessPurchaseText() {
        return "You've finished the transaction!";
    }
    
    public String getFailurePurchaseText() {
        return "Your transaction failed!";
    }



    // >>>> E. CASE DIVISIONS >>>>
    /**
     * CASE 1: Player chooses to make purchase
     * @param good the specific good that the player want to buy
     * @param quantity the specific amount the player want to buy
     * @param player the player who wants to make purchase
     * @return boolean indicating whether the purchase is successful
     */
    public int placePurchase(Good good, int quantity, AppState player) {
        System.out.print(inventory.keySet());
        if (inventory.keySet().contains(good)) { //good available
            if (goodsNumber.get(good) < quantity) { //not enough quantity
                return 1;
            } else { //enough quantity
                System.out.println(good);
                int cost = goodsPrice.get(good) * quantity;
                if (playerCanBuy(player, cost)) { //enough credit
                    player.setCredit(player.getCredit() - cost);
                    player.getShip().buyGoods(good, 1);
                    return 4;
                } else { //not enough credit
                    return 2;
                }
            }
        } else { //no good available
            return 3;
        }
    }

    /**
     * helper method to determine if the player has enough credit to purchase
     * @param player a player
     * @param cost a cost
     * @return bool value
     */
    private boolean playerCanBuy(AppState player, int cost) {
        return (player.getCredit() >= cost);
    }


    /**
     * CASE 2: Player chooses to ignore the trader == do nothing
     * @return true
     */
    public boolean ignore() {
        return true;
    }


    /**
     * CASE 3: Player chooses to rob the trader
     * @param player The player who wants to rob the trader
     * @return boolean indicating if the rob is successful
     */
    public boolean rob(AppState player) {
        Random rand = new Random();
        if (!randomChance(player.getFighterPoint())) {
            //CASE: fail the rob; get damaged
            player.getShip().damage(5);
            return false;
        } else {
            //CASE: rob the trader
            //range for possible number of good types: [1:inventory.size()-1]
            int goodTypeNum = rand.nextInt(5) + 1;
            List<Good> selectedGoodList = new ArrayList<>(goodsNumber.keySet());
            Collections.shuffle(selectedGoodList);
            for (int i = 0; i < goodTypeNum; i++) { //select {goodTypeNum} of goods from list
                Good selectedGood = selectedGoodList.get(i);
                int beforeRob = goodsNumber.get(selectedGood);
                int rob = rand.nextInt(beforeRob) + 1; //range: [1: beforeRob]
                this.numberRobbed = rob;
                this.goodRobbed = selectedGood;
                int afterRob = beforeRob - rob;
                goodsNumber.put(selectedGood, afterRob);
                goodsRobbed.put(selectedGood, 1);
                if (afterRob == 0) {
                    goodsNumber.remove(selectedGood);
                    goodsPrice.remove(selectedGood);
                    inventory.remove(selectedGood);
                }
            }
            robbed = true;
            return true;
        }
    }

    /**
     * CASE 4: the player chooses to negotiate with the trader
     * IMPORTANT: WRITE TO **FRONT_END**! IF boolean negotiated is true,
     *            the button for negotiation should be deactivated :)
     * @param player the player who wants to argue the price
     * @param good the specific good the player wants to take the negotiation
     * @return boolean indicating whether the negotiation is successful
     */
    public boolean negotiate(AppState player, Good good) {
        if ((!negotiated) && (inventory.keySet().contains(good))) {
            if (!randomChance(player.getMerchantPoint())) {
                // CASE: fail the negotiation; higher price
                goodsPrice.put(good, good.getMaxTraderPrice());
                negotiated = true;
                return false;
            } else {
                // CASE: win the negotiation; lower price
                for (Good good2 : goodsPrice.keySet()) {
                    goodsPrice.put(good2, good2.getMinTraderPrice());
                }
                negotiated = true;
                return true;
            }
        } else { //player has negotiated or the good is not in stock
            return false;
        }
    }


    /**
     * Helper method to determine if rob or negotiate is successful
     * @param point fighterPoint for rob; merchantPoint for negotiate
     * @return boolean value to determine if the action is successful
     *         true if sufficient skill point; vice versa
     */
    private boolean randomChance(int point) {
        Random rand = new Random();
        double outcomeIndex = rand.nextDouble();
        int randInt = rand.nextInt(2); //either 1 or 0
        int pointIndex = 1 - point / 20; //assume max point is 20
        if (randInt == 1) {
            outcomeIndex = pointIndex;
        }
        return outcomeIndex > 0.5;
    }
}