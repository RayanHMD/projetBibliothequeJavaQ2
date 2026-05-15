package mvc.view;

import mvc.controller.BookListController;
import mvc.exception.DataAccessException;
import mvc.model.ResultBookList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookListPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryBox;
    private BookListController bookListController;

    public BookListPanel() {
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
        String[] columns = {"Code ISBN", "Titre du livre","Nom de l'auteur", "Prénom de l'auteur", "Maison d'édition", "Catégorie"};

        tableModel = new DefaultTableModel(columns, 0);


        bookListController = new BookListController();
        // add books in db
        try{
            ArrayList<ResultBookList> books = bookListController.getBookList();

            for (ResultBookList book : books) {
                tableModel.addRow(new Object[]{
                        book.getIsbn(),
                        book.getTitleBook(),
                        book.getLastNameAuthor(),
                        book.getFirstNameAuthor(),
                        book.getNamePublisher(),
                        book.getNameCategory()
                });
            }


        }catch (DataAccessException e){
            JOptionPane.showMessageDialog(this, e);
        }

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
