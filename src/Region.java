import java.util.ArrayList;
public enum Region {
    // enum fields-------------------------------------------------------------------------
    ORION("Orion", "The center of the universe populated by all species. "
            + "Highly developed economy and technology",
            9, "Images/orion.jpg", new Market("ORION")),
    COALSACK("Coalsack", "Mining district abundant of all mines ranging "
            + "from precious stones to spaceship "
            + "essential material", 8, "Images/coalsack."
            + "jpg", new Market("COALSACK")),
    GREATRIFT("Great rift", "Deserted and empty. In tale,"
            + " it's caused by the century war between two ancient"
            + "unknown species.", 1, "Images/greatrift.jpg",  new Market("GREATRIFT")),
    N44("N44", "Peaceful and calm small town.", 5, "Images/n44."
            + "jpg",  new Market("N44")),
    DARKHORSE("Dark Horse", "adventurers' favorite space for battle, excitement, "
            + "risk, and profit",
            2, "Images/darkhorse.jpg",  new Market("DARKHORSE")),
    IC418("IC 418", "Technology center of the universe, best and cheapeast place to upgrade "
            + "your spaceship", 10, "Images/ic418.jpg",  new Market("IC418")),
    GERUDO("Gerudo", "Tropical weather. Popular "
            + "for vacation of enjoying great food and relaxation", 6, "Images/geruda.jpg",
            new Market("GERUDO")),
    PRAWN("Prawn", "Extreme cold weather! Cautious! Need special clothes to enter", 3,
            "Images/prawn.jpg",  new Market("PRAWN")),
    FARON("Faron", "folklore has it that only mermaids live on this land. So "
            + "the land is water based and water only. "
            + "Prepare your swimming suit", 7, "Images/faron.jpg",
            new Market("FARON")),
    HYRULE("Hyrule", "Always spring. Miss th fresh fruit and vegatble just"
            + "picked from the tree?", 4, "Images/hyrule.jpg",
            new Market("HYRULE"));

    private String name;
    private String description;
    private int technologyLevel;
    private Market market;
    private String imageUrl;
    private static ArrayList<String> existingCordination; // store all the coordination used

    Region(String name, String description, int technologyLevel, String imageURL, Market market) {
        this.name = name;
        this.description = description;
        this.technologyLevel = technologyLevel;
        this.market = market; // need initialize market
        this.imageUrl = imageURL;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public Market getMarket() {
        return market;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
