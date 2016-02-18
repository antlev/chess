/**
 * Created by antoine on 2/15/16.
 */
public class King extends Piece{
    // Constructor initialising a king piece
    @Override
    public boolean isValid(Move move) {
        return false;

    }

    public King(String color){
        super("King",color);
    }
}
