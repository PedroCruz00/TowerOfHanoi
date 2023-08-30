package view;

import java.awt.*;
import javax.swing.JPanel;

public class Panel extends JPanel {

    private int clicked;

    public Panel() {
        setBackground(Color.RED);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        int width = getWidth();
        int height = getHeight();
        int thickness = 30;
        g2.setColor(Color.decode("#BDBDBD"));
        g2.fillRect(0, 0, width, height);
        border(g2, height, width / 6, (getClicked() == 1));
        border(g2, height, width / 6 * 3, (getClicked() == 2));
        border(g2, height, width / 6 * 5, (getClicked() == 3));
        g2.setColor(Color.BLACK);
        g2.fillRect(width / 6 - thickness / 2, height - 111, width / 6 * 4 + thickness, thickness);
        createBar(g2, "A", width / 6, thickness, height);
        createBar(g2, "B", width / 6 * 3, thickness, height);
        createBar(g2, "C", width / 6 * 5, thickness, height);
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