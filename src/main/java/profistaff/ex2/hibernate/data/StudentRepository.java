package profistaff.ex2.hibernate.data;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import profistaff.ex2.hibernate.entity.Student;
import profistaff.ex2.hibernate.enums.Groups;
import profistaff.ex2.hibernate.util.JPAUtil;


/**
 * This class binds entity class {@link Student} with database
 * using java persistence API. This class provide ability to execute CRUD operations
 * @author Kambachekov Murat
 * @version 1.0
 */
public class StudentRepository {
	
	/**
	 * Returns {@link Student} object from database using id
	 * @param id - {@link Student} object identifier
	 * @return {@link Student} object
	 */
	public Student findById(Long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Student student = entityManager.find(Student.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return student;
	}
	
	/**
	 * Returns all {@link Student} objects in list from database
	 * @return list of {@link Student} object
	 */
	public List<Student> findAll() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		List<Student> studentList = entityManager.createQuery("from Student", Student.class).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return studentList;
	}
	
	/**
	 * Create new {@link Student} objects and put it in the database
	 * @param name - name of student
	 * @param surname - surname of student
	 * @param birthday - birthday of student
	 * @param group - group of student 
	 */
	public void create(String name, String surname, String patronymic, LocalDate birthday, Groups group) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Student student = new Student(name, surname, patronymic, birthday, group);
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * Update {@link Student} objects in the database
	 * @param id - {@link Student} object identifier
	 * @param name - name of student
	 * @param surname - surname of student
	 * @param birthday - birthday of student
	 * @param group - group of student 
	 */
	public void update(Long id, String name, String surname, String patronymic, LocalDate birthday, Groups group) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Student student = entityManager.find(Student.class, id);
		student.updateInfo(name, surname, patronymic, birthday, group);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * Remove {@link Student} objects from database
	 * @param id - {@link Student} object identifier
	 */
	public void remove(Long id) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Student student = entityManager.find(Student.class, id);
		entityManager.remove(student);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
