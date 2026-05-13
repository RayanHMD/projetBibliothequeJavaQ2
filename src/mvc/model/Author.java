package mvc.model;

public class Author {
    private Integer idAuthor;
    private String lastName;
    private String firstName;

    public Author(Integer idAuthor, String lastName, String firstName) {
        this.idAuthor = idAuthor;
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
