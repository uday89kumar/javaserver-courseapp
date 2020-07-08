package courseapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@ManyToOne
	@JsonIgnore
	private Course course;

	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "module")
	private List<Lesson> lessons;

	public Module() {
		super();
	}

	public Module(String title, Course course) {
		this.title = title;
		this.course = course;
	}

	public void set(Module newModule) {
		this.title = newModule.title != null ? newModule.title : this.title;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the lessons
	 */
	public List<Lesson> getLessons() {
		return lessons;
	}

	/**
	 * @param lessons
	 *            the lessons to set
	 */
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

}
