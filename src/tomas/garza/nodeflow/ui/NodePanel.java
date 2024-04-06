package tomas.garza.nodeflow.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import lombok.Getter;

public class NodePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SOCKET_SPACING = 5;
	
	private JPanel inputsPanel;
	private JPanel outputsPanel;
	
	@Getter
	private NodeFrame nodeFrame;
	
	@Getter
	private List<InputSocket> inputs;
	
	@Getter
	private List<OutputSocket> outputs;
	
	/**
	 * Constructor de la clase
	 */
	public NodePanel(NodeFrame nodeFrame) {
		
		this.nodeFrame = nodeFrame;
		this.inputs = new ArrayList<>();
		this.outputs = new ArrayList<>();
		
		this.setLayout(new BorderLayout());
		
		this.inputsPanel = new JPanel();
		this.inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));
		this.inputsPanel.add(Box.createVerticalStrut(SOCKET_SPACING));
		
		this.outputsPanel = new JPanel();
		this.outputsPanel.setLayout(new BoxLayout(outputsPanel, BoxLayout.Y_AXIS));
		this.outputsPanel.add(Box.createVerticalStrut(SOCKET_SPACING));
		
		addInput(new InputSocket());
		addInput(new InputSocket());
		addInput(new InputSocket());
		
		addOutput(new OutputSocket());
		addOutput(new OutputSocket());
		addOutput(new OutputSocket());
		
		this.add(inputsPanel, BorderLayout.WEST);
		this.add(outputsPanel, BorderLayout.EAST);

	}
	
	
	public void addInput(InputSocket button)
	{
		button.setParentNode(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputsPanel.add(button);
        inputsPanel.add(Box.createVerticalStrut(SOCKET_SPACING));
        inputs.add(button);
	}

	public void addOutput(OutputSocket button)
	{
		button.setParentNode(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputsPanel.add(button);
        outputsPanel.add(Box.createVerticalStrut(SOCKET_SPACING));
        outputs.add(button);
	}

}
