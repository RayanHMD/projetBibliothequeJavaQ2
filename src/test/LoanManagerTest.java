package test;

import business.LoanManager;
import exception.BusinessException;
import mvc.model.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class LoanManagerTest {
    private LoanManager loanManager;
    private Loan loan;

    @BeforeEach
    void setUp() {
        loanManager = new LoanManager();
        loan = new Loan(
                1,
                createDate(2026,5,1),
                30,
                null,
                null,
                null
        );
    }

    @Test
    void calculateDueDate() {
        Date expectedDate = createDate(2026,5,31);
        assertEquals(expectedDate, loanManager.calculateDueDate(loan));
    }

    @Test
    void calculateDueDateNullLoanException() {
        assertThrows(BusinessException.class, () -> loanManager.calculateDueDate(null));
    }

    @Test
    void calculateDueDateInvalideDurationException() {
        Loan invalidLoan = new Loan(
                1,
                createDate(2026,5,1),
                0,
                null,
                null,
                null
        );
        assertThrows(BusinessException.class, () -> loanManager.calculateDueDate(invalidLoan));
    }

    @Test
    void calculateLateDays() {
        int expectedDays = 2;
        assertEquals(expectedDays, loanManager.calculateLateDays(loan, createDate(2026,6,2)));
    }

    @Test
    void calculateLateDaysNullReferenceDateException() {
        assertThrows(BusinessException.class, () -> loanManager.calculateLateDays(loan, null));
    }

    @Test
    void isLate() {
        assertTrue(loanManager.isLate(loan, createDate(2026,6,2)));
    }

    @Test
    void calculateLateFee() {
        double expectedFees = 1.0;
        assertEquals(expectedFees, loanManager.calculateLateFee(loan, createDate(2026, 6, 2)), 0.01);
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}