package mvc.model;

import java.util.Date;

public class ResultLoanSearchByBookInReader {
    private String bookTitle;
    private Date loanDate;
    private Integer maximumLoanDuration;
    private Date actualReturnDate;
    private Boolean hasExtended;
    private String readerFirstName;
    private String readerLastName;

    public ResultLoanSearchByBookInReader(String bookTitle, Date loanDate, Integer maximumLoanDuration, Date actualReturnDate, Boolean hasExtended, String readerFirstName, String readerLastName) {
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
        this.maximumLoanDuration = maximumLoanDuration;
        this.actualReturnDate = actualReturnDate;
        this.hasExtended = hasExtended;
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
    }

    public String getBookTitle() {
        return bookTitle;
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

    public Boolean getHasExtended() {
        return hasExtended;
    }

    public String getReaderLastName() {
        return readerLastName;
    }
}
