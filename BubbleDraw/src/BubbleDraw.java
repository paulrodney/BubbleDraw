
import javax.swing.JFrame;

public class BubbleDraw extends JFrame {

	public static void main(String[] args) {
		//set up frame for our bubble draw app
		JFrame frame = new JFrame("Paul's BubbleDraw App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new BubblePanel());
		
		frame.pack();
		frame.setVisible(true);

	}//main

}//class BubbleDraw
