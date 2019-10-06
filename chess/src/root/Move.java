package root;

public class Move {

    Position from;
    Position to;
    PieceType promotionPiece;
    boolean isPromotion;
    boolean isCapture;
    boolean isNormal;
    boolean isCheckMate;
    boolean isCheck;

    public Move(Position from, Position to) { // Default Move
        this.from = from;
        this.to = to;
        this.isCapture = false;
        this.isPromotion = false;
        this.isNormal = true;
        this.isCheck = false;
        this.isCheckMate = false;
    }

    public Move(Position from, Position to, boolean isCheck, boolean isCheckMate) { // Default Move
        this.from = from;
        this.to = to;
        this.isCapture = false;
        this.isPromotion = false;
        this.isNormal = true;
        this.isCheck = isCheck;
        this.isCheckMate = isCheckMate;
    }

    public Move(Position from, Position to, PieceType promotionPiece, boolean isPromotion) { // Promotion Move
        this.from = from;
        this.to = to;
        this.promotionPiece = promotionPiece;
        this.isPromotion = isPromotion;
        this.isCapture = false;
        this.isNormal = false;
    }

    public Move(Position from, Position to, boolean isCapture, boolean isCheck, boolean isCheckMate) { // Capture Move
        this.from = from;
        this.to = to;
        this.isCapture = isCapture;
        this.isPromotion = false;
        this.isNormal = false;
        this.isCheck = isCheck;
        this.isCheckMate = isCheckMate;
    }

    public Position getFrom() {
        return this.from;
    }

    public void setFrom(Position from) {
        this.from = from;
    }

    public Position getTo() {
        return this.to;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public void setCapture(boolean capture) {
        isCapture = capture;
    }
}
