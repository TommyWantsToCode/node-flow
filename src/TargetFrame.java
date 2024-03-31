import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TargetFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Target Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);

        JButton button = new JButton("Click Target");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Robot robot = new Robot();
                            Point location = frame.getLocationOnScreen();
                            robot.mouseMove(location.x + 150, location.y + 150);
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        } catch (AWTException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.SOUTH);

        frame.setContentPane(panel);

        // GlassPane to draw the target lines
        JPanel glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawLine(150, 100, 150, 200);
                g.drawLine(100, 150, 200, 150);
            }
        };
        glassPane.setOpaque(false);
        glassPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispatchEvent(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                dispatchEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dispatchEvent(e);
            }
        });
        glassPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dispatchEvent(e);
            }
        });

        frame.setGlassPane(glassPane);
        glassPane.setVisible(true);

        frame.setVisible(true);
    }

    private static void dispatchEvent(MouseEvent e) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(e.getXOnScreen(), e.getYOnScreen());
            int buttons = e.getModifiersEx();
            if ((buttons & InputEvent.BUTTON1_DOWN_MASK) != 0) {
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
}
