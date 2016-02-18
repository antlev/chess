import javax.swing.text.Position;

/**
 * Created by antoine on 2/15/16.
 */
public class Move {

    private double moveOnX ;
    private double moveOnY;
    private GridPosition departure ;
    private GridPosition arrival ;

    public double getMoveOnX() {
        return moveOnX;
    }

    public void setMoveOnX(double moveOnX) {
        this.moveOnX = moveOnX;
    }

    public double getMoveOnY() {
        return moveOnY;
    }

    public void setMoveOnY(double moveOnY) {
        this.moveOnY = moveOnY;
    }

    public GridPosition getArrival() {
        return arrival;
    }

    public void setArrival(GridPosition arrival) {
        this.arrival = arrival;
    }

    public GridPosition getDeparture() {
        return departure;
    }

    public void setDeparture(GridPosition departure) {
        this.departure = departure;
    }

    public Move(GridPosition departure, GridPosition arrival){
        this.departure = departure ;
        this.arrival = arrival ;
        this.moveOnX = arrival.getColumn() - departure.getColumn();
        this.moveOnY = arrival.getLine() - departure.getLine();
    }


    public boolean isNull(){
        return false;
    }

}
