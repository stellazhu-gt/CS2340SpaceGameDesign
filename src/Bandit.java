import java.util.Random;
public class Bandit extends NPC {
    private int creditLost = randCredits();
    private String welcomeText = "Hey listen you little boy! I don't wanna waste time. Take "
            + creditLost + " credits out and I will leave. You don't want any trouble right?";
    private String deathText = "    I apologize for my ignorance. You are a true hero.";
    private String fleeSuccessText = "    You coward! "
            + "Come back and fight against me!";
    private String fleeFailureText = "    Where are you going you little coward? "
            + "Take your credits out!";
    private String combatFailureText = "    Your impoliteness only exacerbates this situation!"
            + " Now I will take all yours items and credits!";
    private String paySuccess = "    All the money is mine right now hahaha";
    private String payFailure = "    You can't even afford my demand! "
            + "I will punish you little poor guy. ";

    public Bandit(String name) {
        super(name);
    }

    public int randCredits() {
        Random rand = new Random();
        return rand.nextInt(300);
    }

    public String getWelcomeText() {
        StringBuilder message = new StringBuilder("Hey listen you little boy!\n");
        message.append("    I don't wanna waste time.\n ");
        message.append("   Take " + creditLost + " credits out and I will leave\n");
        message.append("    You don't want any trouble right?");
        return message.toString();
    }

    public String getDeathText() {
        return deathText;
    }

    public String getFleeSuccessText() {
        return fleeSuccessText;
    }

    public String getFleeFailureText() {
        return fleeFailureText;
    }

    public String getCombatSuccessText() {
        return deathText;
    }

    public String getCombatFailureText() {
        return combatFailureText;
    }

    public String getPaySuccess() {
        return paySuccess;
    }

    public String getPayFailure() {
        return payFailure;
    }

    public boolean pay(AppState state) {
        if (state.getCredit() >= creditLost) {
            state.setCredit(state.getCredit() - creditLost);
            return true;
        } else {
            if (state.getShip().getCurrItemsNum() == 0) {
                state.getShip().damage(5);
            } else {
                state.getShip().clearCargo();
            }
            return false;
        }
    }


    public boolean flee(AppState state) {
        Random rand = new Random();
        if (rand.nextDouble() < state.getPilotPoint() / 20) {
            return true;
        } else {
            state.getShip().damage(5);
            state.setCredit(0);
            return false;
        }
    }

    public boolean fight(AppState state) {
        Random rand = new Random();
        if (rand.nextDouble() > state.getFighterPoint() / 20) {
            int n = state.getCredit();
            int m = (int) (state.getCredit() * 1.1);
            state.setCredit(m);
            deathText = deathText + " You have earned " + (m - n) + " credit.";
            return true;
        } else {
            state.setCredit(0);
            state.getShip().damage(5);
            return false;
        }
    }
}
