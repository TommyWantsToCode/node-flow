package tomas.garza.nodeflow.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JWindow;

import lombok.Getter;
import tomas.garza.nodeflow.math.BezierCurve;
import tomas.garza.nodeflow.math.Point2d;

/**
 * Frame transparente que se pinta sobre una pantalla
 * 
 */
@Getter
public class WrapperFrame extends JWindow {

	private static final long serialVersionUID = 1L;

	private PhysicalDisplay screen;
	private WrapperPanel wrapperPanel;
	
	private List<BezierCurve> curves;

	/**
	 * Constructor
	 * 
	 * @param screen
	 */
	public WrapperFrame(PhysicalDisplay screen) {

		this.screen = screen;
		this.curves = new ArrayList<>();

		// Configura el frame transparente
		this.setBackground(new Color(0, 0, 0, 0));
		this.setAlwaysOnTop(true);

		// Agrega wrapper para dibujar
		this.wrapperPanel = new WrapperPanel(this);
		this.setContentPane(wrapperPanel);
		this.setVisible(true);

		updateScreenSize();

	}

	/**
	 * Actualiza el tamaño de la pantalla
	 */
	private void updateScreenSize() {
		this.setBounds(screen.getBounds());
	}

	/**
	 * Pinta sobre la pantalla
	 * 
	 * @param g
	 * @param bounds
	 */
	public void paintWrapper(Graphics2D g, Rectangle bounds) {

		int width = (int) (bounds.width * 0.8);
		int height = (int) (bounds.height * 0.8);
		int x = (bounds.width - width) / 2;
		int y = (bounds.height - height) / 2;

		// Dibuja los puntos de control
		curves.forEach(c -> c.getControlPoints().forEach(point -> {
			g.setColor(Color.BLUE);
			g.fillOval((int) point.x - 10, (int) point.y - 10, 20, 20);
			
			
			
			
		}));

		// Dibuja las curvas
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(3));
		
		
		curves.forEach(c -> c.getControlPoints().forEach(point -> {
			
			Point2d prev = c.interpolate(0);
			for (double t = 0; t <= 1.01; t += 0.01) {
				Point2d current = c.interpolate(t);
				g.drawLine((int) prev.x, (int) prev.y, (int) current.x, (int) current.y);
				prev = current;
			}
		}));
		
		
		

	}

}
