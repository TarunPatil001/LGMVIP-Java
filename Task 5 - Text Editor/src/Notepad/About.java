package Notepad;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame {
	
	About(){
		setBounds(100, 100, 500, 400);
		setTitle("About Notepad");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
		setIconImage(icon.getImage());
		
		setLayout(null);
		
		
		JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("Notepad.png")));
		iconLabel.setBounds(100, 50, 600, 80);
		add(iconLabel);
		
		JLabel textLabel = new JLabel("<html>Welcome to Notepad.<br>Welcome to Notepad.<br><br>Advanced Level Task<br>Created using <ul><li>Java</li><li>Java Swing</li><li>Java AWT</li><li>JTextArea</li><li>JMenuBar</li><li>JMenu</li><li>JMenuItems</li></ul><br>- Created by <em>Tarun Patil</em><br>All rights reserved @2023</html>");
		textLabel.setBounds(100, 10, 500, 300);
		textLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN,16));
		add(textLabel);
		
	}

	public static void main(String[] args) {
		new About().setVisible(true);

	}

}
