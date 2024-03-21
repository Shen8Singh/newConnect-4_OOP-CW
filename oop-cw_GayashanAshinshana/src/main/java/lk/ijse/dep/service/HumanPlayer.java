package lk.ijse.dep.service;

public class HumanPlayer extends Player{

    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col){

        boolean flog=this.board.isLegalMove(col);

        if(flog){

            this.board.updateMove(col,Piece.BLUE);
            this.board.getBoardUI().update(col,true);

            Winner winner=this.board.findWinner();

            if(winner.getWinningPiece() != Piece.EMPTY){

                this.board.getBoardUI().notifyWinner(winner);

            } else if (!this.board.existsLegalMove()) {

                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
