package mvc.model.recherches;

public class ResultSearchBookByCategory {
    private String isbn;
    private String titleBook;
    private String authors;
    private String namePublisher;
    private String nameCategory;

    public ResultSearchBookByCategory(String isbn, String titleBook, String authors, String namePublisher, String nameCategory) {
        this.isbn = isbn;
        this.titleBook = titleBook;
        this.authors = authors;
        this.namePublisher = namePublisher;
        this.nameCategory = nameCategory;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String getAuthors() {
        return authors;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public String getNameCategory() {
        return nameCategory;
    }
}
