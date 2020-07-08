package courseapp.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import courseapp.utils.ListType;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int position;
	private String text;
	private String classname;
	private String style;
	private String width;
	private String height;
	private int size;
	private String src;
	private String href;
	private String listItems;
	
	@Enumerated(EnumType.STRING)
	private ListType listType;
	
	@ManyToOne
	@JsonIgnore
	private Topic topic;

	public Widget() {
		super();
	}

	public Widget(String name, int position, String text, String className, String style, String width, String height, 
			int size, String src, String href, String listItems, ListType listType,
			Topic topic) {
		this.name = name;
		this.position = position;
		this.text = text;
		this.classname = className;
		this.style = style;
		this.width = width;
		this.height = height;
		this.topic = topic;
		this.size = size;
		this.src = src;
		this.href = href;
		this.listItems = listItems;
		this.listType = listType;
	}

	public void set(Widget newWidget) {
		this.name = newWidget.name != null ? newWidget.name : this.name;
		this.position = newWidget.position != 0 ? newWidget.position : this.position;
		this.text = newWidget.text != null ? newWidget.text : this.text;
		this.classname = newWidget.classname != null ? newWidget.classname : this.classname;
		this.style = newWidget.style != null ? newWidget.style : this.style;
		this.width = newWidget.width != null ? newWidget.width : this.width;
		this.height = newWidget.height != null ? newWidget.height : this.height;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the className
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassname(String className) {
		this.classname = className;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * @return the topic
	 */
	public Topic getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the listItems
	 */
	public String getListItems() {
		return listItems;
	}

	/**
	 * @param listItems the listItems to set
	 */
	public void setListItems(String listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the listType
	 */
	public ListType getListType() {
		return listType;
	}

	/**
	 * @param listType the listType to set
	 */
	public void setListType(ListType listType) {
		this.listType = listType;
	}

}