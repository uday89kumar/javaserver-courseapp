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

import courseapp.models.Topic;
import courseapp.models.Widget;
import courseapp.repositories.TopicRepository;
import courseapp.repositories.WidgetRepository;

@RestController
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;

	@CrossOrigin(origins = "*")
	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets() {
		return widgetRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/api/topic/{topicId}/widget")
	public Widget createWidget(@PathVariable Integer topicId,
			@RequestBody Widget widget) {
		Optional<Topic> existingTopic = topicRepository.findById(topicId);

		if (existingTopic.isPresent()) {
			Topic topic = existingTopic.get();
			widget.setTopic(topic);
			widgetRepository.save(widget);
		}
		return new Widget();
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/api/widget/{id}")
	public ResponseEntity<String> deleteWidget(@PathVariable Integer id) {
		widgetRepository.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/widget/{id}")
	public Optional<Widget> findWidgetById(@PathVariable Integer id) {
		return widgetRepository.findById(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(@PathVariable Integer topicId) {
		List<Widget> widgets = new ArrayList<>();
		Optional<Topic> existingTopic = topicRepository.findById(topicId);
		if (existingTopic.isPresent()) {
			Topic topic = existingTopic.get();
			widgets = topic.getWidgets();
		}
		return widgets;
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/api/widget/{id}")
	public Widget updateWidget(@PathVariable Integer id, @RequestBody Widget widget) {
		Widget newWidget = new Widget();
		Optional<Widget> existingTopic = widgetRepository.findById(id);
		if (existingTopic.isPresent()) {
			newWidget = existingTopic.get();
			newWidget.set(widget);
			widgetRepository.save(newWidget);
		}

		return new Widget();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/api/topic/{topicId}/widgets")
	public List<Widget> saveAllWidget(@PathVariable Integer topicId,
			@RequestBody List<Widget> widgets) {
		Optional<Topic> existingTopic = topicRepository.findById(topicId);
		List<Widget> createdWidgets = new ArrayList<>();

		if (existingTopic.isPresent()) {
			Topic topic = existingTopic.get();
			widgetRepository.deleteAllWidgetsForTopic(topic.getId());
			
			for(Widget widget: widgets) {
				widget.setTopic(topic);
				createdWidgets.add(widgetRepository.save(widget));
			}
		}
		return createdWidgets;
	}
}
