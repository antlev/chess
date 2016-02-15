/**
 * Created by antoine on 2/15/16.
 */
public class Box {

    private Piece piece ;

    public Box(){

    }

    public Box(Piece piece){
        this.piece = piece ;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean pieceIsPresent()
    {
        return (piece != null);
    }

    public boolean pieceIsPresent(String color)
    {
        if (piece == null){
            return false;
        }
        else{
            return (piece.getColor().equals(color));
        }
    }
}
