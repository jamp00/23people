package people.io.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import people.io.model.Student;
import people.io.repository.StudentRepository;

@CrossOrigin
@RestController
@RequestMapping(value="/student/", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

	@Autowired
	private StudentRepository StudentRepository;

	// Find pagination Student
	@GetMapping("/{pageNbr}/{pageSize}")
	public ResponseEntity<Page<Student>> allStudentsPageable(@PathVariable(value="pageNbr") int pageNbr, @PathVariable(value="pageSize") int pageSize,  HttpServletResponse response) {

		Pageable pageable = PageRequest.of(pageNbr, pageSize);

		return ResponseEntity.ok(StudentRepository.findAll(pageable));
	}

	//Find all Students
	@SuppressWarnings("unchecked")
	@GetMapping("/all")
	public ResponseEntity<List<Student>> allStudents(HttpServletResponse response)  {

		return (ResponseEntity<List<Student>>) StudentRepository.findAll();
	}

	// Find a Student by id
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable(value = "id") int id) {

		return StudentRepository.findById(id)
				.map( obj->{
					return new ResponseEntity<Student>(obj, HttpStatus.OK); })
				.orElseGet(()->{
					return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
				});
	}

	// Save a Student by JSON
	@PostMapping("/create")
	public ResponseEntity<Student> newStudent(@RequestBody @Valid Student student) {

//		return StudentRepository.save(student);
		return new ResponseEntity<Student>(StudentRepository.save(student), HttpStatus.OK);
	}

	// Update a Student by id and return the Student updated, if not find them return an empty Student
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@RequestBody @Valid Student student, @PathVariable(value = "id") int id) {

		return StudentRepository.findById(id)
				.map(obj->{
					obj.setRut(student.getRut());
					obj.setName(student.getName());
					obj.setLastName(student.getLastName());
					obj.setAge(student.getAge());
					obj.setCourse(student.getCourse());
					return new ResponseEntity<Student>(obj, HttpStatus.OK);
				})
				.orElseGet( () -> {
					return new ResponseEntity<Student>(new Student(), HttpStatus.NOT_FOUND);
				});

	}

	// Delete a Student by id
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable (value="id") Integer id) {

		if (StudentRepository.existsById(id)) {
			StudentRepository.deleteById(id);
			ResponseEntity.ok();
		}

		ResponseEntity.notFound();
	}

}






















