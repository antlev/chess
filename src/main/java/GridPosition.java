/**
 * Created by antoine on 2/15/16.
 */


public class GridPosition {
    public int line ;
    public int column ;

    public GridPosition(int line,int column){
        this.line = line ;
        this.column = column ;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColonne(int colonne) {
        this.column = column;
    }


    /**
        Return the piece's position in grid
     */
    public boolean equals(GridPosition pos){
        return false ;
    }
}
