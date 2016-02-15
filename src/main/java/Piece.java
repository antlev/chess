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

    public boolean abstract isValid();
}
