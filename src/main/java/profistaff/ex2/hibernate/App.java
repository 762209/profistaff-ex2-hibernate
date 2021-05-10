package profistaff.ex2.hibernate;

import java.awt.EventQueue;

import javax.swing.JFrame;

import profistaff.ex2.hibernate.gui.MainFrame;


/**
 * The class from which the main application frame is launched
 * @author Kambachekov Murat
 * @version 1.0
 */
public class App {
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater( () -> {
			JFrame frame = new MainFrame();
			frame.setTitle("База студентов");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
