package people.io.controller;

import java.util.List;

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

import people.io.model.Course;
import people.io.repository.CourseRepository;


@CrossOrigin
@RestController
@RequestMapping(value="/course/", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	// Find pagination course
	@GetMapping("/{pageNbr}/{pageSize}")
	public ResponseEntity<Page<Course>> allCoursesPageable(@PathVariable(value="pageNbr") int pageNbr,
				@PathVariable(value="pageSize") int pageSize) {

		Pageable pageable = PageRequest.of(pageNbr, pageSize);

		return ResponseEntity.ok(courseRepository.findAll(pageable));
	}

	//Find all courses
	@SuppressWarnings("unchecked")
	@GetMapping("/all")
	public ResponseEntity<List<Course>> allCourses()  {

		return  (ResponseEntity<List<Course>>) courseRepository.findAll();
	}

	// Find a Course by id
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable(value = "id") int id) {

		return courseRepository.findById(id)
				.map( obj ->{
					return new ResponseEntity<Course>(obj, HttpStatus.OK); })
				.orElseGet(()->{
					return new ResponseEntity<Course>(new Course(), HttpStatus.NOT_FOUND);
				});
	}
 
	// Save a Course by JSON
	@PostMapping("/create")
	public ResponseEntity<Course> newCourse(@RequestBody @Valid Course course) {

		return new ResponseEntity<Course>(courseRepository.save(course), HttpStatus.OK);
	}
	
	// Update a Course by id and return the Course updated, if not find them return an empty Course
	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@RequestBody @Valid Course course, @PathVariable(value = "id") int id) {

		return courseRepository.findById(id)
				.map(obj->{
					obj.setName(course.getName());
					obj.setCode(course.getCode());
					return new ResponseEntity<Course>(obj, HttpStatus.OK);
				})
				.orElseGet( () -> {
					return new ResponseEntity<Course>(new Course(), HttpStatus.NOT_FOUND);
				});
	}

	// Delete a Course by id
	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable (value="id") Integer id) {

		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
			ResponseEntity.ok();
		}
		ResponseEntity.notFound();
	}


}





























