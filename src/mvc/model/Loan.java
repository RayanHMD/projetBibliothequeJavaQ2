package mvc.model;

import java.util.Date;

public class Loan {
    private Integer id;
    private Date loanDate;
    private Integer maximumLoanDuration;
    private Date actualReturnDate;
    private Reader borrower;
    private Copy copy;

    public Loan(Integer id, Date loanDate, Integer maximumLoanDuration, Date actualReturnDate, Reader borrower, Copy copy) {
        this.id = id;
        this.loanDate = loanDate;
        this.maximumLoanDuration = maximumLoanDuration;
        this.actualReturnDate = actualReturnDate;
        this.borrower = borrower;
        this.copy = copy;
    }

    public Integer getId() {
        return id;
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

    public Reader getBorrower() {
        return borrower;
    }

    public Copy getCopy() {
        return copy;
    }
}
