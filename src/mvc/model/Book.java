package mvc.model;

public class Book {
    private Integer isbn;
    private String title;
    private Publisher publisher;
    private Category category;

    public Book(Integer isbn, String title, Publisher publisher, Category category) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
    }

    public Book(Integer isbn, String title, Category category) {
        this(isbn, title, null, category);
    }
}
