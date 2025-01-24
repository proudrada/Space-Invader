package game;

abstract class Objects {
	int x;
    int y;
    int w;
    int h;
    private int speed;
    boolean moveLeft = false;
    boolean moveRight = false;
    
    public Objects(){
    	 x = 0;
         y = 10;
    }
    
    public Objects(int x, int y, int w, int h, int s) {
    	 this.x = x;
         this.y = y;
         this.w = w;
         this.h = h;
         speed=s;
    }
    
    public abstract void move(int d);
    
    public void setPos(int x, int y) {
    	 x = getX();
         y = getY();
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;   
    }

    public int getY() {
        return y;  
    }

    public void setWidth(int w) {
          this.w = w;
    }

    public void setHeight(int h) {
          this.h = h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h; 
    }
    
    public int getSpeed() {
        return speed;
    }
}
