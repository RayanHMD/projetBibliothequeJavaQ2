package mvc.view;

import mvc.controller.ReaderController;
import mvc.exception.DataAccessException;
import mvc.model.Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ReaderListPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private ReaderController readerController;

    public ReaderListPanel() {
        setLayout(new BorderLayout());
        readerController = new ReaderController();

        JLabel title = new JLabel("Liste des lecteurs", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);

        String[] columns = {
                "Numero lecteur",
                "Nom",
                "Prenom",
                "Genre",
                "Telephone",
                "Date inscription",
                "A paye",
                "Date naissance",
                "Email",
                "Adresse",
                "Localite",
                "Modifier",
                "Supprimer"
        };

        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        loadReaders();

        JButton modifyBtn = new JButton("Modifier");
        JButton deleteBtn = new JButton("Supprimer");

        table.getColumn("Modifier").setCellRenderer((table, value, isSelected, hasFocus, row, column) -> modifyBtn);
        table.getColumn("Supprimer").setCellRenderer((table, value, isSelected, hasFocus, row, column) -> deleteBtn);

        table.getColumn("Modifier").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton btn = new JButton("Modifier");

                btn.addActionListener(e -> {
                    fireEditingStopped();

                    Integer readerNumber = (Integer) table.getValueAt(row, 0);

                    Reader selectedReader = findReaderByNumber(readerNumber);

                    if (selectedReader == null) {
                        JOptionPane.showMessageDialog(ReaderListPanel.this, "Lecteur introuvable.");
                        return;
                    }

                    Container container = ReaderListPanel.this.getParent();
                    container.removeAll();
                    container.setLayout(new BorderLayout());
                    container.add(new RegistrationForm((MenuWindow) SwingUtilities.getWindowAncestor(ReaderListPanel.this), selectedReader), BorderLayout.CENTER);
                    container.revalidate();
                    container.repaint();
                });

                return btn;
            }

            @Override
            public Object getCellEditorValue() {
                return "";
            }
        });

        table.getColumn("Supprimer").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton btn = new JButton("Supprimer");

                btn.addActionListener(e -> {
                    fireEditingStopped();

                    Integer readerNumber = (Integer) table.getValueAt(row, 0);

                    int confirm = JOptionPane.showConfirmDialog(
                            ReaderListPanel.this,
                            "Supprimer le lecteur numero " + readerNumber + " ?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteReaderByNumber(readerNumber);
                    }
                });
                return btn;
            }

            @Override
            public Object getCellEditorValue() {
                return "";
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadReaders() {
        tableModel.setRowCount(0);

        try {
            ArrayList<Reader> readers = readerController.getAllReaders();

            for(Reader reader : readers) {
                tableModel.addRow(new Object[]{
                        reader.getReaderNumber(),
                        reader.getLastName(),
                        reader.getFirstName(),
                        reader.getGender(),
                        reader.getNumberPhone(),
                        reader.getRegistrationDate(),
                        reader.getHadPaidRegistration(),
                        reader.getBirthDate(),
                        reader.getEmail(),
                        reader.getStreetNumberAndName(),
                        reader.getLocation(),
                        "Modifier",
                        "Supprimer"
                });
            }
        } catch (DataAccessException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteReaderByNumber(Integer readerNumber) {
        try {
            ArrayList<Reader> readers = readerController.getAllReaders();
            Reader selectedReader = null;
            int index = 0;

            while(index < readers.size() && selectedReader == null) {
                Reader reader = readers.get(index);
                if (reader.getReaderNumber().equals(readerNumber)) {
                    selectedReader = reader;
                }
                index++;
            }

            if(selectedReader == null) {
                JOptionPane.showMessageDialog(this, "Lecteur introuvable.");
            }
            else {
                readerController.deleteReader(selectedReader);
                loadReaders();

                JOptionPane.showMessageDialog(this, "Lecteur supprime.");
            }
        } catch (DataAccessException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Reader findReaderByNumber(Integer readerNumber) {
        try {
            ArrayList<Reader> readers = readerController.getAllReaders();

            int index = 0;

            while(index < readers.size() && !(readers.get(index).getReaderNumber().equals(readerNumber))) {
                index++;
            }

            if(index < readers.size()) {
                return readers.get(index);
            }
        } catch(DataAccessException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}
