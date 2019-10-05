package main;

public class Board {
    PlayerType currentPlayer;
    CostlingStatus blackStatus;
    CostlingStatus whiteStatus;
    char[][] boardMatrix;
    int halfMoveClock;
    int moveCounter; // Evertime Black moves this increments

    public Board(PlayerType player, char[][] board) {
        currentPlayer = player;
        blackStatus = new CostlingStatus();
        whiteStatus = new CostlingStatus();
        boardMatrix = board;
        halfMoveClock = 0;
        moveCounter = 0;
    }

    public void move() {
        if(currentPlayer.equals(PlayerType.BLACK)){ // TODO move this logic so that it's not triggered by invalid moves
            moveCounter++;
        }
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

    public char[][] getBoardMatrix() {
        return boardMatrix;
    }

    public void setBoardMatrix(char[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
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
}