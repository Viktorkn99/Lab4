package se.kth.viktorine.lab4sudoku.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import se.kth.viktorine.lab4sudoku.Model.SudokuBoard;

public class SudokuView extends VBox {
    private SudokuBoard model;
    private GridView gridView = new GridView(this);
    private MenuBar menuBar;
    private VBox numberSelector;
    private VBox actionButtons;

    public SudokuView(SudokuBoard model) {
        super();
        this.model = model;
        menuBar = createMenuBar();
        numberSelector = createNumberSelector();
        actionButtons = createActionButtons();
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
        fileMenu.getItems().addAll(new MenuItem("Load game"), new MenuItem("Save game"), new MenuItem("Exit"));

        gameMenu.getItems().addAll(new MenuItem("New game"), new MenuItem("Choose difficulty"));
        //gameMenu.getItems().add(new MenuItem("New game").setOnAction(clickHandler);)

                helpMenu.getItems().addAll(new MenuItem("Check"), new MenuItem("Rules"));
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(gameMenu);
        menuBar.getMenus().add(helpMenu);
        return menuBar;
    }

    private VBox createNumberSelector() {
        VBox numberSelector = new VBox(5);

        for (int i = 1; i <= 9; i++) {
            Button btn = new Button(String.valueOf(i));
            btn.setMinSize(32, 32);
            int finalI = i;
            //btn.setOnAction(e -> OnActionButtonsPressed(finalI));
            numberSelector.getChildren().add(btn);
        }

        Button clearButton = new Button("C");
        clearButton.setMinSize(32, 32);
        //clearButton.setOnAction(e -> placeNumber(0));
        numberSelector.getChildren().add(clearButton);
        return numberSelector;
    }

    private VBox createActionButtons() {
        VBox actionButtons = new VBox(10);
        Button checkButton = new Button("Check");
        Button hintButton = new Button("Hint");
        checkButton.setMinSize(60, 30);
        hintButton.setMinSize(60, 30);
        actionButtons.getChildren().addAll(checkButton, hintButton);
        return actionButtons;
    }


    /*private EventHandler<ActionEvent> buttonCLickHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(MouseEvent event) {

        }
    }*/

}
