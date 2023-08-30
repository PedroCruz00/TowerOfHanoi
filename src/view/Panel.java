package view;

import java.awt.*;
import javax.swing.JPanel;

public class Panel extends JPanel {

    private int clicked;

    public Panel() {
        setBackground(Color.RED);
    }


    public int getClicked() {
        return this.clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
        repaint();
    }

    private static Color getColorOpacity(Color color, float opacity) {
        return Color.BLUE;
    }


    private void createBar(Graphics2D g2, String letra, int x, int grosor, int height) {
        g2.setColor(Color.BLACK);
        g2.fillRect(x - grosor / 2, 110, grosor, height - 200);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Tahoma", 0, 14));
        g2.drawString(letra, x - grosor / 2 + 10, 130);
    }

    private void border(Graphics2D g2, int height, int width, boolean active) {
        int[] coorX = {width - 60, width + 60, width + 210, width - 210};
        int[] coorY = {110, 110, height - 111, height - 111};
        int nPuntos = 4;
        g2.setColor(getColorOpacity(Color.decode("#64B5F6"), 0.4F));
        g2.fillPolygon(coorX, coorY, nPuntos);
        if (active) {
            g2.setColor(getColorOpacity(Color.decode("#2196F3"), 0.5F));
            g2.setStroke(new BasicStroke(5.0F));
            g2.drawPolygon(coorX, coorY, nPuntos);
        }
    }
}