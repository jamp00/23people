package people.io.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import people.io.model.Course;
import people.io.repository.CourseRepository;


@Service
public class CourseImplement {

	@Autowired
	private CourseRepository courseRepository;


	public Page<Course> findAll(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

	public List<Course> findAll() {
		return (List<Course>) courseRepository.findAll();
	}

	public Optional<Course> findById(int id) {
		return courseRepository.findById(id);
	}

	public Course createCurse(Course course ) {
		return courseRepository.save(course);
	}

	public boolean deleteIfExistCourse(Integer id) {

		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
