package tomas.garza.nodeflow;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingUtilities;

import tomas.garza.nodeflow.ui.PhysicalDisplay;
import tomas.garza.nodeflow.ui.WrapperFrame;

/**
 * Clase principal
 * 
 */
public class Main {

	
	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            	// Busca las pantallas de este sistema
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] gs = ge.getScreenDevices();
                for (GraphicsDevice screen : gs) {
                	// Crea un frame por cada pantalla
                    new WrapperFrame(new PhysicalDisplay(screen)).setVisible(true);
                }

            }
        });
		
	}
	
	
	
}
