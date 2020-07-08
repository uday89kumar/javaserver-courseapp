package courseapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.Lesson;
import courseapp.models.Topic;
import courseapp.repositories.LessonRepository;
import courseapp.repositories.TopicRepository;

@RestController
public class TopicService {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	LessonRepository lessonRepository;

	@CrossOrigin(origins = "*")
	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics() {
		return topicRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createLesson(@PathVariable Integer cid, @PathVariable Integer mid, @PathVariable Integer lid,
			@RequestBody Topic topic) {
		Lesson lesson = lessonRepository.findTopicByLessonIdModuleIdAndCourseId(lid, mid, cid);

		if (lesson != null) {
			topic.setLesson(lesson);
			return topicRepository.save(topic);
		}
		return new Topic();
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/api/topic/{id}")
	public ResponseEntity<String> deleteTopic(@PathVariable Integer id) {
		topicRepository.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/topic/{id}")
	public Optional<Topic> findTopicById(@PathVariable Integer id) {
		return topicRepository.findById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public List<Topic> findAllTopicsForLesson(@PathVariable Integer cid, @PathVariable Integer mid,
			@PathVariable Integer lid) {
		List<Topic> topics = new ArrayList<>();
		Lesson lesson = lessonRepository.findTopicByLessonIdModuleIdAndCourseId(lid, mid, cid);
		if (lesson != null) {
			topics = lesson.getTopics();
		}
		return topics;
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/api/topic/{id}")
	public Topic updateTopic(@PathVariable Integer id, @RequestBody Topic topic) {
		Topic newTopic = new Topic();
		Optional<Topic> existingTopic = topicRepository.findById(id);

		if (existingTopic.isPresent()) {
			newTopic = existingTopic.get();
			newTopic.set(topic);
		}

		return topicRepository.save(newTopic);
	}
}
