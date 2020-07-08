package courseapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import courseapp.models.Topic;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {	

}
