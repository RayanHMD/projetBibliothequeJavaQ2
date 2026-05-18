package business;

import exception.BusinessException;
import mvc.model.Loan;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoanManager {
    private static final double DAILY_LATE_FEE = 0.5;

    private void validateLoanForLateCalculation(Loan loan) throws BusinessException {
        if (loan == null) {
            throw new BusinessException("L'emprunt ne peut pas être vide.");
        }

        if (loan.getLoanDate() == null) {
            throw new BusinessException("La date d'emprunt est obligatoire.");
        }

        if (loan.getMaximumLoanDuration() == null || loan.getMaximumLoanDuration() <= 0) {
            throw new BusinessException("La durée maximale d'emprunt doit être positive.");
        }
    }

    public Date calculateDueDate(Loan loan) throws BusinessException {
        validateLoanForLateCalculation(loan);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loan.getLoanDate());
        calendar.add(Calendar.DAY_OF_MONTH, loan.getMaximumLoanDuration());

        return calendar.getTime();
    }


    public int calculateLateDays(Loan loan, Date referenceDate) throws BusinessException {
        int lateDays = 0;
        validateLoanForLateCalculation(loan);

        if (referenceDate == null) {
            throw new BusinessException("La date de référence est obligatoire.");
        }

        Date actualReturnDate;

        if (loan.getActualReturnDate() == null) {
            actualReturnDate = referenceDate;
        } else {
            actualReturnDate = loan.getActualReturnDate();
        }

        Date dueDate = calculateDueDate(loan);

        if (actualReturnDate.after(dueDate)) {
            long difference = actualReturnDate.getTime() - dueDate.getTime();
            lateDays = (int) TimeUnit.MILLISECONDS.toDays(difference);
        }

        return lateDays;
    }

    public boolean isLate(Loan loan, Date referenceDate) throws BusinessException {
        return calculateLateDays(loan, referenceDate) > 0;
    }

    public double calculateLateFee(Loan loan, Date referenceDate) throws BusinessException {
        return calculateLateDays(loan, referenceDate) * DAILY_LATE_FEE;
    }



}
