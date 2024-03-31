package tomas.garza.nodeflow.ui;

import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import lombok.Getter;
import tomas.garza.nodeflow.util.EncoderUtils;

/**
 * Representa una pantalla física
 */
@Getter
public class PhysicalDisplay {

	private GraphicsDevice graphicsDevice;
	private String screenId;

	/**
	 * Constructor
	 * 
	 * @param screen
	 */
	public PhysicalDisplay(GraphicsDevice graphicsDevice) {
		this.graphicsDevice = graphicsDevice;

		GraphicsConfiguration config = graphicsDevice.getDefaultConfiguration();
		Rectangle bounds = config.getBounds();
		DisplayMode mode = graphicsDevice.getDisplayMode();
		this.screenId = EncoderUtils.toBase64(EncoderUtils
				.toMD5(graphicsDevice.getIDstring() + "-" + bounds.x + "," + bounds.y + "-" + mode.getWidth() + "x"
						+ mode.getHeight() + "-" + mode.getBitDepth() + "bit-" + mode.getRefreshRate() + "Hz"));
	}

	/**
	 * Obtiene el tamaño de la pantalla
	 * 
	 * @return Rectangle
	 */
	public Rectangle getBounds() {
		return graphicsDevice.getDefaultConfiguration().getBounds();
	}

	/**
	 * Checa si la pantalla es válida
	 *
	 * @return Verdadero si la pantalla aun existe
	 */
	public boolean isScreenValid() {
		for (GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
			if (device.equals(graphicsDevice))
				return true;
		}
		return false;
	}

}
