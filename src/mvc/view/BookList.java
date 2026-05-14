package mvc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookList extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryBox;


    public BookList() {
        setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        //title
        JLabel title = new JLabel("Liste des livres", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        //panel category
        JPanel panelCategory = new JPanel(new FlowLayout(FlowLayout.CENTER));
        categoryBox = new JComboBox<>();

        // add category in db
        categoryBox.addItem("Toutes les catégories");
        categoryBox.addItem("Fantasy");
        categoryBox.addItem("Dystopie");

        panelCategory.add(new Label("Catégorie : "));
        panelCategory.add(categoryBox);

        header.add(title);
        header.add(panelCategory);
        add(header, BorderLayout.NORTH);

        // table
        String[] columns = {"Code ISBN", "Titre du livre", "Maison d'édition", "Catégorie"};

        tableModel = new DefaultTableModel(columns, 0);


        // add books in db
        tableModel.addRow(new Object[]{"978-123", "Harry Potter", "Gallimard", "Fantasy"});
        tableModel.addRow(new Object[]{"978-456", "1984", "Seuil", "Dystopie"});
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
