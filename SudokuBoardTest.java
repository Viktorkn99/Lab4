package Model;

public class SudokuBoardTest {
    public static void main(String[] args) {
        SudokuBoard test = new SudokuBoard();
        test.initBoard(SudokuUtilities.SudokuLevel.EASY);

        System.out.println(test);

    }
}
