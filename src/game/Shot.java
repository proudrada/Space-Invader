package game;

import java.awt.Color;
import java.awt.Graphics;

public class Shot extends Objects {
	boolean goUp;
	
    public Shot() {
        super();
    }

    public Shot(int x, int y, int w, int h, int s) {
        super(x, y, w, h, s);
        setY(600);
        goUp = false;
    }

    public void move(int d) {
    	if(getY()<0){
            goUp=false;
            setY(600);
        }
    	if(goUp) { 
            setY(getY()-getSpeed());
    	}
    }

    public void paint(Graphics g) {
    	g.setColor(Color.white);
    	g.fillRect(getX()+20, getY()-80, getWidth(),getHeight());
    }


    public void setLeftRight(int d) {
        if(d==37) {
            moveLeft = true;
        }

        if(d==39) {
            moveRight = true;
        }

    }

    public void stop() {
        moveLeft = false;
        moveRight = false;
    }
}