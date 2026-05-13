package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu application, user, book, infos;
    private JMenuItem quitter, inscription, bookList, iesn , aide;
    private BookList bookListPanel;
    private JLabel acceuilLabel;
    private Container frameContainer;
    //private IesnInformation panelIesnInformation;
    //private RegistrationForm formInscription;

    public MenuWindow() {
        super("First Window");
        setBounds(100, 100, 600, 500);
        setLocationRelativeTo(null);
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        acceuilLabel = new JLabel("Bienvenue dans l'application", SwingConstants.CENTER);
        frameContainer.add(acceuilLabel, BorderLayout.CENTER);

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

        //region user
        user = new JMenu("Utilisateur");
        user.setMnemonic('U');
        menuBar.add(user);
        inscription = new JMenuItem("Inscription");
        user.add(inscription);
        inscription.addActionListener(e -> {
            //formInscription = new RegistrationForm(this);
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            //frameContainer.add(formInscription, BorderLayout.CENTER);
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
            bookListPanel = new BookList();
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            frameContainer.add(bookListPanel, BorderLayout.CENTER);
            frameContainer.revalidate();
            frameContainer.repaint();
        });
        //endregion

        //region Infos
        infos = new JMenu("Infos");
        infos.setMnemonic('I');
        menuBar.add(infos);
        //region Sous menu IESN
        iesn = new JMenuItem("IESN");
        infos.add(iesn);

        // Ouvre le sous menu IESN
        iesn.addActionListener(e -> {
            //panelIesnInformation = new IesnInformation();
            frameContainer = this.getContentPane();
            frameContainer.removeAll();
            frameContainer.setLayout(new BorderLayout());
            //frameContainer.add(panelIesnInformation, BorderLayout.CENTER);
            frameContainer.revalidate();
            frameContainer.repaint();
        });
        //endregion

        //region Sous menu Aide
        aide = new JMenuItem("Aide");
        infos.add(aide);
        aide.addActionListener(e -> {
            //new AideWindow().setVisible(true);
        });
        //endregion
        //endregion

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
        c.add(new JLabel("Bienvenue dans l'application", SwingConstants.CENTER), BorderLayout.CENTER);
        c.revalidate();
        c.repaint();
    }
}
