package mvc.view;

import exception.DataAccessException;
import mvc.controller.LoanSearchByBookInReaderController;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class LoanBookByReaderPanel extends JPanel {
   private  JTable table;
   private JTextField textFieldReaderNumber;
   private LoanSearchByBookInReaderController controller;
   private DefaultTableModel tableModel;
   private Integer readerNumber;

    public LoanBookByReaderPanel() {
        controller = new LoanSearchByBookInReaderController();
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        //title
        JLabel title = new JLabel("Liste des emprunts pour un lecteur", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //panel insert readerNumber
        JPanel panelReader = new JPanel();
        panelReader.add(new JLabel("Entrer le numéro du lecteur : "));
        panelReader.add(this.textFieldReaderNumber = new JTextField(20));

        //panel button
        JPanel panelButton = new JPanel();
        JButton validateButton = new JButton("Rechercher");
        panelButton.add(validateButton);

        JButton resetButton = new JButton("Réinitialiser");
        panelButton.add(resetButton);


        header.add(title);
        header.add(panelReader);
        header.add(panelButton);

        add(header, BorderLayout.NORTH);

        String[] columns = {"Titre", "Date d'emprunt", "Durée max (jours)", "Date de retour", "Prénom", "Nom"};
        tableModel = new DefaultTableModel(columns, 0);

        validateButton.addActionListener(e -> {
            searchLoans();
        });

        resetButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            textFieldReaderNumber.setText("");
        });


        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void searchLoans() {
        String input = textFieldReaderNumber.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de lecteur.");

        }

        try{
            readerNumber = Integer.parseInt(input);
            ArrayList<ResultLoanSearchByBookInReader> loans = controller.getLoansByReader(readerNumber);

            for (ResultLoanSearchByBookInReader loan : loans) {
                tableModel.addRow(new Object[]{
                        loan.getBookTitle(),
                        loan.getLoanDate(),
                        loan.getMaximumLoanDuration(),
                        loan.getActualReturnDate(),
                        loan.getReaderFirstName(),
                        loan.getReaderLastName()

                });
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Le numéro du lecteur est un entier");
        }catch(DataAccessException exec){
            JOptionPane.showMessageDialog(this, exec.getMessage());
        }
    }
}
