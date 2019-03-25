import java.awt.*;

public abstract class GameObject {

    abstract void tick();
    abstract void render(Graphics g);
    protected double x,y,wysokosc,szerokosc,masa,przyspiezzenie;
    protected Color c;
    public GameObject(double x,double y, double wysokosc,double szerokosc,double masa,Color c,double przyspiezzenie){
        this.x=x;
        this.y=y;
        this.wysokosc=wysokosc;
        this.szerokosc=szerokosc;
        this.masa=masa;
        this.c=c;
        this.przyspiezzenie=przyspiezzenie;
    }
    public void SetVelX(double VelX){
        this.przyspiezzenie=VelX;
    }

}
