package mvc.model;

public class Book {
    private String isbn;
    private String title;
    private Publisher publisher;
    private Category category;

    public Book(String isbn, String title, Publisher publisher, Category category) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
    }

    public Book(String isbn, String title, Category category) {
        this(isbn, title, null, category);
    }
}
