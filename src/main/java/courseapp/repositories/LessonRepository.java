package courseapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import courseapp.models.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {

	@Query("SELECT l FROM Lesson l "
			+ "JOIN l.module m "
			+ "WHERE l.module.id = m.id "
			+ "AND l.id=:id "
			+ "AND l.module.id=:moduleId "
			+ "AND m.course.id=:courseId")
	Lesson 
		findTopicByLessonIdModuleIdAndCourseId(
				@Param("id") Integer id, 
				@Param("moduleId") Integer moduleId, 
				@Param("courseId") Integer courseId);	

}
