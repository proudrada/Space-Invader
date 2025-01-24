package game;

import java.awt.Color;
import java.awt.Graphics;

public class Line {
	protected void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 470, 600, 3);
	}
}
