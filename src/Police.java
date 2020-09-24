import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Police extends NPC {
    private static final String WELCOME_TEXT  = "We suspected you of "
            + "conducting \n    criminal activities.";
    private static final String DEALTH_TEXT  = "You are under arrest!";
    private static final String FLEE_SUCCESS_TEXT  = "We lost track of the criminal.";
    private static final String FLEE_FAILURE_TEXT  = "You are under arrest!";
    private static final String COMBAT_SUCCESS_TEXT  = "We lost track of the criminal.";
    private static final String COMBAT_FAILURE_TEXT  = "You are under arrest!";
    private Good targetItem;

    public static String getImageUrl() {
        return IMAGE_URL;
    }

    private static final String IMAGE_URL = "images/police.png";

    public Police(String name) {
        super(name);
    }

    @Override
    public String getWelcomeText() {
        return WELCOME_TEXT;
    }

    @Override
    public String getDeathText() {
        return DEALTH_TEXT;
    }

    @Override
    public String getFleeSuccessText() {
        return FLEE_SUCCESS_TEXT;
    }

    @Override
    public String getFleeFailureText() {
        return FLEE_FAILURE_TEXT;
    }

    @Override
    public String getCombatSuccessText() {
        return COMBAT_SUCCESS_TEXT;
    }

    @Override
    public String getCombatFailureText() {
        return COMBAT_FAILURE_TEXT;
    }

    public Good getTargetItem() {
        return targetItem;
    }

    @Override
    public void encounter(AppState player) {
        Map<Good, Integer>  playerInventory = player.getShip().getItemInventory();
        Set<Good> playerInventoryKeySet = playerInventory.keySet();
        ArrayList<Good> keyNotEmpty = new ArrayList();
        for (Good gd : playerInventoryKeySet) {
            if (playerInventory.get(gd) != 0) {
                keyNotEmpty.add(gd);
            }
        }
        Random rand = new Random();
        targetItem = keyNotEmpty.get(rand.nextInt(keyNotEmpty.size()));
    }

    public void forfeit(AppState player) {
        Map<Good, Integer>  playerInventory = player.getShip().getItemInventory();
        playerInventory.replace(targetItem, 0);
    }

    public boolean flee(AppState player) {
        double successPossibility = (double) player.getPilotPoint() / 25;
        Random rand = new Random();
        double randVal = rand.nextDouble();
        if (randVal > successPossibility) {
            forfeit(player);
            player.getShip().damage(5);
            player.setCredit((int) (player.getCredit() * 0.9));
            return false;
        } else {
            return true;
        }
    }

    public boolean fight(AppState player) {
        double successPossibility = (double) player.getFighterPoint() / 25;
        Random rand = new Random();
        double randVal = rand.nextDouble();
        if (randVal > successPossibility) {
            forfeit(player);
            player.getShip().damage(5);
            player.setCredit((int) (player.getCredit() * 0.9));
            return false;
        } else {
            player.setCredit((int) (player.getCredit() * 1.1));
            return true;
        }
    }
}
