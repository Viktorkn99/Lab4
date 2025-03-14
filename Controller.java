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
        System.out.println("Test grid row = " + row + " col = " + col);
    }

    public void onNewGamePressed() {
        System.out.println("Test new game");
    }

    public void onDifficultyPressed() {
        System.out.println("Tests difficulty");
    }

    public void onLoadGamePressed() {
        System.out.println("Test Load game");
    }

    public void onSaveGamePressed() {
        System.out.println("Test Save game");
    }
    public void onExitPressed() {
        System.out.println("Test Exit");
    }

    public void onClearAllPressed() {
        System.out.println("test clear All");
    }

    public void onCheckPressed() {
        System.out.println("test check");
    }

    public void onRulesPressed() {
        System.out.println("test Rules");
    }

    public void onButtonPressed(int index) {
        System.out.println("test button " + index);
    }

    public void onClearButtonPressed() {
        System.out.println("Test clear button");
    }

    public  void onHintPressed() {
        System.out.println("Test hint");
    }
















}
