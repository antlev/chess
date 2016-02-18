/**
 * Created by antoine on 2/15/16.
 */
public class Knight extends Piece{
    // Constructor initialising a knight piece
    public Knight(String color){
        super("Knight",color);
    }

    @Override
    public boolean isValid(Move move) {
        return false;
    }

    public boolean isValid(){
        return false;
    }
}
