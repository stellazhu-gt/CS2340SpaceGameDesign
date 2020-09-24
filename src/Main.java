import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {

    private AppState state = new AppState();
    private EndGameHandler endGameHandler = new EndGameHandler(state, Good.INFINITYGAUNTLET);
    private static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane general = new StackPane();
        Pane buttonPane = new Pane();
        Image mainPageBG = new Image(new FileInputStream("images/main_page.jpg"));

        //Initialising path of the media file, replace this with your file path
        String path = "space.mp3";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);
        primaryStage.show();

        //Setting the image view
        ImageView mainPageIV = new ImageView(mainPageBG);

        //Setting the position of the image
        mainPageIV.setX(640);
        mainPageIV.setY(360);

        //Setting the preserve ratio of the image view
        mainPageIV.setPreserveRatio(true);
        // create start new game button
        Button startButton = new Button("New Game");
        startButton.setMaxSize(150, 50);
        startButton.setMinSize(150, 50);
        startButton.setLayoutX(565);
        startButton.setLayoutY(450);
        // change botton text color
        startButton.setTextFill(Color.WHITE);
        startButton.setId("record-sales");
        EventHandler<ActionEvent> startButtonHdl = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Scene characterScene = null;
                try {
                    characterScene = getInitialConfigOne(primaryStage);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(characterScene);
            }
        };
        startButton.setOnAction(startButtonHdl);

        Button loadButton = new Button("Load Game");
        loadButton.setMaxSize(150, 50);
        loadButton.setMinSize(150, 50);
        loadButton.setLayoutX(565);
        loadButton.setLayoutY(530);
        // change botton text color
        loadButton.setTextFill(Color.WHITE);
        loadButton.setId("record-sales");

        EventHandler<ActionEvent> loadButtonHdl = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                primaryStage.setScene(win(primaryStage));
            }
        };
        loadButton.setOnAction(loadButtonHdl);

        buttonPane.getChildren().add(startButton);
        buttonPane.getChildren().add(loadButton);
        general.getChildren().addAll(mainPageIV, buttonPane);

        Scene root = new Scene(general, 1280, 720);
        root.getStylesheets().add("/style.css");
        primaryStage.setTitle("Space Trader");
        primaryStage.setScene(root);
        primaryStage.show();
    }

    public Scene getInitialConfigOne(Stage primaryStage) throws FileNotFoundException {
        StackPane initialConfigBGPane = new StackPane();
        Image initialConfigBG = new Image(new FileInputStream("images/inital_config.jpg"));
        //Setting the image view
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        //Setting the position of the image
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        //Setting the preserve ratio of the image view
        initialConfigIV.setPreserveRatio(true);

        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);
        Pane buttonPane = new Pane();

        Text difficultyPrompt = new Text("Difficulty Level");
        difficultyPrompt.setId("header");
        difficultyPrompt.setLayoutX(80);
        difficultyPrompt.setLayoutY(100);

        Button easyMode = new Button("Easy");
        Button normMode = new Button("Normal");
        Button difficultMode = new Button("Difficult");

        easyMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                state.setDifficultyLevel(DifficultyLevel.EASY);
                Scene characterScene = null;
                try {
                    characterScene = getInitConfigTwo(primaryStage);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(characterScene);
            }
        });

        normMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                state.setDifficultyLevel(DifficultyLevel.NORMAL);
                Scene characterScene = null;
                try {
                    characterScene = getInitConfigTwo(primaryStage);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(characterScene);
            }
        });
        difficultMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                state.setDifficultyLevel(DifficultyLevel.DIFFICULT);
                Scene characterScene = null;
                try {
                    characterScene = getInitConfigTwo(primaryStage);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(characterScene);
            }
        });

        easyMode.setLayoutX(100);
        easyMode.setLayoutY(150);
        easyMode.setMaxSize(200, 50);
        easyMode.setMinSize(200, 50);

        normMode.setLayoutX(100);
        normMode.setLayoutY(250);
        normMode.setMaxSize(200, 50);
        normMode.setMinSize(200, 50);

        difficultMode.setLayoutX(100);
        difficultMode.setLayoutY(350);
        difficultMode.setMaxSize(200, 50);
        difficultMode.setMinSize(200, 50);

        buttonPane.getChildren().addAll(difficultyPrompt, easyMode, normMode, difficultMode);
        buttonPane.setId("font_button");
        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        floatingCard.getChildren().addAll(floatingCardBG, buttonPane);

        initialConfigBGPane.getChildren().addAll(initialConfigIV, floatingCard);

        initialConfigBGPane.getStylesheets().add("/style.css");
        return new Scene(initialConfigBGPane, 1280, 720);
    }

    public Scene getInitConfigTwo(Stage primaryStage) throws FileNotFoundException {
        StackPane initialConfigBGPane = new StackPane();
        Image initialConfigBG = new Image(new FileInputStream("images/inital_config.jpg"));
        //Setting the image view
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        //Setting the position of the image
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        //Setting the preserve ratio of the image view
        initialConfigIV.setPreserveRatio(true);

        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);

        Text name = new Text("Name");
        name.setId("grid-prompt");
        name.setX(10);
        name.setY(40);

        TextField nameInputField = new TextField();
        nameInputField.setLayoutX(225);
        nameInputField.setLayoutY(15);

        Text pilot = new Text("Pilot Skill");
        pilot.setId("grid-prompt");
        pilot.setLayoutX(10);
        pilot.setLayoutY(100);

        TextField pilotInputField = new TextField();
        pilotInputField.setLayoutX(225);
        pilotInputField.setLayoutY(75);

        Text fighter = new Text("Fighter Skill");
        fighter.setId("grid-prompt");
        fighter.setLayoutX(10);
        fighter.setLayoutY(160);

        TextField fighterInputField = new TextField();
        fighterInputField.setLayoutX(225);
        fighterInputField.setLayoutY(135);

        Text merchant = new Text("Merchant Skill");
        merchant.setId("grid-prompt");
        merchant.setLayoutX(10);
        merchant.setLayoutY(220);

        TextField merchantInputField = new TextField();
        merchantInputField.setLayoutX(225);
        merchantInputField.setLayoutY(195);

        Text engineer = new Text("Engineer Skill");
        engineer.setId("grid-prompt");
        engineer.setLayoutX(10);
        engineer.setLayoutY(280);

        TextField engineerInputField = new TextField();
        engineerInputField.setLayoutX(225);
        engineerInputField.setLayoutY(255);

        Text message = new Text(String.format("You have %d points in total", state.getPoint()));
        message.setX(38);
        message.setY(355);
        message.setId("pop-up-message");

        Button next = new Button("Next -->");
        next.setStyle("-fx-font-size: 2em; ");
        next.setId("font_button");
        next.setLayoutX(55);
        next.setLayoutY(400);
        next.setMaxSize(300, 60);
        next.setMinSize(300, 60);

        Pane floatingCardContent = new Pane();
        floatingCardContent.setPadding(new Insets(10, 10, 10, 10));
        floatingCardContent.getChildren().addAll(name, nameInputField, pilot,
                pilotInputField, fighter, fighterInputField, merchant,
                merchantInputField, engineer, engineerInputField, message, next);

        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        floatingCard.getChildren().addAll(floatingCardBG, floatingCardContent);
        floatingCard.getStylesheets().add("/style.css");

        initialConfigBGPane.getChildren().addAll(initialConfigIV, floatingCard);

        initialConfigBGPane.getStylesheets().add("/style.css");

        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!nameInputField.getText().equals("")
                        && !pilotInputField.getText().equals("")
                        && !fighterInputField.getText().equals("")
                        && !merchantInputField.getText().equals("")
                        && !engineerInputField.getText().equals("")) {

                    name.setText("Name");
                    name.setId("grid-prompt");
                    pilot.setText("Pilot Skill");
                    pilot.setId("grid-prompt");
                    fighter.setText("Fighter Skill");
                    fighter.setId("grid-prompt");
                    merchant.setText("Merchant Skill");
                    merchant.setId("grid-prompt");
                    engineer.setText("Engineer Skill");
                    engineer.setId("grid-prompt");

                    if (isInteger(pilotInputField.getText())
                            && isInteger(fighterInputField.getText())
                            && isInteger(merchantInputField.getText())
                            && isInteger(engineerInputField.getText())) {
                        String playerName = nameInputField.getText();
                        int pilotSkill = Integer.parseInt(pilotInputField.getText());
                        int fightSkill = Integer.parseInt(fighterInputField.getText());
                        int merchantSkill = Integer.parseInt(merchantInputField.getText());
                        int engineerSkill = Integer.parseInt(engineerInputField.getText());
                        if ((pilotSkill + fightSkill
                                + merchantSkill + engineerSkill) == state.getPoint()) {
                            state.setName(playerName);
                            state.setPilotPoint(pilotSkill);
                            state.setFighterPoint(fightSkill);
                            state.setEngineerPoint(engineerSkill);
                            state.setMerchantPoint(merchantSkill);
                        } else {
                            message.setText(String.format(
                                    "The sum of all skill points must equals to %d",
                                    state.getPoint()));
                            message.setId("skill-not-equal_message");
                        }
                    } else {
                        message.setText(String.format("You have %d points in total",
                                state.getPoint()));
                        message.setId("pop-up-message");
                        if (!isInteger(pilotInputField.getText())) {
                            pilot.setText("Pilot Skill (Only Integer)");
                            pilot.setId("skill-not-null-message");
                        } else {
                            pilot.setText("Pilot Skill");
                            pilot.setId("grid-prompt");
                        }
                        if (!isInteger(fighterInputField.getText())) {
                            fighter.setText("Fighter Skill (Only Integer)");
                            fighter.setId("skill-not-null-message");
                        } else {
                            fighter.setText("Fighter Skill");
                            fighter.setId("grid-prompt");
                        }
                        if (!isInteger(merchantInputField.getText())) {
                            merchant.setText("Merchant Skill (Only Integer)");
                            merchant.setId("skill-not-null-message");
                        } else {
                            merchant.setText("Merchant Skill");
                            merchant.setId("grid-prompt");
                        }
                        if (!isInteger(engineerInputField.getText())) {
                            engineer.setText("Engineer Skill (Only Integer)");
                            engineer.setId("skill-not-null-message");
                        } else {
                            engineer.setText("Engineer Skill");
                            engineer.setId("grid-prompt");
                        }
                    }
                } else {
                    message.setText(String.format("You have %d points in total", state.getPoint()));
                    message.setId("pop-up-message");
                    if (nameInputField.getText().equals("")) {
                        name.setText("Name (Cannot be empty)");
                        name.setId("name-not-null-message");
                    } else {
                        name.setText("Name");
                        name.setId("grid-prompt");
                    }
                    if (pilotInputField.getText().equals("")) {
                        pilot.setText("Pilot Skill (Cannot be empty)");
                        pilot.setId("skill-not-null-message");
                    } else {
                        pilot.setText("Pilot Skill");
                        pilot.setId("grid-prompt");
                    }
                    if (fighterInputField.getText().equals("")) {
                        fighter.setText("Fighter Skill (Cannot be empty)");
                        fighter.setId("skill-not-null-message");
                    } else {
                        fighter.setText("Fighter Skill");
                        fighter.setId("grid-prompt");
                    }
                    if (merchantInputField.getText().equals("")) {
                        merchant.setText("Merchant Skill (Cannot be empty)");
                        merchant.setId("skill-not-null-message");
                    } else {
                        merchant.setText("Merchant Skill");
                        merchant.setId("grid-prompt");
                    }
                    if (engineerInputField.getText().equals("")) {
                        engineer.setText("Engineer Skill (Cannot be empty)");
                        engineer.setId("skill-not-null-message");
                    } else {
                        engineer.setText("Engineer Skill");
                        engineer.setId("grid-prompt");
                    }
                }
                try {
                    if (state.getEngineerPoint() != 0
                            && state.getFighterPoint() != 0
                            && state.getMerchantPoint() != 0
                            && state.getPilotPoint() != 0) {
                        Scene characterScene = getSheet(primaryStage);
                        primaryStage.setScene(characterScene);
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return new Scene(initialConfigBGPane, 1280, 720);
    }

    public Scene getUniverseMap(Stage primaryStage) throws FileNotFoundException {
        GridPane universe = new GridPane();
        ArrayList<Integer> existingPosXList = new ArrayList();
        ArrayList<Integer> existingPosYList = new ArrayList();
        HashMap<String, Integer> existingPosX = new HashMap<String, Integer>();
        HashMap<String, Integer> existingPosY = new HashMap<String, Integer>();
        if (!state.isRegionRandomized()) {
            for (Region reg : Region.values()) {
                Random r = new Random();
                int currX = r.nextInt(5);
                int currY = r.nextInt(4);
                while (!isUniqueCoordinate(existingPosXList,
                        existingPosYList, currX, currY)) {
                    currX = r.nextInt(5);
                    currY = r.nextInt(4);
                }
                existingPosXList.add(currX);
                existingPosYList.add(currY);
                existingPosX.put(reg.getName(), currX);
                existingPosY.put(reg.getName(), currY);
                state.setRegionX(existingPosX);
                state.setRegionY(existingPosY);

            }
            state.setRegionRandomized(true);
            return getUniverseMap(primaryStage);
        } else {
            for (Region reg : Region.values()) {
                StackPane currRegion = new StackPane();
                Image lockImg = new Image(new FileInputStream("images/lock.jpg"));
                ImageView lockImgView = new ImageView(lockImg);
                lockImgView.setFitWidth(40);
                lockImgView.setFitHeight(40);
                currRegion.setMinSize(256, 180);
                currRegion.setMaxSize(256, 180);
                Image regionImg = new Image(new FileInputStream(reg.getImageUrl()));
                ImageView regionImgView = new ImageView(regionImg);
                regionImgView.setFitWidth(256);
                regionImgView.setFitHeight(180);
                currRegion.getChildren().add(regionImgView);
                Random r = new Random();
                int currX = state.getRegionX().get(reg.getName());
                int currY = state.getRegionY().get(reg.getName());
                int startX = state.getRegionX().get(state.getCurrRegion().getName());
                int startY = state.getRegionY().get(state.getCurrRegion().getName());
                int dist = (int) Math.sqrt(Math.pow((currX - startX), 2)
                        + Math.pow((currY - startY), 2));
                Text distance = new Text("Distance: " + dist);
                Text xCord = new Text("X: " + currX);
                Text yCord = new Text("Y: " + currY);
                if (!state.getUnlockedRegion().contains(reg)) {
                    VBox regionInfoButton = new VBox();
                    regionInfoButton.setAlignment(Pos.CENTER);
                    regionInfoButton.getChildren().addAll(lockImgView, distance, xCord, yCord);
                    currRegion.getChildren().addAll(regionInfoButton);
                } else {
                    HBox buttons = new HBox();
                    buttons.setAlignment(Pos.CENTER);
                    Button detail = new Button("Detail");
                    if (state.getCurrRegion() == reg) {
                        Button ship = new Button("Ship");
                        Button upgrade = new Button("Upgrade");
                        ship.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    primaryStage.setScene(getShip(primaryStage));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        upgrade.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    primaryStage.setScene(getUpgrade(primaryStage));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        buttons.getChildren().addAll(detail, ship, upgrade);
                    } else {
                        buttons.getChildren().addAll(detail);
                    }
                    class DetailHandler implements EventHandler<ActionEvent> {
                        private final Region finalReg;
                        private final int finalX;
                        private final int finalY;

                        DetailHandler(Region finalReg, int finalX, int finalY) {
                            this.finalReg = finalReg;
                            this.finalX = finalX;
                            this.finalY = finalY;
                        }

                        public void handle(ActionEvent e) {
                            try {
                                primaryStage.setScene(getRegionDetailPage(primaryStage,
                                        finalReg, finalX, finalY));
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    detail.setOnAction(new DetailHandler(reg, currX, currY));
                    VBox regionInfoButton = new VBox();
                    regionInfoButton.setAlignment(Pos.CENTER);
                    regionInfoButton.getChildren().addAll(buttons, distance, xCord, yCord);
                    currRegion.getChildren().addAll(regionInfoButton);
                }
                universe.add(currRegion, currX, currY, 1, 1);
            }
        }
        universe.setStyle("-fx-background-color: #000000;");
        return new Scene(universe, 1280, 720);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public Scene getSheet(Stage primaryStage) throws FileNotFoundException {
        StackPane stackPane = new StackPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        hbox.getChildren().add(vbox);
        Image background = new Image(new FileInputStream("images/display.jpg"));
        ImageView imageView1 = new ImageView(background);
        stackPane.getChildren().add(imageView1);
        stackPane.getChildren().add(hbox);
        Button startGame = new Button("START");
        startGame.setPrefSize(100, 75);
        startGame.setId("record-sales");
        startGame.getStylesheets().add("/style.css");
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        stackPane.getChildren().add(startGame);
        vbox.setAlignment(Pos.CENTER);
        Text name = new Text(String.format("Name: %s", state.getName()));
        name.setId("name_title");
        DifficultyLevel difficulty = state.getDifficultyLevel();
        Text difficultyLevel;
        Text credit = new Text("Credit: " + state.getCredit());
        switch (difficulty) {
        case EASY:
            difficultyLevel = new Text("Easy");
            difficultyLevel.setId("easy");
            credit.setId("easy");
            break;
        case NORMAL:
            difficultyLevel = new Text("Normal");
            difficultyLevel.setId("normal");
            credit.setId("normal");
            break;
        case DIFFICULT:
            difficultyLevel = new Text("Difficult");
            difficultyLevel.setId("difficult");
            credit.setId("difficult");
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + difficulty);
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Pilot", state.getPilotPoint()),
                        new PieChart.Data("Fighter", state.getFighterPoint()),
                        new PieChart.Data("Merchant", state.getMerchantPoint()),
                        new PieChart.Data("Engineer", state.getEngineerPoint()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Skill Points Allocation");
        pieChartData.forEach(data -> data.nameProperty().bind(
                Bindings.concat(
                        data.getName(), "\n", data.getPieValue(), " pts"
                )
        ));

        vbox.getChildren().addAll(name, difficultyLevel, credit, chart);
        Scene scene = new Scene(stackPane, 1280, 720);
        scene.getStylesheets().add("/style.css");
        return scene;
    }

    public Scene getMarket(Stage primaryStage) throws FileNotFoundException {
        StackPane stackPane = new StackPane();

        stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        HBox whole = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(100);
        whole.getChildren().add(vbox);
        stackPane.getChildren().addAll(whole);
        stackPane.setPadding(new Insets(30, 30, 30, 30));
        Text marketTitle = new Text("Marketplace");
        marketTitle.setFont(Font.font(64));
        marketTitle.setFill(Color.WHITE);
        HBox content = new HBox();
        Set<Good> comboBoxList = state.getCurrMarket().getAvailableGoods().keySet();
        ComboBox<Good> comboBox = new ComboBox(FXCollections.observableArrayList(comboBoxList));
        Label selected = new Label("default item selected");
        HBox priceBox = new HBox();
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                selected.setText(comboBox.getValue() + " selected");
                priceBox.getChildren().clear();
                HBox price = new HBox();
                Text buyPrice = new Text("Buy price is "
                        + state.getCurrMarket().getMerchantNPC().calculateBuyPrice(
                                comboBox.getValue(), state));
                buyPrice.setFill(Color.WHITE);
                Text sellPrice = new Text("Sell price is "
                        + state.getCurrMarket().getMerchantNPC().calculateSellPrice(
                                comboBox.getValue(), state));
                sellPrice.setFill(Color.WHITE);
                Text currCredit = new Text("Credit Available "
                        + state.getCredit());
                currCredit.setFill(Color.WHITE);
                Text avalCargo = new Text("Cargo Available "
                        + (state.getShip().getCargoCapacity()
                        - state.getShip().getCurrItemsNum()));
                avalCargo.setFill(Color.WHITE);
                Text itemNumber = new Text("There are "
                        + state.getShip().numOfGood(comboBox.getValue())
                        + " " + comboBox.getValue().getName());
                itemNumber.setFill(Color.WHITE);
                price.getChildren().addAll(buyPrice, sellPrice, currCredit, avalCargo, itemNumber);
                price.setSpacing(10);
                priceBox.getChildren().add(price);
            }
        };

        comboBox.setOnAction(event);
        content.getChildren().add(comboBox);
        content.setAlignment(Pos.CENTER);


        HBox hbox = new HBox();
        Button buy = new Button("Buy");
        Button sell = new Button("Sell");
        Button exit = new Button("Return to Universe");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(inRegion(primaryStage, state.getCurrRegion()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                state.getCurrMarket().getMerchantNPC().placeBuyOrder(comboBox.getValue(), state);
                selected.setText(comboBox.getValue() + " selected");
                priceBox.getChildren().clear();
                HBox price = new HBox();
                Text buyPrice = new Text("Buy price is "
                        + state.getCurrMarket().getMerchantNPC().calculateBuyPrice(
                        comboBox.getValue(), state));
                buyPrice.setFill(Color.WHITE);
                Text sellPrice = new Text("Sell price is "
                        + state.getCurrMarket().getMerchantNPC().calculateSellPrice(
                        comboBox.getValue(), state));
                sellPrice.setFill(Color.WHITE);
                Text currCredit = new Text("Credit Available " + state.getCredit());
                currCredit.setFill(Color.WHITE);
                Text avalCargo = new Text("Cargo Available " + (state.getShip().getCargoCapacity()
                        - state.getShip().getCurrItemsNum()));
                avalCargo.setFill(Color.WHITE);
                Text itemNumber = new Text("There are "
                        + state.getShip().numOfGood(comboBox.getValue())
                        + " " + comboBox.getValue().getName());
                itemNumber.setFill(Color.WHITE);
                price.getChildren().addAll(buyPrice, sellPrice, currCredit, avalCargo, itemNumber);
                price.setSpacing(10);
                priceBox.getChildren().add(price);
                if (endGameHandler.isWin()) {
                    primaryStage.setScene(win(primaryStage));
                }
            }
        });
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(comboBox.getValue());
                state.getCurrMarket().getMerchantNPC().placeSellOrder((comboBox.getValue()), state);
                selected.setText(comboBox.getValue() + " selected");
                priceBox.getChildren().clear();
                HBox price = new HBox();
                Text buyPrice = new Text("Buy price is "
                        + state.getCurrMarket().getMerchantNPC().calculateBuyPrice(
                        comboBox.getValue(), state));
                buyPrice.setFill(Color.WHITE);
                Text sellPrice = new Text("Sell price is "
                        + state.getCurrMarket().getMerchantNPC().calculateSellPrice(
                        comboBox.getValue(), state));
                sellPrice.setFill(Color.WHITE);
                Text currCredit = new Text("Credit Available " + state.getCredit());
                currCredit.setFill(Color.WHITE);
                Text avalCargo = new Text("Cargo Available " + (state.getShip().getCargoCapacity()
                        - state.getShip().getCurrItemsNum()));
                avalCargo.setFill(Color.WHITE);
                Text itemNumber = new Text("There are "
                        + state.getShip().numOfGood(comboBox.getValue())
                        + " " + comboBox.getValue().getName());
                itemNumber.setFill(Color.WHITE);
                price.getChildren().addAll(buyPrice, sellPrice, currCredit, avalCargo, itemNumber);
                price.setSpacing(10);
                priceBox.getChildren().add(price);
            }
        });
        hbox.getChildren().addAll(buy, sell, exit);
        hbox.setSpacing(80);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(marketTitle, content, priceBox, hbox);
        vbox.setAlignment(Pos.CENTER);

        Image merchant = new Image(new FileInputStream("images/merchant.png"));
        ImageView merchantIm = new ImageView(merchant);
        merchantIm.setFitWidth(800);
        merchantIm.setPreserveRatio(true);
        whole.getChildren().add(merchantIm);

        Scene scene = new Scene(stackPane, 1280, 720);
        scene.getStylesheets().add("/style.css");
        return scene;
    }

    public Scene getUpgrade(Stage primaryStage) throws FileNotFoundException {
        for (Good gd : state.getCurrUpgrade().keySet()) {
            switch (gd) {
            case SUIT:
                state.setMerchantPoint(state.getMerchantPoint()
                        - state.getCurrUpgrade().get(Good.SUIT));
                break;
            case COAT:
                state.setEngineerPoint(state.getEngineerPoint()
                        - state.getCurrUpgrade().get(Good.COAT));
                break;
            case ARMOR:
                state.setPilotPoint(state.getPilotPoint() - state.getCurrUpgrade().get(Good.ARMOR));
                break;
            case INVISIBLE_CLOAK:
                state.setFighterPoint(state.getFighterPoint()
                        - state.getCurrUpgrade().get(Good.INVISIBLE_CLOAK));
                break;
            default:
            }
        }
        StackPane stackPane = new StackPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        hbox.getChildren().add(vbox);
        Image background = new Image(new FileInputStream("images/display.jpg"));
        ImageView imageView1 = new ImageView(background);
        stackPane.getChildren().add(imageView1);
        stackPane.setPadding(new Insets(30, 30, 30, 30));

        Button confirm = new Button("COMFIRM");
        confirm.setPrefSize(100, 75);
        confirm.setId("record-sales");
        confirm.getStylesheets().add("/style.css");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        Button exit = new Button("Exit");
        exit.setPrefSize(100, 75);
        exit.setId("record-sales");
        exit.getStylesheets().add("/style.css");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        VBox buttonPane = new VBox(confirm, exit);
        buttonPane.setSpacing(20);
        buttonPane.setAlignment(Pos.CENTER);
        HBox buttonCombo = new HBox();
        buttonCombo.getChildren().addAll(hbox, buttonPane);
        buttonCombo.setSpacing(30);
        stackPane.getChildren().add(buttonCombo);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        ComboBox<String>[] comboBoxList = new ComboBox[4];
        for (int i = 1; i < 5; i++) {
            HBox currUpgrade = new HBox();
            Text currUpgradeText = new Text("Upgrade #" + i);
            currUpgradeText.setId("name_title");
            ComboBox<String> currUpgradeBox = new ComboBox();
            comboBoxList[i - 1] = currUpgradeBox;
            ArrayList<String> cbContent = new ArrayList();
            cbContent.add("<Empty>");
            currUpgradeBox.setValue("<Empty>");
            for (Good g : state.getShip().getItemInventory().keySet()) {
                if (g.getGoodsType() == GoodType.CLOTHES) {
                    cbContent.add(g.name());
                }
            }
            currUpgradeBox.setItems(FXCollections.observableArrayList(cbContent));
            currUpgrade.getChildren().addAll(currUpgradeText, currUpgradeBox);
            vbox.getChildren().add(currUpgrade);
        }
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<Good, Integer> choice = new HashMap();
                choice.put(Good.ARMOR, 0);
                choice.put(Good.COAT, 0);
                choice.put(Good.INVISIBLE_CLOAK, 0);
                choice.put(Good.SUIT, 0);
                for (ComboBox<String> com : comboBoxList) {
                    switch (com.getValue()) {
                        case "SUIT":
                            choice.replace(Good.SUIT, choice.get(Good.SUIT) + 1);
                            break;
                        case "COAT":
                            choice.replace(Good.COAT, choice.get(Good.COAT) + 1);
                            break;
                        case "ARMOR":
                            choice.replace(Good.ARMOR, choice.get(Good.ARMOR) + 1);
                            break;
                        case "INVISIBLE_CLOAK":
                            choice.replace(Good.INVISIBLE_CLOAK,
                                    choice.get(Good.INVISIBLE_CLOAK) + 1);
                            break;
                        default:
                    }
                }
                for (Good gd : choice.keySet()) {
                    if (state.getShip().getItemInventory().get(gd) < 1) {
                        // Error Msg
                        return;
                    }
                }
                state.setCurrUpgrade(choice);
                for (Good gd : state.getCurrUpgrade().keySet()) {
                    switch (gd) {
                        case SUIT:
                            state.setMerchantPoint(state.getMerchantPoint()
                                    + state.getCurrUpgrade().get(Good.SUIT));
                            break;
                        case COAT:
                            state.setEngineerPoint(state.getEngineerPoint()
                                    + state.getCurrUpgrade().get(Good.COAT));
                            break;
                        case ARMOR:
                            state.setPilotPoint(state.getPilotPoint()
                                    + state.getCurrUpgrade().get(Good.ARMOR));
                            break;
                        case INVISIBLE_CLOAK:
                            state.setFighterPoint(state.getFighterPoint()
                                    + state.getCurrUpgrade().get(Good.INVISIBLE_CLOAK));
                            break;
                        default:
                    }
                }
            }
        });
        Scene scene = new Scene(stackPane, 1280, 720);
        scene.getStylesheets().add("/style.css");
        return scene;
    }

    public Scene getRegionDetailPage(Stage primaryStage, Region region,
                                     int currX, int currY) throws FileNotFoundException {
        int dist = (int) Math.sqrt(Math.pow((currX - state.getRegionX().get(
                state.getCurrRegion().getName())), 2)
                + Math.pow((currY - state.getRegionY().get(state.getCurrRegion().getName())), 2));
        StackPane background = new StackPane();
        background.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane pane = new BorderPane();
        Image regionImg = new Image(new FileInputStream(region.getImageUrl()));
        ImageView regionImgView = new ImageView(regionImg);
        regionImgView.setFitWidth(500);
        regionImgView.setPreserveRatio(true);
        background.getChildren().add(regionImgView);
        background.getChildren().add(pane);
        HBox content = new HBox();
        VBox information = new VBox();
        content.getChildren().add(information);
        Text title = new Text("Region Information");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);
        pane.setTop(title);
        pane.setCenter(content);
        Text coordinates = new Text("This Region's coordinates are: ("
                + currX + ", " + currY + ").");
        Text name = new Text("The name of this region is: " + region.getName() + ".");
        Text techLevel = new Text("The technology level of this region is "
                + region.getTechnologyLevel() + ".");
        coordinates.setFont(Font.font(20));
        coordinates.setFill(Color.LIGHTSLATEGRAY);
        name.setFont(Font.font(20));
        name.setFill(Color.LIGHTSLATEGRAY);
        techLevel.setFont(Font.font(20));
        techLevel.setFill(Color.LIGHTSLATEGRAY);
        information.getChildren().add(coordinates);
        information.getChildren().add(name);
        information.getChildren().add(techLevel);
        information.setSpacing(15);
        HBox buttons = new HBox();
        Button travelTo = new Button("Travel to here");
        travelTo.setPrefSize(150, 50);
        pane.setBottom(buttons);
        pane.setAlignment(content, Pos.CENTER);
        Button backToUniverse = new Button("Back to the Universe");
        backToUniverse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        travelTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String result = state.travel(dist, region);
                if (result == null) {
                    try {
                        primaryStage.setScene(inRegion(primaryStage, region));
                        state.setCurrRegion(region);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (result == "Sorry, you don't have enough fuel.") {
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    Button close = new Button("close");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent e) {
                            warrningMessage.close();
                        }
                    });
                    Text message = new Text("Sorry, you don't have enough fuel. Current fuel: "
                            + state.getShip().getFuelLeft() + ". Fuel needed: " + dist * 10);
                    messageBox.getChildren().add(message);
                    background.getChildren().add(messageBox);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
                } else if (result == "Sorry, your ship has health level of 0, please repair") {
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    Button close = new Button("close");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent e) {
                            warrningMessage.close();
                        }
                    });
                    Text message = new Text("Sorry, your ship has health level "
                            + "of 0, please repair");
                    messageBox.getChildren().add(message);
                    background.getChildren().add(messageBox);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
                } else if (result == "police") {
                    try {
                        primaryStage.setScene(encounterPolice(primaryStage, region));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (result == "bandit") {
                    try {
                        primaryStage.setScene(encounterBandit(primaryStage, region));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        primaryStage.setScene(encounterTrader(primaryStage, region));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        backToUniverse.setPrefSize(200, 50);
        buttons.getChildren().addAll(backToUniverse, travelTo);
        buttons.setSpacing(1000);
        primaryStage.show();
        return new Scene(background, 1280, 720);
    }
    public Scene encounterBandit(Stage primaryStage, Region targetRegion)
            throws FileNotFoundException {
        Bandit nPC = new Bandit("bandit");
        Image npcImage = new Image(new FileInputStream("Images/bandit.png"));
        StackPane initialConfigBGPane = new StackPane();
        initialConfigBGPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Image initialConfigBG = new Image(new FileInputStream(state.getCurrRegion().getImageUrl()));
        //Setting the image view
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        //Setting the position of the image
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        initialConfigIV.setFitWidth(738);
        initialConfigIV.setFitHeight(615);
        //Setting the preserve ratio of the image view
        initialConfigIV.setPreserveRatio(true);
        BorderPane pane = new BorderPane();
        ImageView shipView = new ImageView(npcImage);
        shipView.setFitWidth(420);
        shipView.setPreserveRatio(true);
        pane.setRight(shipView);
        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);
        VBox content = new VBox();
        VBox information = new VBox();
        VBox buttonPane = new VBox();
        Text title = new Text("    You have encrountered a Bandit");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);
        Text welcomeMessage = new Text("    " + nPC.getWelcomeText());
        Text message2 = new Text("    What do you want to do? ");
        Text space1 = new Text("");
        Text space2 = new Text("");
        Text space3 = new Text("");
        Text s1 = new Text("");
        Text s2 = new Text("");
        welcomeMessage.setFont(Font.font(20));
        Button flee = new Button("Flee back");
        flee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    boolean fleed = nPC.flee(state);
                    Text message;
                    if (fleed) {
                        message = new Text(nPC.getFleeSuccessText());
                    } else {
                        message = new Text(nPC.getFleeFailureText());
                    }
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    VBox box = new VBox();
                    Button close = new Button("Close");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                primaryStage.setScene(getRegionDetailPage(primaryStage,
                                        state.getCurrRegion(),
                                        state.getRegionX().get(state.getCurrRegion().getName()),
                                        state.getRegionY().get(state.getCurrRegion().getName())));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    messageBox.getChildren().add(message);
                    box.getChildren().addAll(messageBox, close);
                    background.getChildren().add(box);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
                    if (endGameHandler.isOver()) {
                        primaryStage.setScene(over(primaryStage));
                    }
            }
        });
        information.getChildren().addAll(s1, s2);
        information.getChildren().addAll(welcomeMessage, message2);
        information.getChildren().addAll(space1, space2, space3);
        information.setSpacing(15);
        Button pay = new Button("Pay the amount");
        Button fight = new Button("fight off the Bandit");
        fight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    boolean win = nPC.fight(state);
                    if (win) {
                        state.setCurrRegion(targetRegion);
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        VBox box = new VBox();
                        Button close = new Button("Continue traveling");
                        close.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    primaryStage.setScene(inRegion(primaryStage, targetRegion));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Text message = new Text(nPC.getCombatSuccessText());
                        messageBox.getChildren().add(message);
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    } else {
                        Text message = new Text(nPC.getCombatFailureText()
                                + " You will be sent back to the orginal "
                                + "region");
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        VBox box = new VBox();
                        HBox messageBox = new HBox();
                        Button close = new Button("Close");
                        close.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    primaryStage.setScene(getRegionDetailPage(primaryStage,
                                            state.getCurrRegion(),
                                            state.getRegionX().get(state.getCurrRegion().getName()),
                                            state.getRegionY().get(
                                                    state.getCurrRegion().getName())));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        messageBox.getChildren().add(message);
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    }
                    if (endGameHandler.isOver()) {
                    primaryStage.setScene(over(primaryStage));
                }
            }
        });
        pay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    boolean paied = nPC.pay(state);
                    state.setCurrRegion(targetRegion);
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    VBox box = new VBox();
                    Button close = new Button("Continue traveling");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                primaryStage.setScene(inRegion(primaryStage, targetRegion));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Text message;
                    message = new Text(nPC.getPayFailure());
                    if (paied) {
                        message = new Text(nPC.getPaySuccess());
                    }
                    messageBox.getChildren().add(message);
                    box.getChildren().addAll(messageBox, close);
                    background.getChildren().add(box);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
                if (endGameHandler.isOver()) {
                    primaryStage.setScene(over(primaryStage));
                }
            }
        });
        flee.setMaxSize(150, 50);
        flee.setMinSize(150, 50);
        pay.setMaxSize(150, 50);
        pay.setMinSize(150, 50);
        fight.setMaxSize(150, 50);
        fight.setMinSize(150, 50);
        buttonPane.getChildren().addAll(pay);
        buttonPane.getChildren().add(flee);
        buttonPane.getChildren().add(fight);
        buttonPane.setSpacing(20);
        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        content.getChildren().addAll(information, buttonPane);
        buttonPane.setAlignment(Pos.CENTER);
        floatingCard.getChildren().addAll(floatingCardBG, content);
        pane.setCenter(floatingCard);
        pane.setTop(title);
        initialConfigBGPane.getChildren().addAll(initialConfigIV, pane);
        primaryStage.show();
        return new Scene(initialConfigBGPane, 1280, 720);
    }

    public Scene encounterTrader(Stage primaryStage, Region targetRegion)
            throws FileNotFoundException {
        Trader nPC = new Trader("Trader");
        Image npcImage = new Image(new FileInputStream("Images/merchant.png"));
        StackPane initialConfigBGPane = new StackPane();
        initialConfigBGPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Image initialConfigBG = new Image(new FileInputStream(state.getCurrRegion().getImageUrl()));
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        initialConfigIV.setFitWidth(738);
        initialConfigIV.setFitHeight(615);
        initialConfigIV.setPreserveRatio(true);
        BorderPane pane = new BorderPane();
        ImageView shipView = new ImageView(npcImage);
        shipView.setFitWidth(420);
        shipView.setPreserveRatio(true);
        pane.setRight(shipView);
        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);
        VBox content = new VBox();
        VBox information = new VBox();
        VBox buttonPane = new VBox();
        Text title = new Text("You have encrountered a Trader");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);
        Text welcomeMessage = new Text("    " + nPC.getWelcomeText());
        Text message2 = new Text("    What will you do?");
        Text space1 = new Text("");
        Text space2 = new Text("");
        Text space3 = new Text("");
        Text s1 = new Text("");
        Text s2 = new Text("");
        welcomeMessage.setFont(Font.font(20));
        Button buy = new Button("Buy");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(buyFromTrader(primaryStage, targetRegion));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        information.getChildren().addAll(s1, s2);
        information.getChildren().addAll(welcomeMessage, message2);
        information.getChildren().addAll(space1, space2, space3);
        information.setSpacing(15);
        Button ignore = new Button("Ignore");
        Button rob = new Button("Rob");
        rob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    boolean robbed = nPC.rob(state);
                    Button close = new Button("Close");
                    if (robbed) {
                        state.setCurrRegion(targetRegion);
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        Text message = new Text(nPC.getCombatSuccessText() + " You have robbed "
                                + nPC.getNumberRobbed() + " of " + nPC.getRobbedGood());
                        messageBox.getChildren().add(message);
                        VBox box = new VBox();
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    } else {
                        Text message = new Text(nPC.getCombatFailureText()
                                + "Your ship has been damaged by 5 points");
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        messageBox.getChildren().add(message);
                        VBox box = new VBox();
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    }
                close.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            primaryStage.setScene(inRegion(primaryStage, targetRegion));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (endGameHandler.isOver()) {
                    primaryStage.setScene(over(primaryStage));
                }
                }
        });
        ignore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    state.setCurrRegion(targetRegion);
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    Button close = new Button("Continue traveling");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                primaryStage.setScene(inRegion(primaryStage, targetRegion));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Text message = new Text("You ignored the trader and will keep traveling to "
                            + targetRegion.getName());
                    messageBox.getChildren().add(message);
                    VBox box = new VBox();
                    box.getChildren().addAll(messageBox, close);
                    background.getChildren().add(box);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
            }
        });
        buy.setMaxSize(150, 50);
        buy.setMinSize(150, 50);
        ignore.setMaxSize(150, 50);
        ignore.setMinSize(150, 50);
        rob.setMaxSize(150, 50);
        rob.setMinSize(150, 50);
        buttonPane.getChildren().addAll(ignore);
        buttonPane.getChildren().add(buy);
        buttonPane.getChildren().add(rob);
        buttonPane.setSpacing(20);
        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        content.getChildren().addAll(information, buttonPane);
        buttonPane.setAlignment(Pos.CENTER);
        floatingCard.getChildren().addAll(floatingCardBG, content);
        pane.setCenter(floatingCard);
        pane.setTop(title);
        initialConfigBGPane.getChildren().addAll(initialConfigIV, pane);
        primaryStage.show();
        return new Scene(initialConfigBGPane, 1280, 720);
    }
    public Scene buyFromTrader(Stage primaryStage, Region targetRegion)
            throws FileNotFoundException {
        Trader nPC = new Trader("Trader");
        StackPane stackPane = new StackPane();
        stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        HBox whole = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(100);
        whole.getChildren().add(vbox);
        stackPane.getChildren().addAll(whole);
        stackPane.setPadding(new Insets(30, 30, 30, 30));
        Text marketTitle = new Text("Buy From Trader");
        marketTitle.setFont(Font.font(64));
        marketTitle.setFill(Color.WHITE);
        HBox content = new HBox();
        Set<Good> comboBoxList = nPC.getInventory().keySet();
        ComboBox<Good> comboBox = new ComboBox(FXCollections.observableArrayList(comboBoxList));
        Label selected = new Label("default item selected");
        VBox information = new VBox();
        Button back = new Button("Back to trader");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(encounterTrader(primaryStage, targetRegion));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                selected.setText(comboBox.getValue() + " selected");
                information.getChildren().clear();
                HBox priceOne = new HBox();
                HBox priceTwo = new HBox();
                VBox priceWrapper = new VBox();
                Text buyPrice = new Text(String.valueOf(nPC.getGoodPrice(comboBox.getValue())));
                Button negotiate = new Button("Negotiate price");
                negotiate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        boolean negotiated = nPC.negotiate(state, comboBox.getValue());
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        Button close = new Button("Back to purchase");
                        close.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                warrningMessage.close();
                            }
                        });
                        Text message;
                        if (negotiated) {
                            int price = nPC.getGoodPrice(comboBox.getValue());
                            message = new Text("You have successfully negotiated the price! "
                                    + "The current Price is " + price);
                        } else {
                            int price = nPC.getGoodPrice(comboBox.getValue());
                            message = new Text("You failed to negotiate the price! "
                                    + "The current Price is " + price);
                        }
                        messageBox.getChildren().add(message);
                        VBox box = new VBox();
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    }
                });
                buyPrice = new Text("Price: "
                        + String.valueOf(nPC.getGoodPrice(comboBox.getValue())));
                buyPrice.setFill(Color.WHITE);
                Text currCredit = new Text("Credit Available: "
                        + state.getCredit());
                currCredit.setFill(Color.WHITE);
                Text avalCargo = new Text("  Cargo Available: "
                        + (state.getShip().getCargoCapacity() - state.getShip().getCurrItemsNum()));
                avalCargo.setFill(Color.WHITE);
                Text itemNumber = new Text("  There are "
                        + state.getShip().numOfGood(comboBox.getValue())
                        + " " + comboBox.getValue().getName());
                itemNumber.setFill(Color.WHITE);
                priceOne.getChildren().addAll(buyPrice, currCredit);
                priceOne.setSpacing(10);
                priceTwo.getChildren().addAll(avalCargo, itemNumber);
                priceTwo.setSpacing(10);
                priceWrapper.getChildren().addAll(priceOne, priceTwo);
                priceWrapper.setSpacing(10);
                information.setSpacing(10);
                information.getChildren().addAll(priceWrapper, negotiate);
            }
        };
        comboBox.setOnAction(event);
        content.getChildren().add(comboBox);
        content.setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        Button buy = new Button("Buy");
        Button exit = new Button("Keep on traveling");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    state.setCurrRegion(targetRegion);
                    primaryStage.setScene(inRegion(primaryStage, targetRegion));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.print(nPC.placePurchase(comboBox.getValue(), 1, state));
                selected.setText(comboBox.getValue() + " selected");
                information.getChildren().clear();
                VBox priceWrapper = new VBox();
                HBox priceOne = new HBox();
                HBox priceTwo = new HBox();
                Text buyPrice = new Text("Price is " + nPC.getGoodPrice(comboBox.getValue()));
                buyPrice.setFill(Color.WHITE);
                Text currCredit = new Text("Credit Available " + state.getCredit());
                currCredit.setFill(Color.WHITE);
                Text avalCargo = new Text("Cargo Available " + (state.getShip().getCargoCapacity()
                        - state.getShip().getCurrItemsNum()));
                avalCargo.setFill(Color.WHITE);
                Text itemNumber = new Text("There are "
                        + state.getShip().numOfGood(comboBox.getValue())
                        + " " + comboBox.getValue().getName());
                itemNumber.setFill(Color.WHITE);
                priceOne.getChildren().addAll(buyPrice, currCredit);
                priceOne.setSpacing(10);
                priceTwo.getChildren().addAll(avalCargo, itemNumber);
                priceTwo.setSpacing(10);
                priceWrapper.getChildren().addAll(priceOne, priceTwo);
                information.getChildren().add(priceWrapper);
                if (endGameHandler.isWin()) {
                    primaryStage.setScene(win(primaryStage));
                }
            }
        });
        hbox.getChildren().addAll(buy, exit, back);
        hbox.setSpacing(80);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(marketTitle, content, information, hbox);
        vbox.setAlignment(Pos.CENTER);
        Image merchant = new Image(new FileInputStream("images/merchant.png"));
        ImageView merchantIm = new ImageView(merchant);
        merchantIm.setFitWidth(800);
        merchantIm.setPreserveRatio(true);
        whole.getChildren().add(merchantIm);
        Scene scene = new Scene(stackPane, 1280, 720);
        scene.getStylesheets().add("/style.css");
        return scene;
    }

    public Scene encounterPolice(Stage primaryStage, Region targetRegion)
            throws FileNotFoundException {
        Police nPC = new Police("police");
        nPC.encounter(state);
        Image npcImage = new Image(new FileInputStream("Images/police 2.png"));
        StackPane initialConfigBGPane = new StackPane();
        initialConfigBGPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Image initialConfigBG = new Image(new FileInputStream(state.getCurrRegion().getImageUrl()));
        //Setting the image view
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        //Setting the position of the image
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        initialConfigIV.setFitWidth(738);
        initialConfigIV.setFitHeight(615);
        //Setting the preserve ratio of the image view
        initialConfigIV.setPreserveRatio(true);
        BorderPane pane = new BorderPane();
        ImageView shipView = new ImageView(npcImage);
        shipView.setFitWidth(420);
        shipView.setPreserveRatio(true);
        pane.setRight(shipView);
        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);
        VBox content = new VBox();
        VBox information = new VBox();
        VBox buttonPane = new VBox();
        Text title = new Text("  You have encrountered a space police");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);
        Text welcomeMessage = new Text("    " + nPC.getWelcomeText());
        Text space1 = new Text("");
        Text space2 = new Text("");
        Text space3 = new Text("");
        Text s1 = new Text("");
        Text s2 = new Text("");
        welcomeMessage.setFont(Font.font(20));
        Button flee = new Button("Flee back");
        flee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    boolean fleed = nPC.flee(state);
                    Text message;
                    if (fleed) {
                        message = new Text(nPC.getFleeSuccessText());
                    } else {
                        message = new Text(nPC.getFleeFailureText());
                    }
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    Button close = new Button("Close");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                primaryStage.setScene(getRegionDetailPage(primaryStage,
                                        state.getCurrRegion(),
                                        state.getRegionX().get(state.getCurrRegion().getName()),
                                        state.getRegionY().get(state.getCurrRegion().getName())));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    messageBox.getChildren().add(message);
                    VBox box = new VBox();
                    box.getChildren().addAll(messageBox, close);
                    background.getChildren().add(box);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
                    if (endGameHandler.isOver()) {
                        primaryStage.setScene(over(primaryStage));
                    }
            }
        });
        information.getChildren().addAll(s1, s2);
        information.getChildren().add(welcomeMessage);
        information.getChildren().addAll(space1, space2, space3);
        information.setSpacing(15);
        Button forfeit = new Button("Forfeit the item");
        Button fight = new Button("fight off the police");
        fight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    boolean win = nPC.fight(state);
                    if (win) {
                        state.setCurrRegion(targetRegion);
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        Button close = new Button("Continue traveling");
                        close.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    primaryStage.setScene(inRegion(primaryStage, targetRegion));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Text message = new Text(nPC.getCombatSuccessText());
                        messageBox.getChildren().add(message);
                        VBox box = new VBox();
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    } else {
                        Text message = new Text(nPC.getCombatFailureText());
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        Button close = new Button("Close");
                        close.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    primaryStage.setScene(getRegionDetailPage(primaryStage,
                                            state.getCurrRegion(),
                                            state.getRegionX().get(
                                                    state.getCurrRegion().getName()),
                                            state.getRegionY().get(
                                                    state.getCurrRegion().getName())));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        messageBox.getChildren().add(message);
                        VBox box = new VBox();
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    }
                    if (endGameHandler.isOver()) {
                        primaryStage.setScene(over(primaryStage));
                    }
            }
        });
        forfeit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    nPC.forfeit(state);
                    state.setCurrRegion(targetRegion);
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    Button close = new Button("Continue traveling");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                primaryStage.setScene(inRegion(primaryStage, targetRegion));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Text message = new Text("You have successfully forfeit"
                            + nPC.getTargetItem().getName());
                    messageBox.getChildren().add(message);
                    VBox box = new VBox();
                    box.getChildren().addAll(messageBox, close);
                    background.getChildren().add(box);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
            }
        });
        flee.setMaxSize(150, 50);
        flee.setMinSize(150, 50);
        forfeit.setMaxSize(150, 50);
        forfeit.setMinSize(150, 50);
        fight.setMaxSize(150, 50);
        fight.setMinSize(150, 50);
        buttonPane.getChildren().addAll(forfeit);
        buttonPane.getChildren().add(flee);
        buttonPane.getChildren().add(fight);
        buttonPane.setSpacing(20);
        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        content.getChildren().addAll(information, buttonPane);
        buttonPane.setAlignment(Pos.CENTER);
        floatingCard.getChildren().addAll(floatingCardBG, content);
        pane.setCenter(floatingCard);
        pane.setTop(title);
        initialConfigBGPane.getChildren().addAll(initialConfigIV, pane);
        primaryStage.show();
        return new Scene(initialConfigBGPane, 1280, 720);
    }

    public Scene over(Stage primaryStage) {
        StackPane sp = new StackPane();
        VBox vbox = new VBox();
        Text lose = new Text("You Lost!");
        Text endCredit = new Text("End Credit: " + state.getCredit());
        Button start = new Button("Start");
        EventHandler<ActionEvent> startButtonHdl = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Scene characterScene = null;
                state = new AppState();
                try {
                    characterScene = getInitialConfigOne(primaryStage);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(characterScene);
            }
        };
        start.setOnAction(startButtonHdl);
        vbox.getChildren().addAll(lose, endCredit, start);
        vbox.setAlignment(Pos.CENTER);
        lose.setTextAlignment(TextAlignment.CENTER);
        lose.setFont(new Font(58));
        sp.getChildren().add(vbox);
        return new Scene(sp, 1280, 720);
    }

    public Scene win(Stage primaryStage) {
        StackPane sp = new StackPane();
        VBox vbox = new VBox();
        Text lose = new Text("You Win!");
        Text endCredit = new Text("End Credit: " + state.getCredit());
        Button start = new Button("Start");
        EventHandler<ActionEvent> startButtonHdl = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Scene characterScene = null;
                state = new AppState();
                try {
                    characterScene = getInitialConfigOne(primaryStage);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                primaryStage.setScene(characterScene);
            }
        };
        start.setOnAction(startButtonHdl);
        vbox.getChildren().addAll(lose, endCredit, start);
        vbox.setAlignment(Pos.CENTER);
        lose.setTextAlignment(TextAlignment.CENTER);
        lose.setFont(new Font(58));
        sp.getChildren().add(vbox);
        return new Scene(sp, 1280, 720);
    }

    public boolean isUniqueCoordinate(ArrayList<Integer> existingX,
                                      ArrayList<Integer> existingY, int currX, int currY) {
        for (int i = 0; i < existingX.size(); i++) {
            if (existingX.get(i) == currX && existingY.get(i) == currY) {
                return false;
            }
        }
        return true;
    }
    public Scene inRegion(Stage primaryStage, Region region) throws FileNotFoundException {
        StackPane background = new StackPane();
        background.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane pane = new BorderPane();
        Image regionImg = new Image(new FileInputStream(region.getImageUrl()));
        ImageView regionImgView = new ImageView(regionImg);
        regionImgView.setFitWidth(500);
        regionImgView.setPreserveRatio(true);
        background.getChildren().add(regionImgView);
        background.getChildren().add(pane);
        HBox content = new HBox();
        VBox information = new VBox();
        content.getChildren().add(information);
        Text title = new Text("You are currently in Region "
                + state.getCurrRegion().getName() + ".");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);
        pane.setTop(title);
        pane.setCenter(content);
        Text techLevel = new Text("The technology level of this region is "
                + region.getTechnologyLevel() + ".");

        techLevel.setFont(Font.font(20));
        techLevel.setFill(Color.LIGHTSLATEGRAY);
        Text desciprion = new Text(region.getDescription());
        desciprion.setFont(Font.font(20));
        desciprion.setFill(Color.LIGHTSLATEGRAY);

        information.getChildren().add(techLevel);
        information.setSpacing(15);
        information.getChildren().add(desciprion);
        information.setSpacing(15);
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        background.getChildren().add(buttons);
        Button market = new Button("Market");
        market.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getMarket(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        Button battle = new Button("Battle");
        market.setPrefSize(150, 50);
        battle.setPrefSize(150, 50);
        //pane.setCenter(buttons);
        pane.setAlignment(content, Pos.CENTER);
        Button backToUniverse = new Button("Back to the Universe");
        backToUniverse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        backToUniverse.setPrefSize(200, 50);
        buttons.getChildren().addAll(market, battle, backToUniverse);
        buttons.setSpacing(150);
        primaryStage.show();
        return new Scene(background, 1280, 720);
    }
    public Scene getShip(Stage primaryStage) throws FileNotFoundException {
        StackPane initialConfigBGPane = new StackPane();
        initialConfigBGPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Image initialConfigBG = new Image(new FileInputStream(state.getCurrRegion().getImageUrl()));
        //Setting the image view
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        //Setting the position of the image
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        initialConfigIV.setFitWidth(738);
        initialConfigIV.setFitHeight(615);
        //Setting the preserve ratio of the image view
        initialConfigIV.setPreserveRatio(true);
        BorderPane pane = new BorderPane();
        Image ship = new Image(new FileInputStream(state.getShip().getImageUrl()));
        ImageView shipView = new ImageView(ship);
        shipView.setFitWidth(420);
        shipView.setPreserveRatio(true);
        pane.setRight(shipView);
        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);
        VBox content = new VBox();
        VBox information = new VBox();
        VBox buttonPane = new VBox();
        Text title = new Text("                                                       "
                + " Ship Information");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);

        Text shipName = new Text("        Ship name: "  + state.getShip().getName());
        Text fuel = new Text("        Fuel: " + state.getShip().getFuelLeft() + "/"
                + state.getShip().getFuelCapacity());
        Text cargo = new Text("        Cargo: " + state.getShip().getCurrItemsNum() + "/"
                + state.getShip().getCargoCapacity());
        Text health = new Text("        Health: "  + state.getShip().getHealth());
        Text space1 = new Text("");
        Text space2 = new Text("");
        Text space3 = new Text("");
        Text s1 = new Text("");
        Text s2 = new Text("");
        shipName.setFont(Font.font(20));
        fuel.setFont(Font.font(20));
        cargo.setFont(Font.font(20));
        health.setFont(Font.font(20));
        Button addFuel = new Button("add Fuel");
        information.getChildren().addAll(s1, s2);
        information.getChildren().add(shipName);
        information.getChildren().add(fuel);
        information.getChildren().add(cargo);
        information.getChildren().add(health);
        information.getChildren().addAll(space1, space2, space3);
        information.setSpacing(15);
        Button repair = new Button("Repair Ship");
        addFuel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(addFuel(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        repair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Text message = new Text(state.getShip().repair(state));
                Stage warrningMessage = new Stage();
                warrningMessage.initModality(Modality.APPLICATION_MODAL);
                warrningMessage.initOwner(primaryStage);
                StackPane background = new StackPane();
                background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                        CornerRadii.EMPTY, Insets.EMPTY)));
                VBox box = new VBox();
                HBox messageBox = new HBox();
                Button close = new Button("Close");
                close.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            primaryStage.setScene(getShip(primaryStage));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                messageBox.getChildren().add(message);
                box.getChildren().addAll(messageBox, close);
                background.getChildren().add(box);
                Scene newScene = new Scene(background, 500, 200);
                warrningMessage.setScene(newScene);
                warrningMessage.show();
            }
        });
        Button backToUniverse = new Button("Back to the Universe");
        backToUniverse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        addFuel.setMaxSize(150, 50);
        addFuel.setMinSize(150, 50);
        repair.setMaxSize(150, 50);
        repair.setMinSize(150, 50);
        backToUniverse.setMaxSize(150, 50);
        backToUniverse.setMinSize(150, 50);
        buttonPane.getChildren().addAll(addFuel);
        buttonPane.getChildren().add(repair);
        buttonPane.getChildren().add(backToUniverse);
        buttonPane.setSpacing(20);
        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        content.getChildren().addAll(information, buttonPane);
        buttonPane.setAlignment(Pos.CENTER);
        floatingCard.getChildren().addAll(floatingCardBG, content);
        pane.setCenter(floatingCard);
        pane.setTop(title);
        initialConfigBGPane.getChildren().addAll(initialConfigIV, pane);
        primaryStage.show();
        return new Scene(initialConfigBGPane, 1280, 720);
    }
    public Scene addFuel(Stage primaryStage) throws FileNotFoundException {
        StackPane initialConfigBGPane = new StackPane();
        initialConfigBGPane.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        Image initialConfigBG = new Image(new FileInputStream(state.getCurrRegion().getImageUrl()));
        //Setting the image view
        ImageView initialConfigIV = new ImageView(initialConfigBG);
        //Setting the position of the image
        initialConfigIV.setX(640);
        initialConfigIV.setY(360);
        initialConfigIV.setFitWidth(738);
        initialConfigIV.setFitHeight(615);
        //Setting the preserve ratio of the image view
        initialConfigIV.setPreserveRatio(true);
        BorderPane pane = new BorderPane();
        Image ship = new Image(new FileInputStream(state.getShip().getImageUrl()));
        ImageView shipView = new ImageView(ship);
        shipView.setFitWidth(420);
        shipView.setPreserveRatio(true);
        pane.setRight(shipView);
        StackPane floatingCard = new StackPane();
        floatingCard.setAlignment(Pos.CENTER);
        floatingCard.setMinSize(400, 500);
        floatingCard.setMaxSize(400, 500);
        VBox content = new VBox();
        VBox information = new VBox();
        VBox buttonPane = new VBox();
        Text title = new Text("                                                       "
                + " Add Fuel");
        title.setFont(Font.font(32));
        title.setFill(Color.WHITE);
        Text fuel = new Text("        Current Fuel: " + state.getShip().getFuelLeft() + "/"
                + state.getShip().getFuelCapacity());
        Text cargo = new Text("        Avaible Fuel in cargo: "
                + state.getShip().numOfGood(Good.GAS));
        Text credit = new Text("        Current credit: " + state.getCredit());
        Text space1 = new Text("");
        Text space2 = new Text("");
        Text space3 = new Text("");
        Text s1 = new Text("");
        Text s2 = new Text("");
        credit.setFont(Font.font(20));
        fuel.setFont(Font.font(20));
        cargo.setFont(Font.font(20));
        information.getChildren().addAll(s1, s2);
        information.getChildren().add(fuel);
        information.getChildren().add(cargo);
        information.getChildren().add(credit);
        information.getChildren().addAll(space1, space2, space3);
        information.setSpacing(15);
        HBox addBox = new HBox();
        Text adding = new Text("      add     ");
        adding.setId("grid-prompt");
        adding.setLayoutX(10);
        adding.setLayoutY(280);

        TextField addInputField = new TextField();
        addInputField.setLayoutX(225);
        addInputField.setLayoutY(255);
        HBox choiceBox = new HBox();
        addBox.getChildren().addAll(adding, addInputField);
        Button backToUniverse = new Button("Back to the Universe");
        backToUniverse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(getUniverseMap(primaryStage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        Button addFuel = new Button("Add fuel");
        addFuel.setMaxSize(150, 50);
        addFuel.setMinSize(150, 50);
        addFuel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isInteger(addInputField.getText())) {
                    int fuelAmount = Integer.parseInt(addInputField.getText());
                    if (fuelAmount <= state.getShip().numOfGood(Good.GAS)) {
                        if (fuelAmount <= state.getShip().getFuelCapacity()
                                - state.getShip().getFuelLeft()) {
                            if (state.getCredit() >= 2) {
                                state.getShip().addFuel(fuelAmount * 10);
                                state.getShip().useItem(Good.GAS, fuelAmount);
                                state.setCredit(state.getCredit() - 2);
                                information.getChildren().clear();
                                Text fuel = new Text("        Current Fuel: "
                                        + state.getShip().getFuelLeft() + "/"
                                        + state.getShip().getFuelCapacity());
                                Text cargo = new Text("        Avaible Fuel in cargo: "
                                        + state.getShip().numOfGood(Good.GAS));
                                Text credit = new Text("        Current credit: "
                                        + state.getCredit());
                                Text space1 = new Text("");
                                Text space2 = new Text("");
                                Text space3 = new Text("");
                                Text s1 = new Text("");
                                Text s2 = new Text("");
                                fuel.setFont(Font.font(20));
                                cargo.setFont(Font.font(20));
                                credit.setFont(Font.font(20));
                                information.getChildren().addAll(s1, s2);
                                information.getChildren().add(fuel);
                                information.getChildren().add(cargo);
                                information.getChildren().add(credit);
                                information.getChildren().addAll(space1, space2, space3);
                            } else {
                                Text message = new Text("You don't have enough credit to fuel");
                                Stage warrningMessage = new Stage();
                                warrningMessage.initModality(Modality.APPLICATION_MODAL);
                                warrningMessage.initOwner(primaryStage);
                                StackPane background = new StackPane();
                                background.setBackground(new Background(
                                        new BackgroundFill(Color.GRAY,
                                                CornerRadii.EMPTY, Insets.EMPTY)));
                                HBox messageBox = new HBox();
                                Button close = new Button("Close");
                                close.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        warrningMessage.close();

                                    }
                                });
                                messageBox.getChildren().add(message);
                                VBox box = new VBox();
                                box.getChildren().addAll(messageBox, close);
                                background.getChildren().add(box);
                                Scene newScene = new Scene(background, 500, 200);
                                warrningMessage.setScene(newScene);
                                warrningMessage.show();
                            }


                        } else {
                            Text message = new Text("You don't have enough fuel capacity");
                            Stage warrningMessage = new Stage();
                            warrningMessage.initModality(Modality.APPLICATION_MODAL);
                            warrningMessage.initOwner(primaryStage);
                            StackPane background = new StackPane();
                            background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                    CornerRadii.EMPTY, Insets.EMPTY)));
                            HBox messageBox = new HBox();
                            Button close = new Button("Close");
                            close.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    warrningMessage.close();

                                }
                            });
                            messageBox.getChildren().add(message);
                            VBox box = new VBox();
                            box.getChildren().addAll(messageBox, close);
                            background.getChildren().add(box);
                            Scene newScene = new Scene(background, 500, 200);
                            warrningMessage.setScene(newScene);
                            warrningMessage.show();
                        }
                    } else {
                        Text message = new Text("You don't have enough fuel in cargo");
                        Stage warrningMessage = new Stage();
                        warrningMessage.initModality(Modality.APPLICATION_MODAL);
                        warrningMessage.initOwner(primaryStage);
                        StackPane background = new StackPane();
                        background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                                CornerRadii.EMPTY, Insets.EMPTY)));
                        HBox messageBox = new HBox();
                        Button close = new Button("Close");
                        close.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                warrningMessage.close();

                            }
                        });
                        messageBox.getChildren().add(message);
                        VBox box = new VBox();
                        box.getChildren().addAll(messageBox, close);
                        background.getChildren().add(box);
                        Scene newScene = new Scene(background, 500, 200);
                        warrningMessage.setScene(newScene);
                        warrningMessage.show();
                    }
                } else {
                    Text message = new Text(
                            "Input has to be integer, if you don't want to add fuel, type in 0");
                    Stage warrningMessage = new Stage();
                    warrningMessage.initModality(Modality.APPLICATION_MODAL);
                    warrningMessage.initOwner(primaryStage);
                    StackPane background = new StackPane();
                    background.setBackground(new Background(new BackgroundFill(Color.GRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    HBox messageBox = new HBox();
                    Button close = new Button("Close");
                    close.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            warrningMessage.close();

                        }
                    });
                    messageBox.getChildren().add(message);
                    VBox box = new VBox();
                    box.getChildren().addAll(messageBox, close);
                    background.getChildren().add(box);
                    Scene newScene = new Scene(background, 500, 200);
                    warrningMessage.setScene(newScene);
                    warrningMessage.show();
                }
            }
        });
        backToUniverse.setMaxSize(150, 50);
        backToUniverse.setMinSize(150, 50);
        choiceBox.getChildren().addAll(addFuel, backToUniverse);
        buttonPane.getChildren().add(addBox);
        buttonPane.getChildren().add(choiceBox);
        buttonPane.setSpacing(20);
        Rectangle floatingCardBG = new Rectangle(400, 500);
        floatingCardBG.setStroke(Color.BLACK);
        floatingCardBG.setFill(Color.rgb(255, 255, 255, 0.3));
        floatingCardBG.setStrokeWidth(3);
        content.getChildren().addAll(information, buttonPane);
        buttonPane.setAlignment(Pos.CENTER);
        floatingCard.getChildren().addAll(floatingCardBG, content);
        pane.setCenter(floatingCard);
        pane.setTop(title);
        initialConfigBGPane.getChildren().addAll(initialConfigIV, pane);
        primaryStage.show();
        return new Scene(initialConfigBGPane, 1280, 720);
    }

    public AppState getState() {
        return state;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
