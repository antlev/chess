import javax.swing.text.Position;

/**
 * Created by antoine on 2/15/16.
 */
public class Move {

    private double moveOnX ;
    private double moveOnY;
    private Position departure ;
    private Position arrival ;

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

    public Position getArrival() {
        return arrival;
    }

    public void setArrival(Position arrival) {
        this.arrival = arrival;
    }

    public Position getDeparture() {
        return departure;
    }

    public void setDeparture(Position departure) {
        this.departure = departure;
    }

    public Move(GridPosition departure, GridPosition arrival){
        this.departure = departure ;
        this.arrival = arrival ;
    }


    public boolean isNull(){

    }

}
