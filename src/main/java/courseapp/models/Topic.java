package courseapp.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "topic")
	private List<Widget> widgets;

	public Topic() {
		super();
	}

	public Topic(String title, Lesson lesson) {
		this.title = title;
		this.lesson = lesson;
	}

	public void set(Topic newTopic) {
		this.title = newTopic.title != null ? newTopic.title : this.title;
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
	 * @return the lesson
	 */
	public Lesson getLesson() {
		return lesson;
	}

	/**
	 * @param lesson the lesson to set
	 */
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	/**
	 * @return the widgets
	 */
	public List<Widget> getWidgets() {
		return widgets;
	}

	/**
	 * @param widgets the widgets to set
	 */
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	
}
