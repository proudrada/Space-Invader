package game;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Objects {
	 boolean isVis;
     boolean moveDown;
    
    public Ball() {
        super();
    }

    public Ball(int x, int y, int w, int h, int s) {
        super(x, y, w, h, s);
        isVis = true;
        moveDown = true;
    }

    public void move(int d){
    }
    
    public void draw(Graphics g) {
    	g.setColor(Color.yellow);
    	g.fillOval(getX(),getY(),getWidth(),getHeight());
    }
}
