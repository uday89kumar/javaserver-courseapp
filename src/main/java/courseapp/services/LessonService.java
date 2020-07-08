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
import courseapp.models.Module;
import courseapp.repositories.CourseRepository;
import courseapp.repositories.LessonRepository;
import courseapp.repositories.ModuleRepository;

@RestController
public class LessonService {

	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable Integer cid, @PathVariable Integer mid, @RequestBody Lesson lesson) {
		Module module = moduleRepository.findModuleByIdAndCourseId(mid, cid);
		
		if(module != null) {
			lesson.setModule(module);
			return lessonRepository.save(lesson);
		}
		return new Lesson();
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/api/lesson/{id}")
	public ResponseEntity<String> deleteLesson(@PathVariable Integer id) {
		lessonRepository.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/lesson/{id}")
	public Optional<Lesson> findLessonById(@PathVariable Integer id) {
		return lessonRepository.findById(id);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonssForModule(@PathVariable Integer cid, @PathVariable Integer mid) {
		List<Lesson> lessons = new ArrayList<>();
		//Module module = moduleRepository.findModuleByIdAndCourseId(cid, mid);
		Optional<Module> existingModule = moduleRepository.findById(mid);
		if(existingModule.isPresent()) {
			Module module = existingModule.get();
			lessons = module .getLessons();
		}		
		return lessons;
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/api/lesson/{id}")
	public Lesson updateLesson(@PathVariable Integer id, @RequestBody Lesson lesson) {
		Lesson newLesson = new Lesson();
		Optional<Lesson> existingLesson = lessonRepository.findById(id);

		if (existingLesson.isPresent()) {
			newLesson = existingLesson.get();
			newLesson.set(lesson);
		}

		return lessonRepository.save(newLesson);
	}
}
