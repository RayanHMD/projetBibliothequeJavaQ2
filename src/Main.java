import dao.SingletonConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = SingletonConnection.getInstance();
            System.out.println("Connexion réussie !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}