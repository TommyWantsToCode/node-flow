import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragAndDropLine {
    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        JFrame frame1 = new JFrame();
        JFrame frame2 = new JFrame();
        JFrame transparentFrame = new JFrame();

        // Position frames on different screens if available
        if (gs.length > 1) {
            Rectangle bounds1 = gs[0].getDefaultConfiguration().getBounds();
            Rectangle bounds2 = gs[1].getDefaultConfiguration().getBounds();
            frame1.setBounds(bounds1.x + 100, bounds1.y + 100, 200, 200);
            frame2.setBounds(bounds2.x + 100, bounds2.y + 100, 200, 200);
        } else {
            frame1.setBounds(100, 100, 200, 200);
            frame2.setBounds(400, 100, 200, 200);
        }

        // Create a transparent frame that spans all screens
        final Rectangle allScreenBounds = new Rectangle();
        for (GraphicsDevice device : gs) {
            allScreenBounds.setBounds(allScreenBounds.union(device.getDefaultConfiguration().getBounds()));
        }
        transparentFrame.setBounds(allScreenBounds);
        transparentFrame.setUndecorated(true);
        transparentFrame.setBackground(new Color(0, 0, 0, 0));
        transparentFrame.setAlwaysOnTop(true);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (frame1.isShowing() && frame2.isShowing()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(Color.WHITE);
                    Point p1 = frame1.getLocationOnScreen();
                    Point p2 = frame2.getLocationOnScreen();
                    g2d.drawLine(p1.x + 100 - allScreenBounds.x, p1.y + 100 - allScreenBounds.y, 
                                p2.x + 100 - allScreenBounds.x, p2.y + 100 - allScreenBounds.y);
                }
            }
        };
        panel.setOpaque(false);
        transparentFrame.add(panel);

        frame1.setVisible(true);
        frame2.setVisible(true);
        transparentFrame.setVisible(true);

        Timer timer = new Timer(30, e -> panel.repaint());
        timer.start();

        addDraggableFeature(frame1);
        addDraggableFeature(frame2);
    }

    private static void addDraggableFeature(JFrame frame) {
        final Point offset = new Point();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                offset.setLocation(e.getPoint());
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = frame.getLocation().x + e.getX() - offset.x;
                int y = frame.getLocation().y + e.getY() - offset.y;
                frame.setLocation(x, y);
            }
        });
    }
}
