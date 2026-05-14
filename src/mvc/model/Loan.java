package mvc.model;

import java.util.Date;

public class Loan {
    private Integer id;
    private Date loanDate;
    private Integer maximumLoanDuration;
    private Date actualReturnDate;
    private Boolean hasExtended;
    private Reader borrower;
    private Copy copy;

    public Loan(Integer id, Date loanDate, Integer maximumLoanDuration, Date actualReturnDate, Boolean hasExtended, Reader borrower, Copy copy) {
        this.id = id;
        this.loanDate = loanDate;
        this.maximumLoanDuration = maximumLoanDuration;
        this.actualReturnDate = actualReturnDate;
        this.hasExtended = hasExtended;
        this.borrower = borrower;
        this.copy = copy;
    }
}
