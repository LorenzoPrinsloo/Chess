package root;

import root.errors.MoveValidationErrors;
import root.pieces.King;
import root.pieces.Pawn;
import root.pieces.Piece;
import root.pieces.Space;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Board {
    PlayerType currentPlayer;
    CostlingStatus blackStatus;
    CostlingStatus whiteStatus;
    List<List<Piece>> boardMatrix;
    int halfMoveClock; // Every time pawn or drunken pawn moves
    int moveCounter; // Every time Black moves this increments
    Map<PieceType, Integer> pieceCount;

    // valid status variables
    private boolean isValidCapture = true; // add new ones to resetStatusVaraibles
    private boolean isValidMove = true;
    private boolean isValidPromotion = true;
    private boolean isValidCheck = true;
    private boolean isValidCheckMate = true;

    public Board(Board copyFrom, List<List<Piece>> matrix) {
        currentPlayer = copyFrom.currentPlayer;
        this.blackStatus = copyFrom.blackStatus;
        this.whiteStatus = copyFrom.whiteStatus;
        boardMatrix = matrix;
        this.halfMoveClock = copyFrom.halfMoveClock;
        this.moveCounter = copyFrom.moveCounter;
        pieceCount = copyFrom.pieceCount;
    }

    public Board(PlayerType player, List<List<Piece>> board, Map<PieceType, Integer> pc, CostlingStatus blackStatus, CostlingStatus whiteStatus, int halfMoveClock, int moveCounter) {
        currentPlayer = player;
        this.blackStatus = blackStatus;
        this.whiteStatus = whiteStatus;
        boardMatrix = board;
        this.halfMoveClock = halfMoveClock;
        this.moveCounter = moveCounter;
        pieceCount = pc;
    }

    public void move(Move move, int line) throws Exception {
        Piece piece = Utils.getPiece(move.from.getRow(), move.from.getColumn(), boardMatrix); // Piece to move

        if(piece.isValidMove(move, this)) { //if valid move apply move and switch player

            if(move.isCapture){
                isAllocatedCapture(move, line, piece);
                captureRuleSet(move, line, piece);

            } else if(move.isNormal) {
                isAllocatedMove(move, line, piece);
                moveRuleSet(move, line, piece);
            } else if(move.isPromotion) {
                isAllocatedPromotion(move, line, piece);
                promotionRuleSet(move, line, piece);

                if(isValidPromotion) {
                    Pawn pawn = (Pawn) piece;

                    pawn.makeOfficer(move.promotionPiece, this);
                }
            } else if(move.isCheck) {

                King oponentKing = getKingFor(Utils.nextPlayer(currentPlayer));
                Position kingPos = Utils.findPositionOnBoard(oponentKing, boardMatrix);

                if(!Utils.isSamePosition(kingPos, move.to)){
                    isValidCheck = false;
                    MoveValidationErrors.illegalCheck(line);
                }
            } else if(move.isCheckMate){

                King oponentKing = getKingFor(Utils.nextPlayer(currentPlayer));
                Position kingPos = Utils.findPositionOnBoard(oponentKing, boardMatrix);

                if(!oponentKing.isInCheckMate(kingPos, this)){
                    isValidCheckMate = false;
                    MoveValidationErrors.illegalCheckmate(line);
                }
            }


            if(isValidPromotion && isValidCheckMate && isValidCheck && isValidMove && isValidCapture) {

                movePiece(move, piece); // Move/Capture
                if(piece.getType() == PieceType.PAWN || piece.getType() == PieceType.DRUNKED_PAWN){
                    this.halfMoveClock++;
                }

                if(currentPlayer.equals(PlayerType.BLACK)){ // Valid Black move then increment counter
                    moveCounter++;
                }



                this.currentPlayer = Utils.nextPlayer(currentPlayer);
            }


        } // else stay on same player and throw exception
        else {
            MoveValidationErrors.illegalMove(line);
        }

        resetStatusVaraibles();
    }

    public void promotionRuleSet(Move move, int line, Piece piece) throws Exception {
        // Validate piece is a Drunked Pawn or Pawn

        //1.
        if(!piece.getClass().equals(Pawn.class)){
            isValidPromotion = false;
            MoveValidationErrors.illegalPromotion(line);
        } else {
            if(currentPlayer == PlayerType.BLACK){
                if(move.to.getRow() == 0) {
                    isValidPromotion = true;
                } else {
                    isValidPromotion = false;
                    MoveValidationErrors.illegalPromotion(line);
                }
            } else { // Is WHITE
                if(move.to.getRow() == 9) {
                    isValidPromotion = true;
                } else {
                    isValidPromotion = false;
                    MoveValidationErrors.illegalPromotion(line);
                }
            }

            int available = pieceCount.getOrDefault(move.promotionPiece ,0); // If 0 then no piece found with that Type in map

            //2.
            if(available > 0){
                isValidPromotion = true;
            } else {
                isValidPromotion = false;
                MoveValidationErrors.illegalPromotion(line);
            }

            //3.
            if(move.promotionPiece.equals(PieceType.ELEPHANT)) {
                isValidPromotion = false;
                MoveValidationErrors.illegalPromotion(line);
            }

            King k = getCurrentKing();
            Position kingPos = Utils.findPositionOnBoard(k, boardMatrix);

            //4.
            if(k.isInCheck(kingPos, this)){ //Current king is in check and move doesnt remove him from check
                Board simBoard = new Board(this, getBoardMatrix());

                simBoard.setEntryInBoardMatrix(move.from.row, move.from.column, new Space());
                simBoard.setEntryInBoardMatrix(move.to.row, move.to.column, piece);

                if(k.isInCheck(Utils.findPositionOnBoard(k, simBoard.getBoardMatrix()), simBoard)) {
                    isValidPromotion = false;
                    MoveValidationErrors.illegalPromotion(line);
                }
            } else if(k.isInCheckMate(kingPos, this)){
                isValidPromotion = false;
                MoveValidationErrors.illegalPromotion(line);
            }
        }
    }

    public void moveRuleSet(Move move, int line, Piece piece) throws Exception {
        // Invalid if move piece doesnt exist in position specified
        Position piecePos = move.from;

        if(pieceExistsAt(piece, piecePos)){
            isValidMove = true;
        } else {
            isValidMove = false;
            MoveValidationErrors.illegalMove(line);
        }

        King k = getCurrentKing();
        Position kingPos = Utils.findPositionOnBoard(k, boardMatrix);

        if(k.isInCheck(kingPos, this)){ //Current king is in check and move doesnt remove him from check
            Board simBoard = new Board(this, getBoardMatrix());

            simBoard.setEntryInBoardMatrix(move.from.row, move.from.column, new Space());
            simBoard.setEntryInBoardMatrix(move.to.row, move.to.column, piece);

            if(k.isInCheck(Utils.findPositionOnBoard(k, simBoard.getBoardMatrix()), simBoard)) {
                isValidMove = false;
                MoveValidationErrors.illegalMove(line);
            }
        } else if(k.isInCheckMate(kingPos, this)){
            isValidMove = false;
            MoveValidationErrors.illegalMove(line);
        }

        if(halfMoveClock == 50 && !(piece instanceof Pawn)) {
            isValidMove = false;
            MoveValidationErrors.illegalMove(line);
        }
    }

    public void captureRuleSet(Move move, int line, Piece piece) throws Exception {
        Piece capturedPiece = Utils.getPiece(move.to.getRow(), move.to.getColumn(), boardMatrix);

        if(capturedPiece.getOwner() != currentPlayer){
            isValidCapture = true;
        } else {
            isValidCapture = false;
            MoveValidationErrors.illegalCapture(line);
        }

        King k = getCurrentKing();
        King oponentKing = getKingFor(Utils.nextPlayer(currentPlayer));

        Position kingPos = Utils.findPositionOnBoard(k, boardMatrix);

        if(k.isInCheck(kingPos, this)){ //Current king is in check and capture doesnt remove him from check
            Board simBoard = new Board(this, getBoardMatrix());

            simBoard.setEntryInBoardMatrix(move.from.row, move.from.column, new Space());
            simBoard.setEntryInBoardMatrix(move.to.row, move.to.column, piece);

            if(k.isInCheck(Utils.findPositionOnBoard(k, simBoard.getBoardMatrix()), simBoard)) {
                isValidCapture = false;
                MoveValidationErrors.illegalCapture(line);
            }
        } else if(k.isInCheckMate(kingPos, this)){
            isValidCapture = false;
            MoveValidationErrors.illegalCapture(line);
        }
    }

    public boolean pieceExistsAt(Piece piece, Position position) {
        Piece foundPiece = Utils.getPiece(position.row, position.column, boardMatrix);

        if(piece.getClass().equals(foundPiece.getClass()) && piece.getOwner().equals(foundPiece.getOwner())) {
            return true;
        } else return false;
    }

    /**
     * Checks if Capture Boolean is Set on Move when move puts opposition King in Capture
     */
    public void isAllocatedCapture(Move move, int line, Piece movedPiece) throws Exception {
        King oponentKing = getKingFor(Utils.nextPlayer(currentPlayer));
        Board simBoard = new Board(this, getBoardMatrix());
        Position kingPos = Utils.findPositionOnBoard(oponentKing, simBoard.getBoardMatrix());


        simBoard.setEntryInBoardMatrix(move.from.row, move.from.column, new Space());
        simBoard.setEntryInBoardMatrix(move.to.row, move.to.column, movedPiece);

        if((oponentKing.isInCheck(kingPos, simBoard) && !move.isCheck) || (oponentKing.isInCheckMate(kingPos, simBoard)  && !move.isCheckMate)) {
            isValidCapture = false;
            MoveValidationErrors.illegalCapture(line);
        }
    }

    /**
     * Checks if Move boolean is set on Move when
     */
    public void isAllocatedMove(Move move, int line, Piece piece) throws Exception {
        Piece pieceAtDestination = Utils.getPiece(move.to.row, move.to.column, boardMatrix);

        if(pieceAtDestination.getOwner() == Utils.nextPlayer(currentPlayer)){
            isValidMove = false; // Shoudl've been marked as a Capture
            MoveValidationErrors.illegalMove(line);
        } else {
            isValidMove = true;
        }

        King oponentKing = getKingFor(Utils.nextPlayer(currentPlayer));
        Board simBoard = new Board(this, getBoardMatrix());
        Position kingPos = Utils.findPositionOnBoard(oponentKing, simBoard.getBoardMatrix());


        simBoard.setEntryInBoardMatrix(move.from.row, move.from.column, new Space());
        simBoard.setEntryInBoardMatrix(move.to.row, move.to.column, piece);

        if((oponentKing.isInCheck(kingPos, simBoard) && !move.isCheck) || (oponentKing.isInCheckMate(kingPos, simBoard)  && !move.isCheckMate)) {
            isValidMove = false;
            MoveValidationErrors.illegalMove(line);
        }
    }

    public void isAllocatedPromotion(Move move, int line, Piece piece) throws Exception {
        King oponentKing = getKingFor(Utils.nextPlayer(currentPlayer));
        Board simBoard = new Board(this, getBoardMatrix());
        Position kingPos = Utils.findPositionOnBoard(oponentKing, simBoard.getBoardMatrix());


        simBoard.setEntryInBoardMatrix(move.from.row, move.from.column, new Space());
        simBoard.setEntryInBoardMatrix(move.to.row, move.to.column, piece);

        if((oponentKing.isInCheck(kingPos, simBoard) && !move.isCheck) || (oponentKing.isInCheckMate(kingPos, simBoard)  && !move.isCheckMate)) {
            isValidMove = false;
            MoveValidationErrors.illegalPromotion(line);
        }
    }

    public void movePiece(Move move, Piece piece) {
        List<Piece> rowToModify = boardMatrix.get(move.to.getRow());
        rowToModify.set(move.from.getColumn(), new Space());
        rowToModify.set(move.to.getColumn(), piece);

        boardMatrix.set(move.to.getRow(), rowToModify);
    }

    private King getKingFor(PlayerType playerType){
        return (King) boardMatrix.stream().flatMap(Collection::stream)
                .filter(p -> p.getOwner() == playerType && Utils.isKing(p)).findFirst().get();
    }

    public King getCurrentKing(){
        return getKingFor(currentPlayer);
    }


    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerType currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public CostlingStatus getBlackStatus() {
        return blackStatus;
    }

    public void setBlackStatus(CostlingStatus blackStatus) {
        this.blackStatus = blackStatus;
    }

    public CostlingStatus getWhiteStatus() {
        return whiteStatus;
    }

    public void setWhiteStatus(CostlingStatus whiteStatus) {
        this.whiteStatus = whiteStatus;
    }

    public List<List<Piece>> getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardMatrix(List<List<Piece>> boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public void setEntryInBoardMatrix(int column, int row, Piece piece) {
        int rowCount = 0;
        for(List<Piece> pieces:  this.getBoardMatrix()){
            if(rowCount == row) {
                pieces.set(column, piece);

                this.boardMatrix.set(row, pieces);
            }
            rowCount++;
        }
    }

    public void setEntryInPieceCount(PieceType type, Integer i) {
        this.pieceCount.replace(type, i);
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public void setHalfMoveClock(int halfMoveClock) {
        this.halfMoveClock = halfMoveClock;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }

    public Map<PieceType, Integer> getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(Map<PieceType, Integer> pieceCount) {
        this.pieceCount = pieceCount;
    }

    public void resetStatusVaraibles(){
        this.isValidCapture = true;
        this.isValidMove = true;
        this.isValidPromotion = true;
        this.isValidCheck = true;
        this.isValidCheckMate = true;
    }
}
