package mvc.view;

import mvc.controller.ReaderController;
import exception.DataAccessException;
import mvc.model.Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ReaderListPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private ReaderController readerController;
    private ArrayList<Reader> displayedReaders;
    private JButton backButton;
    private MenuWindow parent;

    public ReaderListPanel(MenuWindow parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        readerController = new ReaderController();
        displayedReaders = new ArrayList<>();

        JLabel title = new JLabel("Liste des lecteurs", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(20f));
        add(title, BorderLayout.NORTH);

        String[] columns = {
                "Numéro lecteur",
                "Nom",
                "Prénom",
                "Genre",
                "Téléphone",
                "Date d'inscription",
                "A payé(e)",
                "Date de naissance",
                "Email",
                "Adresse",
                "Localité",
                "Modifier",
                "Supprimer"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 11 || column == 12;
            }
        };
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

                    int modelRow = table.convertRowIndexToModel(row);
                    Reader selectedReader = displayedReaders.get(modelRow);

                    if (selectedReader == null) {
                        JOptionPane.showMessageDialog(ReaderListPanel.this, "Lecteur introuvable.");
                        return;
                    }

                    MenuWindow menuWindow = (MenuWindow) SwingUtilities.getWindowAncestor(ReaderListPanel.this);

                    if (menuWindow == null) {
                        JOptionPane.showMessageDialog(ReaderListPanel.this, "Fenêtre principale introuvable.");
                        return;
                    }

                    menuWindow.showPanel(new RegistrationForm(menuWindow, selectedReader));
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

                    int modelRow = table.convertRowIndexToModel(row);
                    Reader selectedReader = displayedReaders.get(modelRow);

                    if (selectedReader == null) {
                        JOptionPane.showMessageDialog(ReaderListPanel.this, "Lecteur introuvable.");
                        return;
                    }

                    Integer readerNumber = selectedReader.getReaderNumber();

                    int confirm = JOptionPane.showConfirmDialog(
                            ReaderListPanel.this,
                            "Supprimer le lecteur numéro " + readerNumber + " et ses données liées ?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteReader(selectedReader);
                    }
                });
                return btn;
            }

            @Override
            public Object getCellEditorValue() {
                return "";
            }
        });

        // button back menu
        backButton = new JButton("Retour à la page d'accueil");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            this.parent.setAccueil();
        });



        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadReaders() {
        tableModel.setRowCount(0);
        displayedReaders.clear();

        try {
            ArrayList<Reader> readers = readerController.getAllReaders();

            for(Reader reader : readers) {
                displayedReaders.add(reader);
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

    private void deleteReader(Reader selectedReader) {
        try {
            readerController.deleteReader(selectedReader);
            loadReaders();

            JOptionPane.showMessageDialog(this, "Lecteur supprimé.");
        } catch (DataAccessException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
