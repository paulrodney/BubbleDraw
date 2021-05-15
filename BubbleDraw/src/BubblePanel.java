import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BubblePanel extends JPanel {
	
	private ArrayList<Bubble> bubbleList;
	private int size = 22;
	private Timer timer;
	private final int DELAY = 33;// ms of delay
//	private boolean drawing;
	
	public BubblePanel() {
		
		bubbleList = new ArrayList<Bubble>();
		
		addMouseListener(new BubbleLisener());
		addMouseMotionListener(new BubbleLisener());
		addMouseWheelListener(new BubbleLisener());
		
		timer = new Timer(DELAY, new BubbleLisener());
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600,400));
		
		timer.start();
		
	}//constructor
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		//draw all the bubbles from bubbleList
		for(Bubble bubble:bubbleList) {
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size / 2,
					bubble.y - bubble.size / 2,
					bubble.size,
					bubble.size);
		}//for
		
		//write a number of bubbles on the screen
		page.setColor(Color.GREEN);
		page.drawString("Count: " + bubbleList.size(), 5 , 15);
	}//Graphics
	
	
	private class BubbleLisener implements MouseListener,
										   MouseMotionListener,
										   MouseWheelListener,
										   ActionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			// add to bubbleList my mouse location
						bubbleList.add(new Bubble(e.getX(), e.getY(),
								size));
						repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			timer.stop();
			// add to bubbleList my mouse location
			bubbleList.add(new Bubble(e.getX(), e.getY(),
					size));
			repaint();
			//drawing = false;
			
		}//mousePressed

		@Override
		public void mouseReleased(MouseEvent e) {
			timer.start();
			//drawing = true;
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// change size of bubbles when turn wheel
			size -= e.getWheelRotation();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// update location of bubbles
			for(Bubble bubble:bubbleList)
				//if(drawing)
					bubble.update();
			//repaint the screen
			repaint();
		}
	}//BubbleLisener
	
	
	private class Bubble {
		//a bubble needs a x and y location and a size
		public int x;
		public int y;
		public int size; 
		public Color color;
		public int xspeed;
		public int yspeed;
		public final int MAX_SPEED = 5;
		
		
		Bubble(int newX, int newY, int newSize) {
			x = newX;
			y = newY;
			size = newSize;
			color = new Color((float)Math.random(),
					(float)Math.random(),
					(float)Math.random());
			
			xspeed = (int)(Math.random()* MAX_SPEED * 2 -  MAX_SPEED);
			yspeed = (int)(Math.random()* MAX_SPEED * 2 -  MAX_SPEED);
			
			
			
		}//constructor
		
		public void update() {
			x += xspeed;
			y += yspeed;
			//collisin on side screen
			if (x < 0 || x > getWidth()) {
				xspeed = -1* xspeed;
			}
			
			while(xspeed == 0){
				xspeed = (int)(Math.random()* MAX_SPEED * 2 -  MAX_SPEED);
			}

			if(y < 0 || y > getHeight()) {
				yspeed = -yspeed;
			}
			
			while(yspeed == 0){
				yspeed = (int)(Math.random()* MAX_SPEED * 2 -  MAX_SPEED);
			}
		
		}
		
	}//class Bubble
}//class BubblePanel