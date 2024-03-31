import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovingWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setAlwaysOnTop(true);

        JButton button = new JButton("Click Me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move the window to a new location
                frame.setLocation(frame.getX() + 100, frame.getY() + 100);
            }
        });

        frame.add(button);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Move the window to a new location when clicked
                frame.setLocation(frame.getX() + 100, frame.getY() + 100);
            }
        });

        frame.setVisible(true);
    }
}
