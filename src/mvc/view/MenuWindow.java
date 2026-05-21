package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu application, reader, book, loan;
    private JMenuItem quitter, inscription,readerList, bookList, loanList, loanReaderIsbn;
    private Container frameContainer;

    public MenuWindow() {
        super("First Window");
        setBounds(100, 100, 600, 500);
        setLocationRelativeTo(null);
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.add(new AnimatedLabel("Bibliothèque HÉNALLUX"), BorderLayout.CENTER);

        //region Barre avec les menus
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //endregion

        //region Application
        application = new JMenu("Application");
        application.setMnemonic('A');
        menuBar.add(application);
        quitter = new JMenuItem("Quitter");
        application.add(quitter);
        ExitListener exitListener = new ExitListener();
        quitter.addActionListener(exitListener);
        //endregion

        //region Reader
        reader = new JMenu("Utilisateurs");
        reader.setMnemonic('U');
        menuBar.add(reader);
        inscription = new JMenuItem("Inscription");
        reader.add(inscription);
        inscription.addActionListener(e -> showPanel(new RegistrationForm(this)));

        readerList = new JMenuItem("Lister les lecteurs");
        reader.add(readerList);
        readerList.addActionListener(e -> showReaderList());

        //endregion

        //region Book
        book = new JMenu("Livre");
        book.setMnemonic('L');
        menuBar.add(book);
        bookList = new JMenuItem("Liste livres");
        book.add(bookList);
        bookList.addActionListener(e -> showPanel(new BookListPanel(this)));
        //endregion

        //region loan
        loan = new JMenu("Emprunts");
        loan.setMnemonic('E');
        menuBar.add(loan);

        // new loan
        loanList = new JMenuItem("Lister les emprunts");
        loan.add(loanList);
        loanList.addActionListener(e -> showPanel(new LoanPanel(this)));
        //endregion

        //loan by isbn
        loanReaderIsbn = new JMenuItem("Emprunt par lecteur");
        loan.add(loanReaderIsbn);
        loanReaderIsbn.addActionListener(e -> showPanel(new LoanBookByReaderPanel(this)));
        //region Ferme toute la fenêtre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //endregion

        setVisible(true);
    }

    public void setAccueil() {
        Container c = getContentPane();
        c.removeAll();
        c.add(new AnimatedLabel("Bibliothèque HÉNALLUX"), BorderLayout.CENTER);
        c.revalidate();
        c.repaint();
    }

    public void showPanel(JPanel panel) {
        Container c = getContentPane();
        c.removeAll();
        c.setLayout(new BorderLayout());
        c.add(panel, BorderLayout.CENTER);
        c.revalidate();
        c.repaint();
    }

    public void showReaderList() {
        showPanel(new ReaderListPanel(this));
    }
}
