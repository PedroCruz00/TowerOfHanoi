package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class View extends JFrame {
    private int diskNumber;
    private ArrayList<JLabel> listDiskOrrigin;
    private ArrayList<JLabel> listDiskAux;
    private ArrayList<JLabel> targerListDisk;
    private Panel panel;
    private JLabel title1;
    private JLabel title2;
    private int moveDone;
    private JLabel selectedDisk;
    private boolean diskUp;
    private int positionDiskCurrent;
    private boolean win;
    private int heightDisk;
    private int widthDisk;

    public View() throws HeadlessException {
    }

    public View(int height, int width) {
        heightDisk = height;
        widthDisk = width;
        diskNumber = 0;
        listDiskOrrigin = new ArrayList<>();
        listDiskAux = new ArrayList<>();
        selectedDisk = null;
        diskUp = false;
        win = false;
        targerListDisk = new ArrayList<>();
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        setSize(d.width, d.height);
        readDiskNumber();
        this.panel = new Panel();
        this.panel.setBounds(0, 0,d.width, d.height);
        this.panel.setLayout((LayoutManager)null);
        add(this.panel);
        this.title1 = new JLabel("mejor movimiento : " + numberMove());
        this.title1.setBounds(2, 2, getWidth() / 4, 25);
        this.panel.add(this.title1);
        this.title2 = new JLabel("movimiento : 0");
        this.title2.setBounds(getWidth() / 4, 2, getWidth() / 4, 25);
        this.panel.add(this.title2);
        JButton btnReset = new JButton("Reiniciar");
        btnReset.setBackground(new Color(160, 191, 224));
        btnReset.setBounds(getWidth() / 4 * 2, 2, 200, 25);
        btnReset.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                View.this.listDiskOrrigin.stream().forEach(label -> View.this.panel.remove(label));
                View.this.listDiskAux.stream().forEach(label -> View.this.panel.remove(label));
                View.this.targerListDisk.stream().forEach(label -> View.this.panel.remove(label));
                if (View.this.selectedDisk != null)
                    View.this.panel.remove(View.this.selectedDisk);
                View.this.panel.repaint();
                View.this.panel.revalidate();
                View.this.listDiskOrrigin = new ArrayList<>();
                View.this.listDiskAux = new ArrayList<>();
                View.this.targerListDisk = new ArrayList<>();
                View.this.readDiskNumber();
                View.this.title1.setText("mejor movimiento : " + View.this.numberMove());
                View.this.title2.setText("movimiento : 0");
                View.this.win = false;
                View.this.selectedDisk = null;
                View.this.moveDone = 0;
                View.this.diskUp = false;
                View.this.createDisc();
                View.this.panel.repaint();
            }
        });
        this.panel.add(btnReset);
        eventClick();
        createDisc();
    }

    private void eventClick() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (!View.this.win) {
                    int positionX = View.this.getWidth() / 3;
                    if (evt.getX() <= positionX)
                        View.this.positionDiskCurrent = 1;
                    if (evt.getX() > positionX && evt.getX() <= positionX * 2)
                        View.this.positionDiskCurrent = 2;
                    if (evt.getX() > positionX * 2 && evt.getX() <= positionX * 3)
                        View.this.positionDiskCurrent = 3;
                    View.this.panel.setClicked(View.this.positionDiskCurrent);
                    View.this.moveDisk();
                }
            }
        });
    }

    private void readDiskNumber() {
        InitMessage initMessage= new InitMessage();
        this.diskNumber = initMessage.readDiskNumber(this,diskNumber);

    }

    private void createDisc() {
        int y = getHeight() - 136;
        int width = this.widthDisk;
        int height = this.heightDisk;
        int x = getWidth() / 6 - width / 2;
        for (int i = 0; i < this.diskNumber; i++) {
            Color color = new Color((new Random()).nextInt(256), (new Random()).nextInt(256), (new Random()).nextInt(256));
            JLabel label = new JLabel();
            label.setBackground(color);
            label.setOpaque(true);
            label.setName("disco" + i);
            label.setText(String.valueOf(this.diskNumber - i));
            label.setForeground(new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue()));
            label.setHorizontalAlignment(0);
            label.setCursor(new Cursor(12));
            label.setBounds(x, y, width, height);
            y -= height;
            width -= 20;
            x += 10;
            this.listDiskOrrigin.add(label);
            this.panel.add(this.listDiskOrrigin.get(i));
            this.panel.revalidate();
        }
    }

    private int numberMove() {
        return (int)Math.pow(2.0D, this.diskNumber) - 1;
    }

    private void moveDisk() {
        if (!this.diskUp) {
            if (this.positionDiskCurrent == 1) {
                this.listDiskOrrigin = moveDisk(this.listDiskOrrigin);
            } else if (this.positionDiskCurrent == 2) {
                this.listDiskAux = moveDisk(this.listDiskAux);
            } else if (this.positionDiskCurrent == 3) {
                this.targerListDisk = moveDisk(this.targerListDisk);
            }
        } else if (this.selectedDisk != null) {
            int x = getWidth() / 6 * ((this.positionDiskCurrent == 1) ? 1 : ((this.positionDiskCurrent == 2) ? 3 : 5)) - this.selectedDisk.getWidth() / 2;
            if (this.positionDiskCurrent == 1) {
                this.listDiskOrrigin = moveDisk(this.listDiskOrrigin, x);
            } else if (this.positionDiskCurrent == 2) {
                this.listDiskAux = moveDisk(this.listDiskAux, x);
            } else if (this.positionDiskCurrent == 3) {
                this.targerListDisk = moveDisk(this.targerListDisk, x);
                if (this.targerListDisk.size() == this.diskNumber) {
                    this.win = true;
                    (new Win(this, true, numberMove(), this.moveDone)).setVisible(true);
                }
            }
        }
    }

    private ArrayList<JLabel> moveDisk(ArrayList<JLabel> lista) {
        if (lista.size() > 0) {
            this.diskUp = true;
            this.selectedDisk = lista.get(lista.size() - 1);
            this.selectedDisk.setLocation(this.selectedDisk.getX(), 70);
            lista.remove(lista.size() - 1);
        }
        return lista;
    }

    private ArrayList<JLabel> moveDisk(ArrayList<JLabel> lista, int x) {
        int y = getHeight() - 136;
        boolean regla = true;
        if (lista.size() > 0) {
            JLabel disco = lista.get(lista.size() - 1);
            y = disco.getY() - 25;
            regla = (this.selectedDisk.getWidth() < disco.getWidth());
        }
        if (regla) {
            this.selectedDisk.setLocation(x, y);
            lista.add(this.selectedDisk);
            this.selectedDisk = null;
            this.diskUp = false;
            this.title2.setText("movimiento : " + ++this.moveDone);
        } else {
            JOptionPane.showMessageDialog(this.rootPane, "Movimiento no permitido");
        }
        return lista;
    }

    private void initComponents() {
        setDefaultCloseOperation(3);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 400, 32767));
        layout.setVerticalGroup(layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 300, 32767));
        pack();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, (String)null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new View()).setVisible(true);
            }
        });
    }
}