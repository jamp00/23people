package people.io.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import people.io.model.Student;
import people.io.repository.StudentRepository;


@Service
public class StudentImplement {

	@Autowired
	private StudentRepository StudentRepository;


	public Page<Student> findAll(Pageable pageable) {

		return StudentRepository.findAll(pageable);
	}

	public List<Student> findAll() {
		
		return (List<Student>) StudentRepository.findAll();
	}

	public Optional<Student> findById(int id) {
		
		return StudentRepository.findById(id);
	}

	public Student createCurse(Student Student ) {
		
		return StudentRepository.save(Student);
	}

	public boolean deleteIfExistStudent(Integer id) {

		if (StudentRepository.existsById(id)) {
			StudentRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
}
