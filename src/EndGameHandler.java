public class EndGameHandler {
    private AppState state;
    private Good winningObject;

    public EndGameHandler(AppState state, Good winningObject) {
        this.state = state;
        this.winningObject = winningObject;
    }

    public boolean isOver() {
        return state.getShip().getHealth() == 0;
    }

    public boolean isWin() {
        return state.getShip().getItemInventory().containsKey(winningObject);
    }
}
