package tomas.garza.nodeflow.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * Panel que se pinta sobre una pantalla
 */
public class WrapperPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private WrapperFrame parentFrame;
	private Rectangle bounds;

	/**
	 * Constructor
	 * 
	 * @param parentFrame
	 */
	public WrapperPanel(WrapperFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.setOpaque(false);
		this.bounds = new Rectangle();
	}

	/**
	 * Le da el control de pintar al frame padre
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Rectangle parentBounds = parentFrame.getScreen().getBounds();
		bounds.width = parentBounds.width;
		bounds.height = parentBounds.height;
		parentFrame.paintWrapper((Graphics2D) g, bounds);
	}

}
