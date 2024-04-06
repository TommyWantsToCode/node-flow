package tomas.garza.nodeflow.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeSocket extends JButton {

	private static final long serialVersionUID = 1L;

	private NodePanel parentNode;
	private NodeManager manager;

	public NodeSocket() {
		
		
		super("X");
		
		// Add drag and drop listener
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	onPressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	onReleased(e);
            }
        });

		this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	onDragged(e);
            }
        });
	    
		
		
	}

	/**
	 * Evento cuando se presiona el botón del mouse
	 * 
	 * @param e
	 */
	public void onPressed(MouseEvent e) {

		NodeManager manager =  getManager();
		if (manager == null)
			return;
		
		manager.onSocketPressed(this, e);
		
	}

	/**
	 * Evento cuando se suelta el botón del mouse
	 * 
	 * @param e
	 */
	public void onReleased(MouseEvent e) {

		NodeManager manager = getManager();
		if (manager == null)
			return;

		manager.onSocketReleased(this, e);

	}

	/**
	 * Evento cuando se arrastra el mouse
	 * 
	 * @param e
     */
	public void onDragged(MouseEvent e) {

		NodeManager manager = getManager();
		if (manager == null)
			return;

		manager.onSocketDragged(this, e);

	}
	

	/**
	 * Escanea la jerarquía de nodos para encontrar el manejador de nodos
	 * 
	 * @return El manejador de nodos o null si no se encuentra
	 */
	public NodeManager getManager()
	{
		
		if (manager != null)
			return manager;
		
		if (parentNode == null)
			return null;
		
		if (parentNode.getNodeFrame() == null)
			return null;
		
		this.manager = parentNode.getNodeFrame().getManager();
		return manager;

	}

}
