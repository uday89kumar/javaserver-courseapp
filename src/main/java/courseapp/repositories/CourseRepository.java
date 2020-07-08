package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import courseapp.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {	

}
