package se.kth.viktorine.lab4sudoku.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import se.kth.viktorine.lab4sudoku.Model.SudokuBoard;

import static se.kth.viktorine.lab4sudoku.SudokuUtilities.GRID_SIZE;

public class SudokuView extends VBox {
    private Controller controller;
    private SudokuBoard model;
    private GridView gridView = new GridView(this);
    private MenuBar menuBar;
    private MenuItem loadGame;
    private MenuItem saveGame;
    private MenuItem exit;
    private MenuItem newGame;
    private MenuItem difficulty;
    private MenuItem clearAll;
    private MenuItem check;
    private MenuItem rules;
    private Button[] selectButtons;
    private Button hint;
    private Button check2;
    private VBox numberSelector;
    private VBox actionButtons;


    public SudokuView(SudokuBoard model) {
        super();
        this.controller = new Controller(this);
        this.model = model;

        loadGame = new MenuItem("Load game");
        saveGame = new MenuItem("Save game");
        exit = new MenuItem("Exit");

        newGame = new MenuItem("New game");
        difficulty = new MenuItem("Choose difficulty");

        clearAll = new MenuItem("Clear all");
        check = new MenuItem("Check");
        rules = new MenuItem("Rules");

        check2 = new Button("Check");
        hint = new Button("Hint");

        selectButtons = new Button[10];

        numberSelector = createNumberSelector();
        actionButtons = createActionButtons();
        menuBar = createMenuBar();

        this.getChildren().addAll(menuBar, createMainLayout());

    }


    private BorderPane createMainLayout() {
        BorderPane layout = new BorderPane();
        layout.setCenter(gridView.getNumberPane());
        layout.setRight(numberSelector);
        layout.setLeft(actionButtons);
        return layout;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");

        loadGame.setOnAction(buttonClickHandler);
        saveGame.setOnAction(buttonClickHandler);
        exit.setOnAction(buttonClickHandler);
        fileMenu.getItems().addAll(loadGame, saveGame, exit);

        newGame.setOnAction(buttonClickHandler);
        difficulty.setOnAction(buttonClickHandler);
        gameMenu.getItems().addAll(newGame, difficulty);

        clearAll.setOnAction(buttonClickHandler);
        check.setOnAction(buttonClickHandler);
        rules.setOnAction(buttonClickHandler);
        helpMenu.getItems().addAll(clearAll, check, rules);

        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(gameMenu);
        menuBar.getMenus().add(helpMenu);
        return menuBar;
    }


    private VBox createNumberSelector() {
        VBox numberSelector = new VBox(5);

        for (int i = 0; i < 10; i++) {
            if(i == 9) {
                selectButtons[9] = new Button("C");
                selectButtons[9].setOnAction(buttonClickHandler);
            }
            else {
                selectButtons[i] = new Button(String.valueOf(i+1));
                selectButtons[i].setOnAction(buttonClickHandler);
            }

            selectButtons[i].setMinSize(32, 32);
            numberSelector.getChildren().add(selectButtons[i]);
        }
        return numberSelector;
    }

    private VBox createActionButtons() {
        VBox actionButtons = new VBox(10);
        //Button checkButton = new Button("Check");
        //Button hintButton = new Button("Hint");
        check2.setMinSize(60, 30);
        hint.setMinSize(60, 30);

        hint.setOnAction(buttonClickHandler);
        check2.setOnAction(buttonClickHandler);
        actionButtons.getChildren().addAll(check2, hint);
        return actionButtons;
    }



    private EventHandler<ActionEvent> buttonClickHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if(event.getSource() == newGame) controller.onNewGamePressed();

            if(event.getSource() == difficulty) controller.onDifficultyPressed();

            if(event.getSource() == loadGame) controller.onLoadGamePressed();

            if(event.getSource() == saveGame) controller.onSaveGamePressed();

            if(event.getSource() == exit) controller.onExitPressed();

            if(event.getSource() == clearAll) controller.onClearAllPressed();

            if(event.getSource() == check) controller.onCheckPressed();

            if(event.getSource() == rules) controller.onRulesPressed();

            if(event.getSource() == hint) controller.onHintPressed();

            if(event.getSource() == check2) controller.onCheckPressed();

            for (int i = 0; i < 9; i++) {
                    if(event.getSource() == selectButtons[i]) controller.onButtonPressed(i + 1);
            }

            if(event.getSource() == selectButtons[9]) controller.onClearButtonPressed();
        }
    };

}
