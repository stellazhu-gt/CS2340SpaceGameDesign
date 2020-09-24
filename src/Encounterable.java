/**
 * This abstract class will be extended by all Encounterable npcs
 * (including Bandit, Trader, and Police)
 */
public abstract class Encounterable {
    protected String name;

    protected String welcomeText;
    protected String deathText;

    // The Text displays
    // after the player takes a flee
    protected String fleeSuccessText;
    protected String fleeFailureText;

    // The Text displays
    // after the player takes a fight
    protected String combatSuccessText;
    protected String combatFailureText;

    public Encounterable(String name, String welcomeText, String deathText,
                         String fleeSuccessText, String  fleeFailureText,
                         String combatSuccessText, String combatFailureText) {
        this.name = name;
        this.welcomeText = welcomeText;
        this.deathText = deathText;
        this.fleeSuccessText = fleeSuccessText;
        this.fleeFailureText = fleeFailureText;
        this.combatSuccessText = combatSuccessText;
        this.combatFailureText = combatFailureText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    public String getDeathText() {
        return deathText;
    }

    public void setDeathText(String deathText) {
        this.deathText = deathText;
    }

    public String getFleeSuccessText() {
        return fleeSuccessText;
    }

    public void setFleeSuccessText(String fleeSuccessText) {
        this.fleeSuccessText = fleeSuccessText;
    }

    public String getFleeFailureText() {
        return fleeFailureText;
    }

    public void setFleeFailureText(String fleeFailureText) {
        this.fleeFailureText = fleeFailureText;
    }

    public String getCombatSuccessText() {
        return combatSuccessText;
    }

    public void setCombatSuccessText(String combatSuccessText) {
        combatSuccessText = combatSuccessText;
    }

    public String getCombatFailureText() {
        return combatFailureText;
    }

    public void setCombatFailureText(String combatFailureText) {
        combatFailureText = combatFailureText;
    }

}
