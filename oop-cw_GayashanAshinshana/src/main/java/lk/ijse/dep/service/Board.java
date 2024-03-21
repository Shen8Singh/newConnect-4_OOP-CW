package lk.ijse.dep.service;

public interface Board {
    int NUM_OF_COLS = 6;
    int NUM_OF_ROWS = 5;

    BoardUI getBoardUI();

    int findNextAvailableSpot(int col);

    boolean isLegalMove(int col);

    boolean existsLegalMove();

    void updateMove(int col, Piece move);

    void updateMove(int col, int row, Piece move);

    Winner findWinner();


   // Board copy();

    //Piece getPiece(int row, int col);
}
