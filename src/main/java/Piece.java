/**
 * Created by antoine on 2/15/16.
 */
public  abstract class Piece {
    private  String name;
    private String color;

    public Piece(String name,String color){
        this.name = name;
        this.color = color ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean abstract isValid();
}
