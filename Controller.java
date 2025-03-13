package se.kth.viktorine.lab4sudoku.View;

import se.kth.viktorine.lab4sudoku.Model.SudokuBoard;
import javafx.scene.control.Label;

public class Controller {
    private SudokuView view;
    private SudokuBoard model;

    public Controller(SudokuView view) {
        this.view = view;
        this.model = new SudokuBoard();
    }

    private Label[][] numberTiles;


    public void onTilePressed(int row, int col) {
        System.out.println("Test grid");
    }











}
