package Presenter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//View
public class AWTWindow {

    static Presenter presenter = new Presenter();

    public static void main(String[] args) {
        Frame frame = new Frame();
        ScrollPane scrollPane = new ScrollPane();
        Panel panel = new Panel();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        presenter.getData(panel);
        Button button = new Button("Click");
        button.addActionListener(e -> {
            presenter.getData(panel);
            frame.pack();
        });
        panel.add(button);
        scrollPane.add(panel);
        frame.add(scrollPane);
        frame.setPreferredSize(new Dimension(1000, 590));
        frame.pack();
        frame.setVisible(true);
    }

    /*@GetMapping("/MVP")
    public String mainMVP_Page() {
        return "jsp/java_servlet_page";
    }*/

}
