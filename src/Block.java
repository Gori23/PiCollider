import java.awt.*;

public class Block extends GameObject{
    private Counter counter;

    public Block(double x, double y, double wysokosc, double szerokosc, double masa, Color c, double velX, Counter counter) {
        super(x, y, wysokosc, szerokosc, masa, c,velX);
        this.counter=counter;
    }

    @Override
    void tick() {
        x+=przyspiezzenie;
        if(x<0){
            przyspiezzenie*=-1;
            counter.count();
        }
    }

    @Override
    void render(Graphics g) {
        g.setColor(c);
        g.fillRect((int)x,(int)y,(int)szerokosc,(int)wysokosc);
    }
    public boolean Collision(Block block){
        if(this.x+szerokosc<block.x||this.x>block.x+block.szerokosc){
            return false;
        }
        else{
            return true;
        }

    }
    public double Bounce(Block block){

        double sumM=this.masa+block.masa;
        double NewValX=(this.masa-block.masa)*this.przyspiezzenie/sumM;
        NewValX+=2*block.masa/sumM*block.przyspiezzenie;
        return NewValX;
    }
}
