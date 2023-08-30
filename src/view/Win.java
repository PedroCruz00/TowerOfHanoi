package view;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

public class Win extends JDialog {

    private JButton jButton1;

    private JLabel lblDescripcion;

    private JLabel lblImagen;

    public Win(Frame parent, boolean modal, int movimientosMinimo, int movimientosRealizados) {
        super(parent, modal);
        initComponents();
        this.lblImagen.setIcon(resizeIcon("win.jpg", this.lblImagen.getWidth(), this.lblImagen.getHeight()));
        setLocationRelativeTo(parent);
        this.lblDescripcion.setText(mensaje(movimientosMinimo, movimientosRealizados));
    }

    private String mensaje(int movimientosMinimo, int movimientosRealizados) {
        if (movimientosMinimo == movimientosRealizados) {
            return "Felicidades, movimientos perfecto " + movimientosMinimo + " de " + movimientosRealizados;
        }
        float nivel = movimientosMinimo / movimientosRealizados * 100.0F;
        if (nivel >= 90.0F) {
            return "Felicidades, te falta mejorar un poquito " + movimientosMinimo + " de " + movimientosRealizados;
        }
        if (nivel >= 70.0F && nivel < 90.0F) {
            return "Felicidades, pero puedes mejorar " + movimientosMinimo + " de " + movimientosRealizados;
        }
        if (nivel >= 50.0F && nivel < 70.0F) {
            return "Puedes hacerlo mejor " + movimientosMinimo + " de " + movimientosRealizados;
        }
        return "!!GANASTE!! " + movimientosMinimo + " de " + movimientosRealizados;
    }

    private Icon    resizeIcon(String path, int resizedWidth, int resizedHeight) {
        Image img = (new ImageIcon(getClass().getResource(path))).getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, 4);
        return new ImageIcon(resizedImage);
    }

    private void initComponents() {
        this.lblImagen = new JLabel();
        this.jButton1 = new JButton();
        this.lblDescripcion = new JLabel();
        setDefaultCloseOperation(2);
        this.jButton1.setText("Cerrar");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Win.this.jButton1ActionPerformed(evt);
            }
        });
        this.lblDescripcion.setHorizontalAlignment(0);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.lblImagen, -1, -1, 32767)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(this.jButton1, -1, 380, 32767)
                                .addComponent(this.lblDescripcion, -1, -1, 32767))
                        .addContainerGap()));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(this.lblImagen, -2, 331, -2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.lblDescripcion, -1, 40, 32767)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.jButton1, -2, 30, -2)
                        .addContainerGap()));
        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        dispose();
    }
}