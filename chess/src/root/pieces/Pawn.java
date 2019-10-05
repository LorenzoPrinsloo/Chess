package root.pieces;

import root.Board;
import root.Move;
import root.PieceType;
import root.PlayerType;

import java.util.List;

public class Pawn implements Piece {

    boolean isFirstMove;
    PlayerType owner;

    public Pawn(PlayerType o) {
        this.owner = o;
        this.isFirstMove = true;
    }

    @Override
    public boolean isValidMove(Move move, Board board) {

        int rowDiff = move.getTo().getRow() - move.getFrom().getRow();
        int colDiff = move.getTo().getColumn() - move.getFrom().getColumn();

        if(rowDiff < 0 && owner == PlayerType.BLACK) {
            if(rowDiff == -1){ // move upwards
                Piece dest = board.getBoardMatrix().get(move.getTo().getRow()).get(move.getTo().getColumn());

                if(Math.abs(colDiff) == 1 && dest.getOwner() != owner){
                    System.out.println("Capture!");
                    return true;
                } else if(colDiff == 0 && dest == null) {
                    return true;
                } else return false;

            } else if(rowDiff == -2 && isFirstMove) {

                Piece oneAhead = board.getBoardMatrix().get(move.getTo().getRow()-1).get(move.getTo().getColumn());
                Piece twoAhead = board.getBoardMatrix().get(move.getTo().getRow()).get(move.getTo().getColumn());

                if(oneAhead == null && twoAhead == null){
                    return true;
                } else return false;
            } else return false;
        } else if(rowDiff > 0 && owner == PlayerType.WHITE) {
            if(rowDiff == 1){
                Piece dest = board.getBoardMatrix().get(move.getTo().getRow()).get(move.getTo().getColumn());

                if(Math.abs(colDiff) == 1 && dest.getOwner() != owner){
                    System.out.println("Capture!");
                    return true;
                } else if(colDiff == 0 && dest == null) {
                    return true;
                } else return false;

            } else if(rowDiff == 2 && isFirstMove) {

                Piece oneAhead = board.getBoardMatrix().get(move.getTo().getRow()-1).get(move.getTo().getColumn());
                Piece twoAhead = board.getBoardMatrix().get(move.getTo().getRow()).get(move.getTo().getColumn());

                if(oneAhead == null && twoAhead == null){
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }

    public Piece makeOfficer(char requested, Board board) {

        Piece piece = null;
        PieceType type = null;

        switch(requested) {
            case 'K' : piece = new King(owner); type = PieceType.KING; break;
            case 'k' : piece = new King(owner); type = PieceType.KING; break;
            case 'Q' : piece = new Queen(owner); type = PieceType.QUEEN; break;
            case 'q' : piece = new Queen(owner); type = PieceType.QUEEN; break;
            case 'A' : piece = new Amazon(owner); type = PieceType.AMAZON; break;
            case 'a' : piece = new Amazon(owner); type = PieceType.AMAZON; break;
            case 'F' : piece = new Dragon(owner); type = PieceType.DRAGON; break;
            case 'f' : piece = new Dragon(owner); type = PieceType.DRAGON; break;
            case 'D' : piece = new DrunkenPawn(owner); type = PieceType.DRUNKED_PAWN; break;
            case 'd' : piece = new DrunkenPawn(owner); type = PieceType.DRUNKED_PAWN; break;
            case 'B' : piece = new Bishop(owner); type = PieceType.BISHOP; break;
            case 'b' : piece = new Bishop(owner); type = PieceType.BISHOP; break;
            case 'N' : piece = new Knight(owner); type = PieceType.KNIGHT; break;
            case 'n' : piece = new Knight(owner); type = PieceType.KNIGHT; break;
            case 'P' : piece = new Pawn(owner); type = PieceType.PAWN; break;
            case 'p' : piece = new Pawn(owner); type = PieceType.PAWN; break;
            case 'W' : piece = new Princess(owner); type = PieceType.PRINCESS; break;
            case 'w' : piece = new Princess(owner); type = PieceType.PRINCESS; break;
            case 'R' : piece = new Rook(owner); type = PieceType.ROOK; break;
            case 'r' : piece = new Rook(owner); type = PieceType.ROOK; break;
        }

        board.setEntryInPieceCount(PieceType.PAWN, board.getPieceCount().get(PieceType.PAWN) - 1);
        board.setEntryInPieceCount(type, board.getPieceCount().get(type) + 1);

        int rowCount = 0;
        int columnCount = 0;
        for(List<Piece> row: board.getBoardMatrix()) {

            columnCount = 0;
            for(Piece p: row){
                if(p.hashCode() == this.hashCode()) {
                    board.setEntryInBoardMatrix(columnCount, rowCount, p);
                }
                columnCount++;
            }

            rowCount++;
        }

        return piece;
    }

    @Override
    public PlayerType getOwner() {
        return this.owner;
    }
}
