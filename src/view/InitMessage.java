package view;

import javax.swing.*;
import java.awt.*;

public class InitMessage {
    public int readDiskNumber(Component component,int diskNumber) {
        try {
            diskNumber = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de discos"));
            if (diskNumber < 1 || diskNumber > 18) {
                JOptionPane.showMessageDialog(component, "El numero de discos debe ser %numeroDiscos% >= 2 y %numeroDiscos% <= 50".replaceAll("%numeroDiscos%", String.valueOf(diskNumber)));
                readDiskNumber(component,diskNumber);
            }
        } catch (HeadlessException|NumberFormatException e) {
            JOptionPane.showMessageDialog(component, "Ingrese solo numeros enteros");
            readDiskNumber(component,diskNumber);
        }
        return diskNumber;
    }
}
