package profistaff.ex2.hibernate.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import profistaff.ex2.hibernate.data.StudentRepository;

import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import profistaff.ex2.hibernate.enums.Groups;

/**
 * This class represent info about student such as name, surname, patronymic and birthday date.
 * This class binds with database via {@link StudentRepository} class.
 * This class constructed via lombok documentation and java persistence API.
 * @see     lombok.Data;
 * @see     lombok.NoArgsConstructor;
 * @author  Kambachekov Murat
 * @version 1.0
 * 
 */
@Data
@NoArgsConstructor
@Entity
@Table( name = "STUDENTS" )
public class Student {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;
	private String name;
	private String surname;
	private String patronymic;
	private LocalDate birthday;
	
	@Column(name = "group_name")
	@Enumerated(EnumType.STRING)
	private Groups group;
	
	/**
	 * Create new {@link Student} object
	 * @param name - name of student
	 * @param surname - surname of student
	 * @param birthday - birthday of student
	 * @param group - group of student
	 */
	public Student(String name, String surname, String patronymic, LocalDate birthday, Groups group) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthday = birthday;
		this.group = group;
	}
	
	/**
	 * Update info about student
	 * @param name - name of student
	 * @param surname - surname of student
	 * @param birthday - birthday of student
	 * @param group - group of student
	 */
	public void updateInfo(String name, String surname, String patronymic, LocalDate birthday, Groups group) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birthday = birthday;
		this.group = group;
	}
	
	
}
