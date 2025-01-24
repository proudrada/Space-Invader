package game;

import java.awt.Color;
import java.awt.Graphics;

public class LineMove extends Objects {
    boolean moveUp;
   
   public LineMove() {
       super();
   }

   public LineMove(int x, int y, int w, int h, int s)
   {
       super(x, y, w, h, s);
       moveUp = true;
   }

   public void move(int d){
   }
   
   protected void draw(Graphics g) {
   	g.setColor(Color.red);
   	g.fillRect(getX(),getY(),getWidth(),getHeight());
   }
}
