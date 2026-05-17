package mvc.view;

import javax.swing.*;
import java.awt.*;

public class AnimatedLabel extends JPanel implements Runnable{
    private String label;
    private Float color = 0f;
    private Integer offset = 0;
    private Integer direction = 1;
    private Thread thread;
    private Boolean running;

    public AnimatedLabel(String label) {
        this.label = label;
        setOpaque(false);
        running = true;

        //thread start
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run(){
        while(running){

            //change color
            color += 0.05f;
            if(color > 1){
                color = 0f;
            }

            //move text
            offset += direction * 2;
            if(offset > 80 || offset < -80){
                direction *= -1;
            }

            repaint();

            try{

                Thread.sleep(50);

            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                running = false;
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Applique la couleur arc-en-ciel
        g2d.setColor(Color.getHSBColor(color, 1f, 0.9f));

        g2d.setFont(new Font("Arial", Font.BOLD, 22));

        // Calcule la position centrée + décalage pour le mouvement
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(label)) / 2 + offset;
        int y = getHeight() / 2 + fm.getAscent() / 2 - 4;

        g2d.drawString(label, x, y);
    }
}
