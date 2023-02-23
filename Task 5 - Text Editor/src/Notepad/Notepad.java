package Notepad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener{
	
	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu help = new JMenu("Help");
	
	JMenuItem newFile = new JMenuItem("New");
	JMenuItem openFile = new JMenuItem("Open");
	JMenuItem saveFile = new JMenuItem("Save");
	JMenuItem print = new JMenuItem("Print");
	JMenuItem exit = new JMenuItem("Exit");
	
	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");
	JMenuItem selectAll = new JMenuItem("Select All");
	
	JMenuItem about = new JMenuItem("About");
	
	JTextArea textArea = new JTextArea();

	
	Notepad(){
		setTitle("Notepad Inc. by Tarun Patil");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
		setIconImage(icon.getImage());
	
		setJMenuBar(menubar);
		menubar.add(file);
		file.add(newFile);
		file.add(openFile);
		file.add(saveFile);
		file.add(new JSeparator());
		file.add(print);
		file.add(new JSeparator());
		file.add(exit);
		
		menubar.add(edit);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(new JSeparator());
		edit.add(selectAll);
		
		menubar.add(help);
		help.add(about);
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		newFile.addActionListener(this);
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		print.addActionListener(this);
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		about.addActionListener(this);
		
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("New")) {
			textArea.setText(null);
		}else if (e.getActionCommand().equalsIgnoreCase("Open")) {
			
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text documents(*.txt)", "txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textFilter);

			int action = fileChooser.showOpenDialog(null);
			
			if (action!=JFileChooser.APPROVE_OPTION) {
				return;
			}else {
				String filename = fileChooser.getSelectedFile().getAbsolutePath().toString();
				if(!filename.contains(".txt")) {
					filename = filename+".txt";
				}
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
					textArea.read(reader,null);
				}catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}else if (e.getActionCommand().equalsIgnoreCase("Save")) {
			
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text documents(*.txt)", "txt");
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(textFilter);
			
			int action = fileChooser.showSaveDialog(null);
			if (action!=JFileChooser.APPROVE_OPTION) {
				return;
			}else {
				String filename = fileChooser.getSelectedFile().getAbsolutePath().toString();
				if(!filename.contains(".txt")) {
					filename = filename+".txt";
				}
				
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
					textArea.write(writer);
				}catch (IOException ex) {
					ex.printStackTrace();
				}
					
			}
			
		}else if (e.getActionCommand().equalsIgnoreCase("Print")) {
			
			try {
				textArea.print();
			} catch (Exception e2) {
				Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, e2);
			}
			
		}else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
			
			System.exit(0);
			
		}else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
			
			textArea.cut();
			
		}else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
			
			textArea.copy();
			
		}else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
			
			textArea.paste();
			
		}else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
			
			textArea.selectAll();
			
		}else if (e.getActionCommand().equalsIgnoreCase("About")) {
			
			new About().setVisible(true);
			
		}
	}
	
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Notepad().setVisible(true);

	}

}
