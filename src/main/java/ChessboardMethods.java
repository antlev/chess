/**
 * Created by antoine on 2/15/16.
 */
public interface ChessboardMethods {

    public abstract void start() ;
    public abstract Box getBox(int line,int column) ;
    public abstract boolean possiblePath(Move move) ;
    public abstract boolean canBeCaptured(Move move) ;
}
