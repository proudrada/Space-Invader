package game;

import java.awt.Color;
import java.awt.Graphics;

public class Rocket extends Objects {
	  public Rocket() {
	        super();
	    }

	    public Rocket(int x, int y, int w, int h, int s) {
	        super(x, y, w, h, s);
	    }

	    public void paint(Graphics g) {
	    	g.setColor(Color.green);
	    	int[]x = {getX(),getX()+50,getX()+100,getX()+80,getX()+50,getX()+20};
			int[]y = {getY(),getY()-100,getY(),getY(),getY()-50,getY()};
			g.fillPolygon(x,y,6);
	    }

	    public void move(int d) {
	        if(moveLeft)
	            setX(getX()-getSpeed());

	        if(moveRight){
	            setX(getX()+getSpeed());
	        }

	    }

	    public void setLeft(int d) {
	    	 if(d==37) {
	             moveLeft = true;
	         }
	    }
	    
	    public void setRight(int d) {
	    	 if(d==39) {
	             moveRight = true;
	         }
        }
	    public void stop() {
	        moveLeft = false;
	        moveRight = false;
	    }
}
