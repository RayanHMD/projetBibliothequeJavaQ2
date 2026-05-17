package mvc.model.recherches;

import java.util.Date;

public class ResultSearchLoansBetweenDates {


    private Date loanDate;
    private Integer maximumLoanDuration;
    private Date actualReturnDate;
    private String readerLastName;
    private String readerFirstName;
    private String email;
    private String isbn;
    private String title;
    private String categoryLabel;

    public ResultSearchLoansBetweenDates(Date loanDate, Integer maximumLoanDuration, Date actualReturnDate, String readerLastName, String readerFirstName, String email, String isbn, String title, String categoryLabel) {
        this.loanDate = loanDate;
        this.maximumLoanDuration = maximumLoanDuration;
        this.actualReturnDate = actualReturnDate;
        this.readerLastName = readerLastName;
        this.readerFirstName = readerFirstName;
        this.email = email;
        this.isbn = isbn;
        this.title = title;
        this.categoryLabel = categoryLabel;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Integer getMaximumLoanDuration() {
        return maximumLoanDuration;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public String getReaderFirstName() {
        return readerFirstName;
    }

    public String getReaderLastName() {
        return readerLastName;
    }

    public String getEmail() {
        return email;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }
}
