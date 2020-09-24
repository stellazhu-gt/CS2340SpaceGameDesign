import java.util.*;

public class AppState {

    static final int EASY_SP = 20;
    static final int NORM_SP = 15;
    static final int HARD_SP = 10;

    private String name;
    private DifficultyLevel difficultyLevel;
    private int point;
    private int pilotPoint;
    private int fighterPoint;
    private int merchantPoint;
    private int engineerPoint;
    private List<Region> unlockedRegion;
    private boolean regionRandomized;
    private HashMap<String, Integer> regionX;
    private HashMap<String, Integer> regionY;
    private Region currRegion;
    private int credit;
    private Ship ship;
    private HashMap<Good, Integer> currUpgrade;

    public AppState() {
        //      Random r = new Random();
        //      unlockedRegion = new ArrayList();
        //      unlockedRegion.add(Region.values()[r.nextInt(10)]);
        //      Region secondRand = Region.values()[r.nextInt(10)];
        //      while (unlockedRegion.contains(secondRand)) {
        //          secondRand = Region.values()[r.nextInt(10)];
        //      }
        Random rand = new Random();
        Region temp = Region.values()[rand.nextInt(10)];
        temp.getMarket().setWinningLoc();
        System.out.println(temp.getName());
        unlockedRegion = Arrays.asList(Region.values());
        currRegion = unlockedRegion.get(0);
        ship = new Ship("Test", 1, 1, 1, "");
        currUpgrade = new HashMap();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        switch (difficultyLevel) {
        case EASY:
            this.point = EASY_SP;
            this.credit = 200;
            break;
        case NORMAL:
            this.point = NORM_SP;
            this.credit = 150;
            break;
        case DIFFICULT:
            this.point = HARD_SP;
            this.credit = 100;
            break;
        default:
        }
        this.ship = new Ship("unknown ship", 20, 100, 20, "Images/shipModified2.png");
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPilotPoint() {
        return this.pilotPoint;
    }

    public void setPilotPoint(int pilotPoint) {
        this.pilotPoint = pilotPoint;
        if ((this.pilotPoint + this.engineerPoint
                + this.fighterPoint + this.merchantPoint) > this.point) {
            throw new IllegalArgumentException("The sum of all points exceed limit!");
        }
    }

    public String travel(int distance, Region region) {
        if (region.equals(currRegion)) {
            return null;
        }
        int fuel = ship.getFuelLeft();
        if (fuel < distance * 10) {
            return ("Sorry, you don't have enough fuel.");
        }
        if (ship.getHealth() < 0) {
            return ("Sorry, your ship has health level of 0, please repair");
        }
        ship.useFuel(distance * 10);
        Random random = new Random();
        int randomInt = random.nextInt(10);
        switch (difficultyLevel) {
        case EASY:
            if (randomInt < 2) {
                return ("bandit");
            } else if (randomInt < 3) {
                if (ship.getCurrItemsNum() == 0) {
                    return null;
                }
                return ("police");
            } else if (randomInt < 7) {
                return ("trader");
            }
            break;
        case NORMAL:
            if (randomInt < 3) {
                return ("bandit");
            } else if (randomInt < 5) {
                if (ship.getCurrItemsNum() == 0) {
                    return null;
                }
                return ("police");
            } else if (randomInt < 7) {
                return ("trader");
            }
            break;
        case DIFFICULT:
            if (randomInt < 4) {
                return ("bandit");
            } else if (randomInt < 6) {
                if (ship.getCurrItemsNum() == 0) {
                    return null;
                }
                return ("police");
            } else if (randomInt < 8) {
                return ("trader");
            }
            break;
        default:
        }

        return null;
    }

    public int getFighterPoint() {
        return this.fighterPoint;
    }

    public void setFighterPoint(int fighterPoint) {
        this.fighterPoint = fighterPoint;
        if ((this.pilotPoint + this.engineerPoint
                + this.fighterPoint + this.merchantPoint) > this.point) {
            throw new IllegalArgumentException("The sum of all points exceed limit!");
        }
    }

    public int getMerchantPoint() {
        return this.merchantPoint;
    }

    public void setMerchantPoint(int merchantPoint) {
        this.merchantPoint = merchantPoint;
        if ((this.pilotPoint + this.engineerPoint
                + this.fighterPoint + this.merchantPoint) > this.point) {
            throw new IllegalArgumentException("The sum of all points exceed limit!");
        }
    }

    public int getEngineerPoint() {
        return this.engineerPoint;
    }

    public void setEngineerPoint(int engineerPoint) {
        this.engineerPoint = engineerPoint;
        if ((this.pilotPoint + this.engineerPoint
                + this.fighterPoint + this.merchantPoint) > this.point) {
            throw new IllegalArgumentException("The sum of all points exceed limit!");
        }
    }

    public boolean unlockRegion(Region region) {
        if (!unlockedRegion.contains(region)) {
            return false;
        } else {
            unlockedRegion.add(region);
            return true;
        }
    }

    public List<Region> getUnlockedRegion() {
        return unlockedRegion;
    }

    public boolean isRegionRandomized() {
        return regionRandomized;
    }

    public void setRegionRandomized(boolean bol) {
        this.regionRandomized = bol;
    }

    public HashMap<String, Integer> getRegionX() {
        return this.regionX;
    }

    public HashMap<String, Integer> getRegionY() {
        return this.regionY;
    }

    public void setRegionX(HashMap<String, Integer> regionX) {
        this.regionX = regionX;
    }

    public void setRegionY(HashMap<String, Integer> regionY) {
        this.regionY = regionY;
    }

    public Region getCurrRegion() {
        return currRegion;
    }

    public void setCurrRegion(Region region) {
        this.currRegion = region;
    }

    public Market getCurrMarket() {
        return currRegion.getMarket();
    }


    /**
     * determine whether the player has enough goods for sale
     * @param good a specific goods which the play wants to sell
     * @param quantity the amount of goods which the play wants to sell
     * @return true for "can sell"; false for "cannot sell"/"insufficient goods"
     */
    public boolean isGoodToSell(Good good, int quantity) {
        int goodsStock = ship.getItemInventory().get(good);
        return (quantity <= goodsStock);
    }

    public int getCredit() {
        return this.credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public HashMap<Good, Integer> getCurrUpgrade() {
        return currUpgrade;
    }

    public void setCurrUpgrade(HashMap<Good, Integer> currUpgrade) {
        this.currUpgrade = currUpgrade;
    }
}
