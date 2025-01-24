package Default;
import javax.swing.JPanel;

import game.PewPew_easy;
import game.PewPew_hard;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class page extends JPanel implements ActionListener, ItemListener {
	JPanel namePanel = new JPanel();
	JPanel startPanel = new JPanel();
	JLabel modeLB = new JLabel("Mode:");
	JRadioButton easyBT = new JRadioButton("Easy");
	JRadioButton hardBT = new JRadioButton("Hard");
	JLabel messageLB = new JLabel("Enter your user name");
	JTextField blankTF = new JTextField(20);
	JButton startBT = new JButton("Start");
	String message = "WELCOME TO PEW PEW GAME";
	
	public page(String s) {
		JFrame frame = new JFrame(s);
		setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		frame.add(namePanel, BorderLayout.NORTH);
		frame.add(startPanel, BorderLayout.SOUTH);
		
		GraphicsPanel graphicsPanel = new GraphicsPanel();
		frame.add(graphicsPanel, BorderLayout.CENTER);
		
		namePanel.add(messageLB);
		namePanel.add(blankTF);
		startPanel.setLayout(new GridLayout(3, 3));
		startPanel.add(new JLabel());
		startPanel.add(startBT);
		startPanel.add(new JLabel());
		startPanel.add(new JLabel());
		startPanel.add(new JLabel());
		startPanel.add(new JLabel());
		startPanel.add(modeLB);
		startPanel.add(easyBT);
		startPanel.add(hardBT);
		ButtonGroup modeGroup = new ButtonGroup(); // create a ButtonGroup object
		modeGroup.add(easyBT); // add the radio buttons to the ButtonGroup object
		modeGroup.add(hardBT);
		easyBT.addItemListener(this);
		hardBT.addItemListener(this);
		blankTF.addActionListener(this);
		startBT.addActionListener(this);
		frame.setVisible(true);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	public static void main(String[] args) {
		new page("Pew pew");
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (easyBT.isSelected()) {
	        PewPew_easy objGame1 = new PewPew_easy();
	    } 
		else if (hardBT.isSelected()) {
	        PewPew_hard objGame2 = new PewPew_hard();
	    }
	}
	
	private class GraphicsPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); // call paintComponent of the super class to clear the Panel
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
			g.drawString(message, 20, 90);
		}
	}
}
