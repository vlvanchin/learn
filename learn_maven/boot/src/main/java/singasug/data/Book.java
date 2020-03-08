package singasug.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

//simple jpa entity mapping
@Entity
class Book {

	@Id
	@GeneratedValue
	long id;

//	private String title;
//	private int year;
	
	// modify class Book
	@Size(min = 1) // <- add this
	private String title;
	@Min(1900) // <- add this
	private int year;

	protected Book() {
	} // mandates by JPA

	public Book(String title, int year) {
		this.title = title;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Book{id=" + id + ", title='" + title + '\'' + ", year=" + year + '}';
	}
}