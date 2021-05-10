package profistaff.ex2.hibernate.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import profistaff.ex2.hibernate.data.StudentRepository;
import profistaff.ex2.hibernate.entity.Student;
import profistaff.ex2.hibernate.enums.Groups;
import profistaff.ex2.hibernate.util.GroupsConverter;

/**
 * This class represent main frame of GUI. This frame provides visual representation about information
 * of all student persist in the database. This frame give you interface to execute CRUD operations on
 * the database. All operations that executes with database perform in the background.
 * 
 * @author Kambachekov Murat
 * @version 1.0
 */
public class MainFrame extends JFrame {
	
	/** Class for remove object from database in background*/
	private StudentRemover studentRemover;
	/** Button that call frame to create new student*/
	private JButton createStudentButton;
	/** Panel that showing all students info stored in the database*/
	private JPanel panel;
	
	/**
	 * Create main frame for convenient way work with student database
	 */
	public MainFrame() {
		createStudentButton = new JButton("Создать нового студента");
		createStudentButton.addActionListener( (event) -> {
			JFrame studentCreatorFrame = new StudentCreatorFrame();
			studentCreatorFrame.setTitle("Создать новго студента");
			studentCreatorFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			studentCreatorFrame.setVisible(true);
		});
		
		panel = new JPanel(new GridLayout(1,1));
		panel.add(getHeaderPanel());
		
		setLayout(new BorderLayout());
		add(createStudentButton, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		
		pack();
	}
	
	/**
	 * Show in the panel all {@link Student} object stored in the database
	 * @param studentList - All {@link Student} objects in {@link List} representation from the database
	 */
	private void getAllStudent(List<Student> studentList) {
		remove(panel);
		panel = new JPanel(new GridLayout(studentList.size()+1, 1));
		panel.add(getHeaderPanel());
		
		for (Student student : studentList) {
			JPanel studentPanel = createStudentPanel(student.getId(), student.getName(),
													 student.getSurname(), student.getPatronymic(), 
													 student.getBirthday(), student.getGroup());
			panel.add(studentPanel);
		}
		add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Create header for {@link for Student} objects
	 */
	private JPanel getHeaderPanel(){
		JPanel headerPanel = new JPanel(new GridLayout(1,8));
		
		List<JComponent> componentList = new ArrayList<JComponent>(8);
		componentList.add(new JLabel("№"));
		componentList.add(new JLabel("Имя"));
		componentList.add(new JLabel("Фамилия"));
		componentList.add(new JLabel("Отчество"));
		componentList.add(new JLabel("Дата рождения"));
		componentList.add(new JLabel("Группа"));
		componentList.add(new JLabel("Удаление"));
		componentList.add(new JLabel("Редактирование"));
		
		for (JComponent component : componentList) {
			component.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			component.setFont(new Font("Calibri", Font.BOLD, 14));
			headerPanel.add(component);
		}
		
		return headerPanel;
	}
	
	/**
	 * Create {@link JPanel} object that represent {@link Student} objects info
	 * @param id - student identifier
	 * @param name - student name
	 * @param surname - student surname
	 * @param patronymic - student patronymic
	 * @param birthday - student birthday date
	 * @param group - group of student
	 * @return panel with students info
	 */
	private JPanel createStudentPanel(Long id, String name, String surname, String patronymic, LocalDate birthday, Groups group) {
		JPanel studentPanel = new JPanel(new GridLayout(1, 8));
		/**Creates a button to remove panel with student info from frame and remove student object from database*/
		JButton deleteButton = new JButton("Удалить");
		/**Creates a button to update panel the student information panel from frame and update student object from the database*/
		JButton updateButton = new JButton("Редактировать");
		deleteButton.addActionListener( (event) -> {
			studentRemover = new StudentRemover(id);
			studentRemover.execute();
		});
		updateButton.addActionListener( (event -> {
			JFrame studentUpdaterFrame = new StudentUpdateFrame(id);
			studentUpdaterFrame.setTitle("Редактировать  студента");
			studentUpdaterFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			studentUpdaterFrame.setVisible(true);
			
		}));
		
		List<JComponent> componentList = new ArrayList<JComponent>(8);
		componentList.add(new JLabel(id.toString()));
		componentList.add(new JLabel(name));
		componentList.add(new JLabel(surname));
		componentList.add(new JLabel(patronymic));
		componentList.add(new JLabel(birthday.toString()));
		componentList.add(new JLabel(GroupsConverter.groupToString(group)));
		componentList.add(deleteButton);
		componentList.add(updateButton);
		
		for (JComponent component : componentList) {
			component.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			component.setFont(new Font("Calibri", Font.ITALIC, 12));
			studentPanel.add(component);
		}
		
		return studentPanel;
	}
	
	/**
	 * Frame that represent form for convenient way to save {@link Student} objects in the database
	 * @author Kambachekov Murat
	 * @version 1.0
	 */
	protected class StudentCreatorFrame extends StudentFrame {
		
		/**Creates button for persist new student object to the database */
		private JButton createButton;
		
		/**
		 * Create new frame that represent form for convenient way to save {@link Student} objects in the database
		 */
		protected StudentCreatorFrame() {
			super();
			createButton = new JButton("Создать студента");
			createButton.addActionListener( (event) -> {
				StudentCreator studentCreator = new StudentCreator();
				studentCreator.execute();
			});
			add(createButton);
			pack();
		}
		
		/**
		 * This class in the background creates and saves new {@link Student} object in the database 
		 */
		private class StudentCreator extends StudentSwingWorker {
			/**
			 * Creates and saves new {@link Student} object in the database
			 * @return updated list of student objects in list representation
			 */
			@Override
			protected List<Student> doInBackground() throws Exception {
				repository.create(nameText.getText(), surnameText.getText(), patronymicText.getText(),
								  birthdayPicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
								  GroupsConverter.stringToGroup((String)groupsBox.getSelectedItem()) );
				return repository.findAll();
			}
		}
	}
	
	/**
	 * Frame that represent form for convenient way to update {@link Student} objects in the database
	 * @author Kambachekov Murat
	 * @version 1.0
	 */
	protected class StudentUpdateFrame extends StudentFrame {
		/** student identifier */
		private Long id;
		/**Creates button for update new student object in the database */
		private JButton updateButton;
		
		/**
		 * Create new frame that represent form for convenient way to update {@link Student} objects in the database
		 * @param id - student identifier
		 */
		protected StudentUpdateFrame(Long id) {
			super();
			this.id = id;
			updateButton = new JButton("Обновить студента");
			updateButton.addActionListener( (event) -> {
				StudentUpdater studentUpdater = new StudentUpdater();
				studentUpdater.execute();
			});
			add(updateButton);
			pack();
		}
		
		/**
		 * This class in the background updates and saves new {@link Student} object in the database 
		 */
		protected class StudentUpdater extends StudentSwingWorker {
			
			/**
			 * Updates and saves new {@link Student} object in the database
			 * @return updated list of student objects
			 */
			@Override
			protected List<Student> doInBackground() throws Exception {
				repository.update(id, nameText.getText(), surnameText.getText(), patronymicText.getText(),
								  birthdayPicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
								  GroupsConverter.stringToGroup((String)groupsBox.getSelectedItem()) );
				return repository.findAll();
			}
		}
	}
	
	/**
	 * This class in the background delete {@link Student} object from the database 
	 */
	protected class StudentRemover extends StudentSwingWorker {
		
		/** student identifier */
		private Long id;
		
		protected StudentRemover(Long id) {
			this.id = id;
		}
		
		/**
		 * Remove {@link Student} object from the database
		 * @return updated list of student objects
		 */
		@Override
		protected List<Student> doInBackground() throws Exception {
			repository.remove(id);
			return repository.findAll();
		}
	}
	
	/**
	 * This is abstract class used for extend classes for execute CRUD operations via GUI.
	 * This class override SwingWorker done method for update panel with student after execute CRUD operations
	 */
	protected abstract class StudentSwingWorker extends SwingWorker<List<Student>, Void>{
		
		/**Repository that connect GUI with database */
		protected StudentRepository repository = new StudentRepository();
		
		/**
		 * Update panel with student after execute CRUD operations
		 */
		@Override
		protected void done() {
			try {
				List<Student> studentList = get();
				getAllStudent(studentList);
				MainFrame.this.pack();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
