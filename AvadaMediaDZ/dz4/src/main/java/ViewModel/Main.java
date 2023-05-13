package ViewModel;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame();
        Panel panel = new Panel();
        Button button = new Button("Click");
        button.addActionListener(e -> {
            ViewModel viewModel = new ViewModel();
            viewModel.getData();
            frame.pack();
        });
        panel.add(button);
        frame.add(panel);
        Observer observer = new Observer();
        observer.addContainer(panel);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.setPreferredSize(new Dimension(1000, 590));
        frame.pack();
        frame.setVisible(true);
    }

}
