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

import courseapp.models.Course;
import courseapp.models.Module;
import courseapp.repositories.CourseRepository;
import courseapp.repositories.ModuleRepository;

@RestController
public class ModuleService {

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@CrossOrigin(origins = "*")
	@GetMapping("/api/module")
	public Iterable<Module> findAllModules() {
		return moduleRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable Integer courseId, @RequestBody Module module) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			module.setCourse(course);
			return moduleRepository.save(module);
		}
		return new Module();
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/api/module/{id}")
	public ResponseEntity<String> deleteModule(@PathVariable Integer id) {
		moduleRepository.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/api/module/{id}")
	public Optional<Module> findModuleById(@PathVariable Integer id) {
		return moduleRepository.findById(id);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/api/course/{cid}/module")
	public List<Module> findAllModulesForCourse(@PathVariable Integer cid) {
		List<Module> modules = new ArrayList<>();
		Optional<Course> data = courseRepository.findById(cid);
		if(data.isPresent()) {
			Course course = data.get();
			modules = course.getModules();
		}		
		return modules;
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/api/module/{id}")
	public Module updateModule(@PathVariable Integer id, @RequestBody Module module) {
		Module newModule = new Module();
		Optional<Module> existingModule = moduleRepository.findById(id);

		if (existingModule.isPresent()) {
			newModule = existingModule.get();
			newModule.set(module);
		}

		return moduleRepository.save(newModule);
	}
}
