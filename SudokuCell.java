package Model;

public class SudokuCell {

    private final int id, correctValue;
    private int value;
    private final boolean show;

    public SudokuCell(int id, int correctValue, boolean show) {
        this.id = id;
        this.correctValue = correctValue;
        this.show = show;
        if (show) {
            this.value = correctValue;
        } else {
            this.value = 0;
        }
    }

    public int getId() {
        return id;
    }

    public int getCorrectValue() {
        return correctValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isShow() {
        return show;
    }

    @Override
    public String toString() {
        return "SudokuCell{" +
                "id=" + id +
                ", correctValue=" + correctValue +
                ", value=" + value +
                ", show=" + show +
                '}';
    }
}
