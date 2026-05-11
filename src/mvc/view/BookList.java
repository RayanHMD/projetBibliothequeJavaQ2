package mvc.view;

import javax.swing.*;
import java.awt.*;

public class BookList extends JPanel {
    private JList<String> bookList;
    private DefaultListModel<String> listModel;

    public BookList() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Liste des livres", SwingConstants.CENTER);

        listModel = new DefaultListModel<>();

        listModel.addElement("Harry Potter");
        listModel.addElement("Le Seigneur des Anneaux");
        listModel.addElement("1984");

        bookList = new JList<>(listModel);

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(bookList), BorderLayout.CENTER);
    }
}
