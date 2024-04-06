package tomas.garza.nodeflow;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.SwingUtilities;

import tomas.garza.nodeflow.ui.NodeFrame;
import tomas.garza.nodeflow.ui.NodeManager;
import tomas.garza.nodeflow.ui.PhysicalDisplay;
import tomas.garza.nodeflow.ui.WrapperFrame;

/**
 * Clase principal
 * 
 */
public class Main {

	
	public Main()
	{
		
		// Genera un manejador de nodos
		NodeManager manager = new NodeManager();

		// Busca las pantallas de este sistema
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		for (GraphicsDevice screen : gs) {
			// Crea un frame por cada pantalla
			PhysicalDisplay display = new PhysicalDisplay(screen);
			WrapperFrame displayCanvas = new WrapperFrame(display);
			manager.addDisplayCanvas(displayCanvas);
		}
		
		manager.addNode(new NodeFrame("Nodo prueba 1"));
		manager.addNode(new NodeFrame("Nodo prueba 2"));
		manager.addNode(new NodeFrame("Nodo prueba 3"));
		
	}
	
	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});

	}

}
