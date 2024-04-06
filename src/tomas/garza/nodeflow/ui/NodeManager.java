package tomas.garza.nodeflow.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import tomas.garza.nodeflow.math.BezierCurve;
import tomas.garza.nodeflow.math.Point2d;

public class NodeManager {

	private List<NodeFrame> nodes;
	private List<SocketLink> socketLinks;
	
	private List<WrapperFrame> displayCanvas;
	
	private OutputSocket currentDragging;

	public NodeManager()
	{

		this.nodes = new ArrayList<>();
		this.socketLinks = new ArrayList<>();
		
		this.displayCanvas = new ArrayList<>();

	}
	
	void updateLinks() {

		
		displayCanvas.forEach(canvas -> {
			canvas.getCurves().clear();
		});
		
		// Creates a curve for each link
		for (SocketLink link : socketLinks) {

			Component output = link.getOutput();
			Component input = link.getInput();

			Point outputLocation = output.getLocationOnScreen();
			Point inputLocation = input.getLocationOnScreen();

			Rectangle outputBounds = output.getBounds();
			Rectangle inputBounds = input.getBounds();

			outputBounds.setLocation(outputLocation.x + outputBounds.x, outputLocation.y + outputBounds.y);
			inputBounds.setLocation(inputLocation.x + inputBounds.x, inputLocation.y + inputBounds.y);

			Point2d outputCenter = new Point2d(outputBounds.x + outputBounds.width / 2,
					outputBounds.y + outputBounds.height / 2);
			Point2d inputCenter = new Point2d(inputBounds.x + inputBounds.width / 2,
					inputBounds.y + inputBounds.height / 2);

			

			// Calculate control points
			double controlPointOffsetX = (outputCenter.x - inputCenter.x) / 3;

			Point2d controlPoint1 = new Point2d(Math.min(inputCenter.x + controlPointOffsetX, inputCenter.x - 50), 
			                                           inputCenter.y); // Control point 1
			Point2d controlPoint2 = new Point2d(Math.max(outputCenter.x - controlPointOffsetX, outputCenter.x + 50), 
			                                           outputCenter.y); // Control point 2

			BezierCurve curve = new BezierCurve();
			curve.add(inputCenter);       // Start point
			curve.add(controlPoint1);     // Control point 1
			curve.add(controlPoint2);     // Control point 2
			curve.add(outputCenter);      // End point
            
            displayCanvas.forEach(canvas -> {
    			canvas.getCurves().add(curve);
    		});
			
		}
		
		displayCanvas.forEach(canvas -> {
			canvas.repaint();
		});
		
		
	}

	public void addDisplayCanvas(WrapperFrame canvas) {
		this.displayCanvas.add(canvas);
		
	}

	public void addNode(NodeFrame node) {
		node.setManager(this);
		nodes.add(node);
	}

	public void onSocketPressed(NodeSocket nodeSocket, MouseEvent e) {
		
		System.out.println("Socket presionado");
		
		if (currentDragging != null)
			return;
		
		if (nodeSocket instanceof OutputSocket)
		{
			
			System.out.println("Es un output socket");
			
			this.currentDragging = (OutputSocket) nodeSocket;
			
			
			
			
		}
		
	}
	
	public void redrawLinks()
	{
		
	}

	public void onSocketReleased(NodeSocket nodeSocket, MouseEvent e) {
		
		System.out.println("Socket soltado");
		
		// Guarda el socket de salida que se estaba arrastrando y limipia la variable
		OutputSocket output = currentDragging;
		this.currentDragging = null;
		
		// Ignora si no se estaba arrastrando un socket de salida
		if (output == null)
			return;
		
		System.out.println("El socket existe");
		
	    

		
		// Revisa si se soltó el mouse sobre un nodo
		for (NodeFrame node : nodes) {
			
			// Evita que un nodo se conecte a sí mismo
			if (output.getParentNode().getNodeFrame().equals(node))
				continue;
			
			if (node.getBounds().contains(e.getLocationOnScreen())) {
				
				
				System.out.println("Soltado sobre un nodo");
				
				// Revisa si se soltó el mouse sobre un input
				for (InputSocket input : node.getNodePanel().getInputs()) {

					 Rectangle inputBounds = input.getBounds();
					 Point inputLocationOnScreen = input.getLocationOnScreen();
					 inputBounds.setLocation(inputLocationOnScreen.x + inputBounds.x, inputLocationOnScreen.y + inputBounds.y);
					
					if (inputBounds.contains(e.getLocationOnScreen())) {
						// Crea una conexión lógica entre nodos
						socketLinks.add(new SocketLink(input, output));
						updateLinks();
						return;
					}
				}
				
				
			}
		}
		
		
		
	}

	public void onSocketDragged(NodeSocket nodeSocket, MouseEvent e) {
		
		
	}

	public void onNodeMoved(NodeFrame nodeFrame, ComponentEvent e) {
		updateLinks();
		
	}

}
