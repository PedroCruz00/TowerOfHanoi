package presenter;


import model.Disk;
import view.View;

public class TorreDeHanoi {

    private View view;
    private Disk disk;

    public TorreDeHanoi() {
        view = new View(25,350);
        run();
    }

    private void run(){
        view.setVisible(true);
    }
    public static void main(String[] args) {
        new TorreDeHanoi();
    }
}