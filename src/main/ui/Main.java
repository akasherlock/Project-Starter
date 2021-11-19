package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Gui app = new Gui();
            app.setTitle("ToDoListApplication");
            app.setVisible(true);
            app.setSize(1000, 600);
            app.setLocation(200,100);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImageIcon appImage = new ImageIcon("data/AppIcon.png");
            app.setIconImage(appImage.getImage());

        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }
}

