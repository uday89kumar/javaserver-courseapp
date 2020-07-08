package courseapp.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import courseapp.models.User;
import courseapp.repositories.UserRepository;

@RestController
public class UserService {
	private static final String CURRENT_USER = "currentUser";
	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/user")
	public Iterable<User> findAllUsers(
			@RequestParam(name="username", required=false) String username, 
			@RequestParam(name="password", required=false) String password) {
		if(username != null && password != null)
			return userRepository.findUserByCredentials(username, password);
		else if(username != null)
			return userRepository.findUserByUsername(username);
		return userRepository.findAll();
	}

	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session) {
		List<User> userRecord = (List<User>) userRepository.findUserByCredentials(user.getUsername(),
				user.getPassword());
		if (userRecord != null && userRecord.size() == 1) {
			session.setAttribute(CURRENT_USER, userRecord.get(0));
			return userRecord.get(0);
		}
		return new User();
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		List<User> userRecord = (List<User>) userRepository.findUserByUsername(user.getUsername());
		if (userRecord == null || userRecord.size() == 0) {
			return userRepository.save(user);
		}
		return new User();
	}

	@DeleteMapping("/api/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		userRepository.deleteById(userId);
		return new ResponseEntity<String>(HttpStatus.OK); 
	}

	@GetMapping("/api/user/{userId}")
	public Optional<User> findById(@PathVariable Integer userId) {
		return userRepository.findById(userId);
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable Integer userId, @RequestBody User user) {
		User existingUser = new User();
		Optional<User> existingRecord = userRepository.findById(userId);
		
		if(existingRecord.isPresent()) {
			existingUser = existingRecord.get();
			existingUser.set(user);
		}
		
		return userRepository.save(existingUser);
	}

	@PostMapping("/api/register")
	public ResponseEntity<String> register(@RequestBody User user, HttpSession session) {
		List<User> userRecord = (List<User>) userRepository.findUserByUsername(user.getUsername());
		if (userRecord == null || userRecord.size() == 0) {
			session.setAttribute(CURRENT_USER, user);
			userRepository.save(user);
			return new ResponseEntity<String>(HttpStatus.OK); 
		}
		
		return new ResponseEntity<String>(HttpStatus.CONFLICT); 
	}

	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user, HttpSession session) {
		User currentUser = (User) session.getAttribute(CURRENT_USER);
		if (currentUser.getUsername().equals(user.getUsername())) {
			currentUser.set(user);
			return userRepository.save(currentUser);
		}
		return new User();
	}

	@PostMapping("/api/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<String>(HttpStatus.OK); 
	}
	
	@GetMapping("/api/sessionUser")
	public User getSessionUser(HttpSession session) {
		User user = (User) session.getAttribute(CURRENT_USER);
		if(user == null)
			user = new User();
		
		return user;
	}
}
