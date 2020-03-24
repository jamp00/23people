package people.io.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import people.io.model.Student;

@Repository
public interface StudentRepository  extends PagingAndSortingRepository<Student, Serializable> {

}
