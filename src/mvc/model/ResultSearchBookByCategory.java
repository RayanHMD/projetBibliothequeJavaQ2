package mvc.model;

public class ResultSearchBookByCategory {
    private String isbn;
    private String titleBook;
    private String lastNameAuthor;
    private String firstNameAuthor;
    private String namePublisher;
    private String nameCategory;

    public ResultSearchBookByCategory(String isbn, String titleBook, String lastNameAuthor, String firstNameAuthor, String namePublisher, String nameCategory) {
        this.isbn = isbn;
        this.titleBook = titleBook;
        this.lastNameAuthor = lastNameAuthor;
        this.firstNameAuthor = firstNameAuthor;
        this.namePublisher = namePublisher;
        this.nameCategory = nameCategory;
        this.lastNameAuthor = lastNameAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String getLastNameAuthor() {
        return lastNameAuthor;
    }

    public String getFirstNameAuthor() {
        return firstNameAuthor;
    }

    public String getNamePublisher() {
        return namePublisher;
    }

    public String getNameCategory() {
        return nameCategory;
    }
}
