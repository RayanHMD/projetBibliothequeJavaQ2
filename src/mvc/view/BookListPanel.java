package mvc.view;

import mvc.controller.BookListController;
import mvc.controller.CategoryController;
import mvc.controller.SearchBookByCategoryController;
import mvc.exception.DataAccessException;
import mvc.model.Category;
import mvc.model.recherches.ResultBookList;
import mvc.model.recherches.ResultSearchBookByCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookListPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> categoryBox;
    private BookListController bookListController;
    private CategoryController categoryController;
    private SearchBookByCategoryController searchBookByCategoryController;

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
        categoryController = new CategoryController();
        categoryBox.addItem("Toutes les catégories");

        try{
            ArrayList<Category> categories = categoryController.getAllCategories();

            for(Category category : categories){
                categoryBox.addItem(category.getLabel());
            }
        }catch(DataAccessException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        panelCategory.add(new JLabel("Catégorie : "));
        panelCategory.add(categoryBox);

        header.add(title);
        header.add(panelCategory);
        add(header, BorderLayout.NORTH);

        // table
        String[] columns = {"Code ISBN", "Titre du livre","Nom de l'auteur", "Prénom de l'auteur", "Maison d'édition", "Catégorie"};

        tableModel = new DefaultTableModel(columns, 0);

        bookListController = new BookListController();

        try {
            ArrayList<ResultBookList> booksInitial = bookListController.getBookList();
            for (ResultBookList book : booksInitial) {
                addBookRow(book);
            }
        } catch (DataAccessException e) {
            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
        }

        searchBookByCategoryController = new SearchBookByCategoryController();
        categoryBox.addActionListener(e -> {
           tableModel.setRowCount(0);
           try {
               if ("Toutes les catégories".equals(categoryBox.getSelectedItem())) {
                ArrayList<ResultBookList> books = bookListController.getBookList();
                for(ResultBookList book : books){
                    addBookRow(book);
                }
               }
               else{
                   ArrayList<ResultSearchBookByCategory> booksResult = searchBookByCategoryController.getBooksByCategory(categoryBox.getSelectedItem().toString());
                   for(ResultSearchBookByCategory book : booksResult){
                       addBookRow(book);
                   }
               }

           }catch(DataAccessException exception){
               JOptionPane.showMessageDialog(this, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
           }
        });



        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void addBookRow(ResultBookList book) {
        tableModel.addRow(new Object[]{
                book.getIsbn(),
                book.getTitleBook(),
                book.getLastNameAuthor(),
                book.getFirstNameAuthor(),
                book.getNamePublisher(),
                book.getNameCategory()
        });
    }

    private void addBookRow(ResultSearchBookByCategory book) {
        tableModel.addRow(new Object[]{
                book.getIsbn(),
                book.getTitleBook(),
                book.getLastNameAuthor(),
                book.getFirstNameAuthor(),
                book.getNamePublisher(),
                book.getNameCategory()
        });
    }
}
