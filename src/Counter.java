import java.awt.*;

public class Counter {
    private int x=0;

    public void count(){
       x++;
    }
    public void render(Graphics g){
        Graphics2D g2=(Graphics2D)g;
        g2.drawString(String.valueOf(x),40,120);
    }
}
