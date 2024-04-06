package tomas.garza.nodeflow.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocketLink {

	private InputSocket input;
	private OutputSocket output;
	
}
