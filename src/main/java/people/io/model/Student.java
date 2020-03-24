package people.io.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import people.io.annotations.RutNumber;



@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;

	@RutNumber
	private String rut;
	private String name;
	private String lastName;

	@Min(value = 18, message = "Age should not be less than 18")
	private int age;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinColumn(name="course")
	private Course course;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int studentId, String rut, String name, String lastName, int age, Course course) {
		super();
		this.studentId = studentId;
		this.rut = rut;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.course = course;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", rut=" + rut + ", name=" + name + ", lastName=" + lastName
				+ ", age=" + age + ", course=" + course + "]";
	}

}
