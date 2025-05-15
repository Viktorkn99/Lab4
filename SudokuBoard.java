package se.kth.viktorine.labb4fx.model;

import javax.swing.*;
import java.io.*;
import java.util.Random;

public class SudokuBoard implements Serializable {

    private final SudokuCell[][] cells;

    public SudokuBoard() {
        this.cells = new SudokuCell[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
    }

    public void initBoard(SudokuUtilities.SudokuLevel level) {

        int[][][] generatedMatrix = SudokuUtilities.generateSudokuMatrix(level);
        boolean show;

        for (int i = 0; i < generatedMatrix.length; i++) {
            for (int j = 0; j < generatedMatrix[i].length; j++) {

                if (generatedMatrix[i][j][0] == generatedMatrix[i][j][1]){
                    show = true;
                } else {
                    show = false;
                }
                int cellId = ((i + 1) * 10) + (j + 1);

                cells[i][j] = new SudokuCell(cellId, generatedMatrix[i][j][1], show);
            }
        }
    }

    public void addValue(int value, int cellId) throws IllegalArgumentException {
        int columnIndex = (cellId % 10) - 1;
        int rowIndex = ((cellId - cellId % 10) / 10) - 1;

        if (!cells[rowIndex][columnIndex].isShow()) {
            cells[rowIndex][columnIndex].setValue(value);
        } else {
            throw new IllegalArgumentException("This is a cell with initialized value");
        }
    }

    public int removeValue(int cellId) throws IllegalArgumentException {
        int columnIndex = (cellId % 10) - 1;
        int rowIndex = ((cellId - cellId % 10) / 10) - 1;
        if (!cells[rowIndex][columnIndex].isShow()) {
            int removedValue = cells[rowIndex][columnIndex].getValue();
            cells[rowIndex][columnIndex].setValue(0);

            return removedValue;
        }
        throw new IllegalArgumentException("This is a cell with initialized value");
    }

    public SudokuCell[][] getCells() {
        SudokuCell[][] copy = new SudokuCell[9][9];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                SudokuCell original = cells[i][j];
                copy[i][j] = new SudokuCell(original.getId(), original.getCorrectValue(), original.isShow());
                copy[i][j].setValue(original.getValue());
            }
        }
        return copy;
    }

    public void clearBoard() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!cells[i][j].isShow()){
                    cells[i][j].setValue(0);
                }
            }
        }
    }

    public boolean checkCorrect(){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (!(cells[i][j].getValue() == 0)){
                    if (!(cells[i][j].getValue() == cells[i][j].getCorrectValue())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void giveHelp(){
        Random random = new Random();

        while(true) {
            int row = random.nextInt(cells.length);
            int col = random.nextInt(cells[0].length);

            if (cells[row][col].getValue() == 0) {
                cells[row][col].setValue(cells[row][col].getCorrectValue());
                break;
            }
        }
    }

    public boolean isSolved() {
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                SudokuCell cell = cells[r][c];
                if (cell.getValue() == 0)
                    return false;
                if (cell.getValue() != cell.getCorrectValue())
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                sb.append(cells[i][j].getValue()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void SerializeToFile(SudokuCell[][] board) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Sudoku-files (*.sudoku)", "sudoku"));

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".sudoku")) {
                file = new File(file.getAbsolutePath() + ".sudoku");
            }

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(board);
            } catch (IOException e) {
                e.printStackTrace(); // möjligtvis inte catcha här med då
                                      //behöver vi reda ut hur man throwar efter varje anropning, hela vägen till buttonevent handle
            }
        }
    }

    public SudokuCell[][] deSerializeFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Game");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Sudoku-files (*.sudoku)", "sudoku"));

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                return (SudokuCell[][]) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace(); // Samma sak här
            }
        }
        return null;
    }
}
