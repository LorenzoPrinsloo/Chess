package root;

public class Move {

    Position from;
    Position to;
    PieceType promotionPiece;
    boolean isPromotion;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Move(Position from, Position to, PieceType promotionPiece, boolean isPromotion) {
        this.from = from;
        this.to = to;
        this.promotionPiece = promotionPiece;
        this.isPromotion = isPromotion;
    }

    public Position getFrom() {
        return from;
    }

    public void setFrom(Position from) {
        this.from = from;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = to;
    }
}
