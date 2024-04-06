package tomas.garza.nodeflow.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.WindowConstants;

import lombok.Getter;
import lombok.Setter;

public class NodeFrame extends JDialog {

	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private NodeManager manager;
	
	@Getter
	private NodePanel nodePanel;

	/**
	 * Constructor de la clase
	 */
	public NodeFrame(String title) {
		
		
		this.nodePanel = new NodePanel(this);
		
		this.setTitle(title);
		this.setContentPane(nodePanel);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
		
		
		this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
            	onMoveEvent(e);
            }
        });
		
		
		this.setVisible(true);
		
		
		
	}
	
	public void onMoveEvent(ComponentEvent e)
	{
		if (manager != null)
			manager.onNodeMoved(this, e);
	}

	
}
