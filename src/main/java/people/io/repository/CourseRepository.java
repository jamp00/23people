package people.io.repository;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import people.io.model.Course;

@Repository("CourseRepository")
public interface CourseRepository  extends PagingAndSortingRepository<Course, Serializable> {

}
