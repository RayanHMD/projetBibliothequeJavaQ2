package mvc.view;

import mvc.controller.SearchLoansBetweenDatesController;
import exception.DataAccessException;
import mvc.model.recherches.ResultSearchLoansBetweenDates;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.sql.Date;

public class LoanPanel extends JPanel {
    private JTable table;
    private JSpinner startDate, endDate;
    private DefaultTableModel tableModel;
    private SearchLoansBetweenDatesController controller;

    public LoanPanel() {
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        controller = new SearchLoansBetweenDatesController();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        //title
        JLabel title = new JLabel("Liste des emprunts sur une période donnée", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //panel dateStart
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startDatePanel.add(new JLabel("Date de début : ", SwingConstants.LEFT));
        SpinnerDateModel startModel = new SpinnerDateModel();
        startDate = new JSpinner(startModel);
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDate, "dd/MM/yyyy");
        startDate.setEditor(startDateEditor);
        startDatePanel.add(startDate);


        //panel dateEnd
        JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        endDatePanel.add(new JLabel("Date de fin : ", SwingConstants.LEFT));
        SpinnerDateModel endModel = new SpinnerDateModel();
        endDate = new JSpinner(endModel);
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDate, "dd/MM/yyyy");
        endDate.setEditor(endDateEditor);
        endDatePanel.add(endDate);

        // button search
        JButton searchButton = new JButton("Rechercher");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.addActionListener(e -> {
            try{
                Date start = new Date(((java.util.Date) startDate.getValue()).getTime());
                Date end = new Date(((java.util.Date) endDate.getValue()).getTime());

                if(start.after(end)){
                    JOptionPane.showMessageDialog(
                            this,
                            "La date de début doit être avant la date de fin.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                else {
                    loadLoans(controller.getAllLoansBetweenDates(start,end));
                }

            }catch(DataAccessException exception){
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

            }
        });

        header.add(title);
        header.add(startDatePanel);
        header.add(endDatePanel);
        header.add(searchButton);
        add(header, BorderLayout.NORTH);


        //table
        String[] columns = {"Date d'emprunt", "Durée max emprunt",
                            "Date fin effective", "Prolongé", "Nom emprunteur", "Prénom emprunteur",
                            "Mail emprunteur", "Code ISBN", "Titre du livre", "Catégorie"};

        tableModel = new DefaultTableModel(columns, 0);


        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    public void loadLoans(ArrayList<ResultSearchLoansBetweenDates> loans) {
        tableModel.setRowCount(0);
        for (ResultSearchLoansBetweenDates loan : loans) {
            tableModel.addRow(new Object[]{
                    loan.getLoanDate(),
                    loan.getMaximumLoanDuration(),
                    loan.getActualReturnDate(),
                    loan.getHasExtended(),
                    loan.getReaderLastName(),
                    loan.getReaderFirstName(),
                    loan.getEmail(),
                    loan.getIsbn(),
                    loan.getTitle(),
                    loan.getCategoryLabel()
            });
        }
    }


}
