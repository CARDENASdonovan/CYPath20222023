import javafx.Rectangle;


public abstract class Shape{
    private int width;
    private int height;
    private String color;

    public Shape(int width, int height, String color){
        this.height = height;
        this.width = width;
        this.color = color;
    }

    @Override
    public String toString(){
        return "La taille est : " + this.height + this.width + "La couleur est : " + this.color;
    }

}   