package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PewPew_hard {
	public PewPew_hard() {
		JFrame frame = new JFrame();
		frame = new JFrame("Pew pew");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Game_hard(frame.getSize()));
		frame.setVisible(true);
	}

public class Game_hard extends JPanel implements ActionListener, Runnable, KeyListener {
    private Thread animator;
    int xAxis = 30;
    int yAxis = 30;
    Rocket r;
    Ball[][] b = new Ball[3][10];
    Shot s;
    LineMove l;
    boolean gameOn = false;
    private Timer timer;
	long startTime = System.nanoTime();
	long endTime = System.nanoTime();
	long elapsedTime = endTime - startTime;
	double elapsedMs = elapsedTime / 1000000.0;
	int visibleBalls = 0;
    
    public Game_hard(Dimension dimension) {
        r = new Rocket(200,600,57,35,5);
        s = new Shot(2250000,450,5,20,15);
        l = new LineMove(0, 470, 600, 3,1);
        int x = 125;
        int y = 50;
        for(int i = 0; i<b.length; i++){
            for (int j = 0; j<b[0].length; j++){
                b[i][j] = new Ball(x,y,30,20,1);
                x += 35;
                timer = new Timer(4300, this);
                timer.start();
            }
            x = 125;
            y += 25;
        }
        addKeyListener(this) ; 
        setFocusable(true);
        if (animator == null) {
            animator = new Thread(this);
            animator.start();
        }
    }
    
    public void reset() {
    	xAxis = 30;
        yAxis = 50;
        b = new Ball[3][10];
        
        gameOn = false;
        r = new Rocket(200,600,57,35,5);
        s = new Shot(2250000,450,5,20,15);
        l = new LineMove(0, 470, 600, 3,1);
        startTime = System.nanoTime();
        int x = 125;
        int y = 30;
        for(int i = 0; i<b.length; i++){
            for (int j = 0; j<b[0].length; j++){
            	b[i][j] = new Ball(x,y,30,20,1);
            	x += 35;
            	timer = new Timer(4200, this);
            }
            x = 125;
            y += 25;
        }
    }
    
    protected void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	Dimension d = getSize(); //draw things to the screen
    	// create a background
        g2.setColor(Color.black);
        g2.fillRect(0, 0, d.width, d.height);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j].isVis) {
                    visibleBalls++;
                }
            }
        }
        int score = visibleBalls * 100 - (int) elapsedMs;

        boolean anyBallVisible = false;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j].isVis) {
                    anyBallVisible = true;
                    break;
                }
            }
            if (anyBallVisible) {
                break;
            }
        }
        
		if (gameOn == true) {
			gameSet();
			r.move(0);
			s.move(0);
			for (int i = 0; i < b.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					if (anyBallVisible && b[i][j].getY() <= l.getY()) {
						// count time
						long currentTime = System.nanoTime();
						elapsedMs = (currentTime - startTime) / 1000000.0;
						g.setColor(Color.white);
						g.setFont(new Font("serif", Font.BOLD, 25));
						g.drawString("Time: " + String.format("%.2f", elapsedMs), 420, 30);
					}
				}
			}
		}
        
        else {
        	  if (!gameOn) {
                  boolean allBallsNotVisible = true;
                  for (int i = 0; i < b.length; i++) {
                      for (int j = 0; j < b[0].length; j++) {
                          if (b[i][j].isVis) {
                              allBallsNotVisible = false;
                              break;
                          }
                      }
                      if (!allBallsNotVisible) {
                          break;
                      }
                  }
                  if (allBallsNotVisible) {
                      g2.setColor(Color.pink);
                      g2.setFont(new Font("serif", Font.BOLD, 30));
                      g2.drawString("You Won, Score: "+score, 200, 400);

                      g2.setColor(Color.pink);
                      g2.setFont(new Font("serif", Font.BOLD, 20));
                      g2.drawString("Press (Enter) to Restart", 200, 450);
                  }
                  else {
                      for (int i = 0; i < b.length; i++) {
                          for (int j = 0; j < b[0].length; j++) {
                              if (b[i][j].getY() >= l.getY()) {
                                  gameOn = false;
                                  g2.setColor(Color.pink);
                                  g2.setFont(new Font("serif", Font.BOLD, 30));
                                  g2.drawString("Game Over", 200, 400);

                                  g2.setColor(Color.pink);
                                  g2.setFont(new Font("serif", Font.BOLD, 20));
                                  g2.drawString("Press (Enter) to Restart", 200, 450);
                              }
                          }
                      }
                  }
              }
        }
        s.paint(g2);
        r.paint(g2);
        l.draw(g2);
        hitDetect();

        for(int i = 0; i<b.length; i++) {
            for (int j = 0; j<b[0].length; j++) {
                if(b[i][j].isVis)
                    b[i][j].draw(g2);
            }
        }
    } 
    
    public void hitDetect() {
    	for(int i = 0; i<b.length; i++){
            for (int j = 0; j<b[0].length; j++){
                if (b[i][j].isVis == true && s.getX() + s.getWidth() >= b[i][j].getX()-20 && 
                s.getX() <= (b[i][j].getX()-20) + b[i][j].getWidth() && 
                s.getY() + s.getHeight() - 80 >= (b[i][j].getY()) && 
                s.getY() - 80 <= b[i][j].getY() + b[i][j].getHeight()) {
                	b[i][j].isVis = false; 
                    s.x = -30;
                }
            }
         }
    }
    
    public void gameSet(){
        for(int i = 0; i<b.length; i++) {
            for (int j = 0; j<b[0].length; j++) {
            	if(b[i][j].getY()>l.getY()) {
                	gameOn = false;
                }
            }
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		 int k = e.getKeyCode();
         if (k == KeyEvent.VK_ENTER) {
        	startTime = System.nanoTime();
            elapsedMs = 0;
         	reset();
         	gameOn = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	 r.setRight(k);
         }
         if(e.getKeyCode() == KeyEvent.VK_LEFT) {
        	 r.setLeft(k);
         }
         if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	 if(k==32) {
        		 s.goUp=true;
        		 s.setX(r.getX() + (r.getWidth()/2));
        		 s.setY(r.getY() );
        	 }
         }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		r.stop();
	}
	@Override
	public void run() {
		long beforeTime;
		//int sleep;
        beforeTime = System.currentTimeMillis();
        int animationDelay = 30;
        long time = System.currentTimeMillis();
        while (true) {// infinite loop
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0, time - System.currentTimeMillis()));
            } 
            catch (InterruptedException e) {
                System.out.println(e);
            } 
        } 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		gameOn=true;
		for(int i = 0; i<b.length; i++){
            for (int j = 0; j<b[0].length; j++){
                if(b[i][j].getY()<0)
                	b[i][j].moveDown = true;
                if(b[i][j].moveDown) 
                	b[i][j].setY(b[i][j].getY()+b[i][j].getSpeed());
            }
		}
		if(l.getY()<0)
        	l.moveUp = true;
		if(l.moveUp) 
        	l.setY(l.getY()-l.getSpeed());
	}
}
}