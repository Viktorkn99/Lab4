package se.kth.viktorine.labb4fx.vy;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import se.kth.viktorine.labb4fx.model.SudokuBoard;
import se.kth.viktorine.labb4fx.model.SudokuUtilities;

public class SudokuView extends VBox {

    private final SudokuBoard model;
    private Controller controller = null;
    private final GridView gridView;

    private final MenuItem loadGame = new MenuItem("Load Game");
    private final MenuItem saveGame = new MenuItem("Save Game");
    private final MenuItem newGame = new MenuItem("New Game");
    private final Menu difficultyMenu = new Menu("Difficulty");
    private final MenuItem easyItem = new MenuItem("Easy");
    private final MenuItem mediumItem = new MenuItem("Medium");
    private final MenuItem hardItem = new MenuItem("Hard");
    private final MenuItem check = new MenuItem("Check");
    private final MenuItem rules = new MenuItem("Rules");
    private final MenuItem clearAll = new MenuItem("Start over on the same board");

    public SudokuView(SudokuBoard model) {
        this.model = model;
        this.controller = new Controller(this, model);
        this.gridView = new GridView(controller);

        MenuBar menuBar = createMenuBar();
        this.getChildren().addAll(menuBar, createMainLayout());
    }

    /**
     * Refresh the Sudoku grid based on the current model state.
     * Used to update the visual board after changes.
     */

    public void refreshBoard() {
        gridView.updateGrid(model.getCells());
    }

    /**
     * Create the main layout including the grid, number selector, and action buttons.
     *
     * @return a BorderPane containing the main UI components
     */

    private BorderPane createMainLayout() {
        BorderPane layout = new BorderPane();
        layout.setCenter(gridView.getNumberPane());
        layout.setRight(createNumberSelector());
        layout.setLeft(createActionButtons());
        return layout;
    }

    /**
     * Create the menu bar with the options file, game and help and their sub menus.
     *
     * @return a MenuBar with configured options and their actions
     */

    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");

        fileMenu.getItems().addAll(loadGame, saveGame);
        loadGame.setOnAction(e -> controller.loadGame());
        saveGame.setOnAction(e -> controller.saveGame());

        difficultyMenu.getItems().addAll(easyItem, mediumItem, hardItem);            // ta reda på hur easyItem funkar!
        easyItem.setOnAction (e -> controller.newGame(SudokuUtilities.SudokuLevel.EASY));
        mediumItem.setOnAction (e -> controller.newGame(SudokuUtilities.SudokuLevel.MEDIUM));
        hardItem.setOnAction (e -> controller.newGame(SudokuUtilities.SudokuLevel.HARD));


        gameMenu.getItems().addAll(newGame, difficultyMenu);
        newGame.setOnAction(e -> controller.newGame());


        helpMenu.getItems().addAll(check, rules, clearAll);
        check.setOnAction(e -> controller.onCheckPressed());
        rules.setOnAction(e -> controller.onRulesPressed());
        clearAll.setOnAction(e -> controller.onClearAllPressed());

        MenuBar bar = new MenuBar(fileMenu, gameMenu, helpMenu);
        return bar;
    }

    /**
     * Create the number selector used to fill cells in the Sudoku grid.
     * Includes buttons for numbers 1–9 and a clear button.
     *
     * @return a VBox containing number buttons and a clear button
     */

    private VBox createNumberSelector() {
        VBox box = new VBox(5);

        for (int i = 1; i <= 9; i++) {
            Button btn = new Button(String.valueOf(i));
            btn.setMinSize(32, 32);
            int n = i;
            btn.setOnAction(e -> controller.onNumberSelected(n));
            box.getChildren().add(btn);
        }

        Button clear = new Button("C");
        clear.setMinSize(32, 32);
        clear.setOnAction(e -> controller.onNumberSelected(0));
        box.getChildren().add(clear);

        return box;
    }

    /**
     * Create the action buttons for the player to check the puzzle or request a hint.
     *
     * @return a VBox containing the "Check" and "Hint" buttons
     */

    private VBox createActionButtons() {
        VBox box = new VBox(10);
        Button check = new Button("Check");
        Button hint  = new Button("Hint");
        check.setMinSize(60,30);
        hint.setMinSize(60,30);

        check.setOnAction(e -> controller.onCheckPressed());
        hint.setOnAction(e -> controller.onHintPressed());
        box.getChildren().addAll(check, hint);
        return box;
    }
}
