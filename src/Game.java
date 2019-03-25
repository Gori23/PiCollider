import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Block block;
    private Block block1;
    private Counter counter;
    private int TimeStep=10000;
    double dig=4;
    double digits=Math.pow(100,dig);



    public Game() {
        new Window(WIDTH, HEIGHT, "LETS Built a Game", this);
        counter=new Counter();
        block=new Block(WIDTH/2,HEIGHT-80,40,40,digits, Color.RED,-0.0001,counter);
        block1=new Block(WIDTH/2-70,HEIGHT-50,10,10,1, Color.WHITE,0,counter);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                double timeBeforeTick = System.nanoTime();
                render();
                double timeAfterTIck = System.nanoTime();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        for(int i=0;i<TimeStep;i++) {


            if (block.Collision(block1)) {
                counter.count();
                double v = block.Bounce(block1);
                double v1 = block1.Bounce(block);
                block.SetVelX(v);
                block1.SetVelX(v1);
            }
            block.tick();
            block1.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        block.render(g);
        block1.render(g);
        counter.render(g);
        g.dispose();
        bs.show();


    }
}
