package se.kth.viktorine.lab4sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.viktorine.lab4sudoku.Model.SudokuBoard;
import se.kth.viktorine.lab4sudoku.View.SudokuView;

public class Main extends Application {

    private SudokuBoard model;

    @Override
    public void start(Stage stage) {

        model = new SudokuBoard();

        SudokuView view = new SudokuView(model);
        Scene scene = new Scene(view, 400, 400);

        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

