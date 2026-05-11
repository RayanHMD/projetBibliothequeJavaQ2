package mvc.model;

import java.util.Date;

public class Loan {
    private int id;
    private Date LoanDate;
    private int maximumLoanDuration;
    private Date actualReturnDate;
    private boolean hasExtended;
    private User borrower;
    private Copy copy;

    public Loan(int id, Date loanDate, int maximumLoanDuration, Date actualReturnDate, boolean hasExtended, User borrower, Copy copy) {
        this.id = id;
        this.LoanDate = loanDate;
        this.maximumLoanDuration = maximumLoanDuration;
        this.actualReturnDate = actualReturnDate;
        this.hasExtended = hasExtended;
        this.borrower = borrower;
        this.copy = copy;
    }
}
