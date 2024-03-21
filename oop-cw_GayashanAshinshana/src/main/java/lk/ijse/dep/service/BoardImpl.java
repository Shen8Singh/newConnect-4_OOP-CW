package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private static final Piece[][] pieces = new Piece[6][5]; // 6 columns and 5 rows

    private final BoardUI boardUI; // Interface type

    public BoardImpl(BoardUI boardUI) { // Constructor

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                this.pieces[i][j] = Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for (int i = 0; i < 5; ++i) {
            if (this.pieces[col][i] == Piece.EMPTY) {

                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return this.findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existsLegalMove() {
        for (int i = 0; i < 6; ++i) {
            if (this.isLegalMove(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {

        if (findNextAvailableSpot(col) != -1 ){
        this.pieces[col][this.findNextAvailableSpot(col)] = move;
        }
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        this.pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {

        if(!existsLegalMove() ){
            getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
        for (int i = 0; i < NUM_OF_COLS; i++) {
            //checking the common indices of columns (1,2,3) in human player(Vertically)
            if (pieces[i][1].equals(Piece.BLUE) & pieces[i][2].equals(Piece.BLUE) & pieces[i][3].equals(Piece.BLUE)) {
                if (pieces[i][0].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, i, 0, i, 3);

                } else if (pieces[i][4].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, i, 1, i, 4);
                }
            } else if (pieces[i][1].equals(Piece.GREEN) & pieces[i][2].equals(Piece.GREEN) & pieces[i][3].equals(Piece.GREEN)) {
                //checking the common indices of columns (1,2,3) in AI player(Vertically)
                if (pieces[i][0].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, i, 0, i, 3);

                } else if (pieces[i][4].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, i, 1, i, 4);
                }
            }
        }

        for (int j = 0; j < NUM_OF_ROWS; j++) {
            //checking the common indices of columns (2,3) in human player(Horizontally)
            if(pieces[2][j].equals(Piece.BLUE) & pieces[3][j].equals(Piece.BLUE)) {
                if (pieces[0][j].equals(Piece.BLUE) & pieces[1][j].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, 0, j, 3, j);
                } else if (pieces[1][j].equals(Piece.BLUE) & pieces[4][j].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, 1, j, 4, j);
                } else if (pieces[4][j].equals(Piece.BLUE) & pieces[5][j].equals(Piece.BLUE)) {
                    return new Winner(Piece.BLUE, 2, j, 5, j);
                }
            }else if (pieces[2][j].equals(Piece.GREEN) & pieces[3][j].equals(Piece.GREEN))
                //checking the common indices of columns (2,3) in AI player(Horizontally)
                if (pieces[0][j].equals(Piece.GREEN) & pieces[1][j].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, 0, j, 3, j);
                } else if (pieces[1][j].equals(Piece.GREEN) & pieces[4][j].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, 1, j, 4, j);
                } else if (pieces[4][j].equals(Piece.GREEN) & pieces[5][j].equals(Piece.GREEN)) {
                    return new Winner(Piece.GREEN, 2, j, 5, j);
                }
        }

        return new Winner(Piece.EMPTY);
    }
}

     //Method to copy the current board
//    public BoardImpl copy() {
//        BoardImpl copiedBoard = new BoardImpl(this.boardUI);
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 5; j++) {
//                copiedBoard.pieces[i][j] = this.pieces[i][j];
//            }
//        }
//        return copiedBoard;
//    }
//
//     //Inside the BoardImpl class
//    @Override
//    public Piece getPiece(int row, int col) {
//        return pieces[col][row];
//    }