package lk.ijse.dep.service;

public class Winner {
    private Piece winningPiece; // Enum type
    private int col1;
    private int row1;
    private int col2;
    private int row2;

    public Winner(Piece winningPiece) {  // Constructor
        this.winningPiece = winningPiece;
        this.col1 = -1;
        this.row1 = -1;
        this.col2 = -1;
        this.row2 = -1;
    }

    public Winner(Piece winningPiece, int col1, int row1, int col2, int row2) { // Constructor
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
    }

    public Piece getWinningPiece() { // Getter-winnerPiece
        return this.winningPiece;
    }

    public void setWinningPiece(Piece winningPiece) { // Setter-winnerPiece
        this.winningPiece = winningPiece;
    }

    public int getCol1() { // Getter-col1
        return this.col1;
    }

    public void setCol1(int col1) { // Setter-col1
        this.col1 = col1;
    }

    public int getRow1() { // Getter-row1
        return this.row1;
    }

    public void setRow1(int row1) { // Setter-row1
        this.row1 = row1;
    }

    public int getCol2() { // Getter-col2
        return this.col2;
    }

    public void setCol2(int col2) { // Setter-col2
        this.col2 = col2;
    }



    public int getRow2() { // Getter-row2
        return this.row2;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "winningPiece=" + winningPiece +
                ", col1=" + col1 +
                ", row1=" + row1 +
                ", col2=" + col2 +
                ", row2=" + row2 +
                '}';
    }
}
