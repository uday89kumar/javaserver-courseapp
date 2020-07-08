package courseapp.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import courseapp.models.Widget;

@Repository
public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Widget w WHERE w.topic.id=:topicId")
	void 
		deleteAllWidgetsForTopic
			(@Param("topicId") Integer topicId);

}
