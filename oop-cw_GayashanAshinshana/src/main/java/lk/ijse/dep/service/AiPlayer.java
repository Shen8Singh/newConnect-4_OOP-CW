//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lk.ijse.dep.service;

import java.util.Random;


public class AiPlayer extends Player {
    private static final int SIMULATION_COUNT = 1000; // Increase simulation count
    private Random random;




    public AiPlayer(Board board) {
        super(board);
        random = new Random();
    }


    @Override
    public void movePiece(int col) {


        col=minMax();
        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);
        Winner winner = board.findWinner();
        if(winner.getWinningPiece().equals(Piece.BLUE)||winner.getWinningPiece().equals(Piece.GREEN)) {
            board.getBoardUI().notifyWinner(winner);
        }

    }


    private int minMax() {
        for (int i = 0; i < 6; i++) {
            if(board.isLegalMove(i)){
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i,Piece.GREEN);
                if(board.findWinner().getWinningPiece().equals(Piece.GREEN)){
                    board.updateMove(i,row,Piece.EMPTY);
                    return i;
                }else{
                    board.updateMove(i,row,Piece.EMPTY);
                }
            }

        }for (int i = 0; i < 6; i++) {
            if(board.isLegalMove(i)){
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i,Piece.BLUE);
                if(board.findWinner().getWinningPiece().equals(Piece.BLUE)){
                    board.updateMove(i,row,Piece.EMPTY);
                    return i;

                }else{
                    board.updateMove(i,row,Piece.EMPTY);
                }
            }

        }
        int num;
        do{
            num= (int) (Math.random() * 6.0D);
        }while (!board.isLegalMove(num));

        return num;
    }
}


//MCTS Algorithm

//    public void movePiece(int col) {
//        System.out.println("Moving piece...");
//        int[] move = predictMove();
//        col = move[0];
//        int row = move[1];
//        System.out.println("AI move: " + col + ", " + row);
//        board.updateMove(col, row, Piece.GREEN);
//        board.getBoardUI().update(col, false);
//        Winner winner = board.findWinner();
//        if (winner.getWinningPiece() != Piece.EMPTY) {
//            board.getBoardUI().notifyWinner(winner);
//        } else if (!board.existsLegalMove()) {
//            // Check if all positions are filled (indicating a tie)
//            boolean allPositionsFilled = true;
//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 6; j++) {
//                    if (board.getPiece(i, j) == Piece.EMPTY) {
//                        allPositionsFilled = false;
//                        break;
//                    }
//                }
//                if (!allPositionsFilled) {
//                    break;
//                }
//            }
//            if (allPositionsFilled) {
//                // Check if there's no winner to avoid unnecessary tie notification
//                if (winner.getWinningPiece() == Piece.EMPTY) {
//                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY)); // Notify tie
//                }
//            }
//        }
//    }
//
//
//
//    private int[] predictMove() {
//
//        Node root = new Node(null, -1, -1);
//        for (int i = 0; i < SIMULATION_COUNT; i++) {
//            simulate(root);
//        }
//        Node bestChild = getBestChild(root);
//        int[] move = new int[]{bestChild.getColumn(), bestChild.getRow()};
//
//        return move;
//    }
//
//
//    private void simulate(Node node) {
//
//        L1: while (board.existsLegalMove()) {
//
//            int[] randomMove = getRandomMove();
//            int col = randomMove[0];
//            int row = randomMove[1];
//
//            board.updateMove(col, row, Piece.GREEN);
//            Winner winner = board.findWinner();
//
//            Node child = new Node(node, col, row);
//            node.addChild(child);
//            if (winner.getWinningPiece() != Piece.EMPTY) {
//                backpropagate(child, winner.getWinningPiece());
//                break L1;
//            }
//        }
//
//    }
//
//    private int[] getRandomMove() {
//        int col, row;
//        do {
//            col = random.nextInt(6); // Assuming 6 is the column count for the Connect 4 board
//            row = board.findNextAvailableSpot(col);
//
//        } while (row == -1);
//        return new int[]{col, row};
//    }
//
//    private void backpropagate(Node node, Piece winningPiece) {
//        while (node != null) {
//            node.incrementVisit();
//            if (winningPiece == Piece.GREEN) {
//                node.addWin();
//            }
//            node = node.getParent();
//        }
//    }
//
//    private Node getBestChild(Node node) {
//        double maxUCB = Double.NEGATIVE_INFINITY;
//        Node bestChild = null;
//        for (Node child : node.getChildren()) {
//            double ucb = calculateUCB(child);
//            if (ucb > maxUCB) {
//                maxUCB = ucb;
//                bestChild = child;
//            }
//        }
//        return bestChild;
//    }
//
//    private double calculateUCB(Node node) {
//        if (node.getVisit() == 0) {
//            return Double.POSITIVE_INFINITY;
//        }
//        double exploration = Math.sqrt(Math.log(node.getParent().getVisit()) / node.getVisit());
//        double exploitation = (double) node.getWin() / node.getVisit();
//        return exploitation + Math.sqrt(2) * exploration; // Adjust exploration weight
//    }
//
//    private static class Node {
//        private Node parent;
//        private List<Node> children;
//        private int column;
//        private int row;
//        private int visit;
//        private int win;
//
//        public Node(Node parent, int column, int row) {
//            this.parent = parent;
//            this.column = column;
//            this.row = row;
//            children = new ArrayList<>();
//            visit = 0;
//            win = 0;
//        }
//
//        public void addChild(Node child) {
//            children.add(child);
//        }
//
//        public List<Node> getChildren() {
//            return children;
//        }
//
//        public int getColumn() {
//            return column;
//        }
//
//        public int getRow() {
//            return row;
//        }
//
//        public Node getParent() {
//            return parent;
//        }
//
//        public int getVisit() {
//            return visit;
//        }
//
//        public int getWin() {
//            return win;
//        }
//
//        public void incrementVisit() {
//            visit++;
//        }
//
//        public void addWin() {
//            win++;
//        }
//    }
//}
