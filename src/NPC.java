/**
 * This abstract class will be extended by all Encounterable npcs
 * (including Bandit, Trader, and Police)
 */
public abstract class NPC {
    protected String name;

    public NPC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getWelcomeText();

    public abstract String getDeathText();

    public abstract String getFleeSuccessText();

    public abstract String getFleeFailureText();

    public abstract String getCombatSuccessText();

    public abstract String getCombatFailureText();

    public void encounter(AppState player) {

    }
}
