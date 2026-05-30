package mvc.model.recherches;

import java.util.Date;

public class ResultLoanSearchByBookInReader {
    private String bookTitle;
    private Date loanDate;
    private Integer maximumLoanDuration;
    private Date actualReturnDate;
    private String readerFirstName;
    private String readerLastName;
    private String readerNumberPhone;

    public ResultLoanSearchByBookInReader(String bookTitle, Date loanDate, Integer maximumLoanDuration, Date actualReturnDate, String readerFirstName, String readerLastName, String readerNumberPhone) {
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
        this.maximumLoanDuration = maximumLoanDuration;
        this.actualReturnDate = actualReturnDate;
        this.readerFirstName = readerFirstName;
        this.readerLastName = readerLastName;
        this.readerNumberPhone = readerNumberPhone;
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

    public String getReaderLastName() {
        return readerLastName;
    }

    public String getReaderNumberPhone() {
        return readerNumberPhone;
    }
}
