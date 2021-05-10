package profistaff.ex2.hibernate.gui;



import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import profistaff.ex2.hibernate.entity.Student;

/**
 * This class used to extend classes {@link StudentCreatorFrame} and {@link StudentUpdaterFrame}.
 * Represent form for convenient way to save and update {@link Student} objects in the database.
 * In this class uses {@link org.jdesktop.swingx.JXDatePicker} for convenient date selecting
 * 
 * @author Kambachekov Murat
 * @version 1.0
 */
public class StudentFrame extends JFrame{
	/** Label for name text field */
	protected JLabel nameLabel;
	/** Label for surname text field */
	protected JLabel surnameLabel;
	/** Label for patronymic text field */
	protected JLabel patronymicLabel;
	/** Label for birthday date text field */
	protected JLabel birthdayLabel;
	/** Group list box  */
	protected JComboBox<String> groupsBox;
	/** Name input field  */
	protected JTextField nameText;
	/** Surname input field  */
	protected JTextField surnameText;
	/** Patronymic input field  */
	protected JTextField patronymicText;
	/** Picker for convenient date selecting */
	protected JXDatePicker birthdayPicker;
	
	/**
	 * Create new frame that represent form for convenient way to save and update {@link Student} objects in the database
	 */
	protected StudentFrame() {
		
		nameLabel = new JLabel("Имя");
		surnameLabel = new JLabel("Фамилия");
		patronymicLabel = new JLabel("Отчество");
		birthdayLabel = new JLabel("Дата рождения");
		nameText = new JTextField(10);
		surnameText = new JTextField(10);
		patronymicText = new JTextField(10);
		birthdayPicker = new JXDatePicker();
		groupsBox = new JComboBox<String>(new String[]{"Экономика", "Информатика", "Юриспруденция", "Менеджмент"});
		
		setLayout(new GridLayout(5, 2));
		add(nameLabel);
		add(nameText);
		add(surnameLabel);
		add(surnameText);
		add(patronymicLabel);
		add(patronymicText);
		add(birthdayLabel);
		add(birthdayPicker);
		add(groupsBox);
	}
}
