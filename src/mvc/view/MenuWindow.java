package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu application, reader, book, loan, infos;
    private JMenuItem quitter, inscription,readerList,readerUpdate, readerDelete, bookList, loanNew, aide;
    private BookListPanel bookListPanel;
    private ReaderListPanel readerListPanel;
    private LoanPanel loanPanel;
    private Container frameContainer;
    private RegistrationForm formInscription;

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

        //region reader
        reader = new JMenu("Utilisateur");
        reader.setMnemonic('U');
        menuBar.add(reader);
        inscription = new JMenuItem("Inscription");
        reader.add(inscription);
        inscription.addActionListener(e -> {
            formInscription = new RegistrationForm(this);
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.add(formInscription, BorderLayout.CENTER);
            frameContainer.revalidate();
            frameContainer.repaint();
        });

        readerList = new JMenuItem("Lister les membres");
        reader.add(readerList);
        readerList.addActionListener(e -> {
            readerListPanel = new ReaderListPanel();
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.add(readerListPanel, BorderLayout.CENTER);
            frameContainer.revalidate();
            frameContainer.repaint();
        });

        readerUpdate = new JMenuItem("Modifier un membre");
        reader.add(readerUpdate);
        readerUpdate.addActionListener(e -> {
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.revalidate();
            frameContainer.repaint();
        });

        readerDelete = new JMenuItem("Supprimer un membre");
        reader.add(readerDelete);
        readerDelete.addActionListener(e -> {
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.revalidate();
            frameContainer.repaint();
        });
        //endregion

        //region Book
        book = new JMenu("Livre");
        book.setMnemonic('L');
        menuBar.add(book);
        bookList = new JMenuItem("Liste livres");
        book.add(bookList);
        bookList.addActionListener(e -> {
            bookListPanel = new BookListPanel();
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.add(bookListPanel, BorderLayout.CENTER);
            frameContainer.revalidate();
            frameContainer.repaint();
        });
        //endregion



        //region loan
        loan = new JMenu("Emprunts");
        loan.setMnemonic('E');
        menuBar.add(loan);

        // new loan
        loanNew = new JMenuItem("Lister les emprunts");
        loan.add(loanNew);
        loanNew.addActionListener(e -> {
            loanPanel = new LoanPanel();
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.add(loanPanel, BorderLayout.CENTER);
            frameContainer.revalidate();
            frameContainer.repaint();
        });


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
}
