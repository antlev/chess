/**
 * Created by antoine on 2/15/16.
 */
public class Rook extends Piece {
    public Rook (String color) {
        super("Rook", color);
    }

    @Override
    public boolean isValid(Move move) {
        return false;
    }
}
