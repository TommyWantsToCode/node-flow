package tomas.garza.nodeflow.math;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Representa un punto 2D
 */
@AllArgsConstructor
@NoArgsConstructor
public class Point2d {

	public double x;
	public double y;

	// Constructor copiador
	public Point2d(Point2d p) {
		this.x = p.x;
		this.y = p.y;
	}

}
