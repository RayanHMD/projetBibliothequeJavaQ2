package mvc.view;

import exception.DataAccessException;
import mvc.controller.LoanSearchByBookInReaderController;
import mvc.controller.ReaderController;
import mvc.model.Reader;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class LoanBookByReaderPanel extends JPanel {
   private  JTable table;
   private JTextField filterField;
   private JComboBox<Reader> readerComboBox;
   private ArrayList<Reader> readers;
   private ReaderController readerController;
   private LoanSearchByBookInReaderController controller;
   private DefaultTableModel tableModel;

    public LoanBookByReaderPanel() {
        controller = new LoanSearchByBookInReaderController();
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        //title
        JLabel title = new JLabel("Liste des emprunts pour un lecteur", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //panel search reader
        readerController = new ReaderController();

        JPanel panelReader = new JPanel();
        panelReader.add(new JLabel("Filtrer : "));

        filterField = new JTextField(15);
        panelReader.add(filterField);

        panelReader.add(new JLabel("Choisir un lecteur : "));

        readerComboBox = new JComboBox<>();
        panelReader.add(readerComboBox);

        readers = new ArrayList<>();

        try {
            readers = readerController.getAllReaders();
            refreshReaderComboBox("");
        } catch (DataAccessException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        filterField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                refreshReaderComboBox(filterField.getText());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                refreshReaderComboBox(filterField.getText());
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                refreshReaderComboBox(filterField.getText());
            }
        });

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
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        validateButton.addActionListener(e -> {
            searchLoans();
        });

        resetButton.addActionListener(e -> {
            filterField.setText("");
            readerComboBox.setSelectedIndex(-1);
            tableModel.setRowCount(0);
        });


        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void searchLoans() {
        Reader selectedReader = (Reader) readerComboBox.getSelectedItem();

        if (selectedReader == null) {
            JOptionPane.showMessageDialog(this, "Veuillez choisir un lecteur.");

        }
        else {
            try {
                tableModel.setRowCount(0);

                ArrayList<ResultLoanSearchByBookInReader> loans = controller.getLoansByReader(selectedReader.getReaderNumber());

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
                if (loans.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aucun emprunt trouvé pour ce lecteur.");
                }
            } catch(DataAccessException exec){
                JOptionPane.showMessageDialog(this, exec.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshReaderComboBox(String filter) {
        readerComboBox.removeAllItems();

        String filterLowerCase = filter.toLowerCase();

        for(Reader reader : readers) {
            String readerText = reader.toString().toLowerCase();

            if(readerText.contains(filterLowerCase)) {
                readerComboBox.addItem(reader);
            }
        }
        if(readerComboBox.getItemCount() == 1) {
            readerComboBox.setSelectedIndex(0);
        }
        else {
            readerComboBox.setSelectedIndex(-1);
        }

    }
}
