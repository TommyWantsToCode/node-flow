package tomas.garza.nodeflow.math;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Clase que representa una curva de Bezier
 */
@Getter
public class BezierCurve {

	public List<Point2d> controlPoints;

	/**
	 * Constructor
	 */
	public BezierCurve() {
		this.controlPoints = new ArrayList<>();
	}

	/**
	 * Agrega un punto de control
	 * 
	 * @param point
	 */
	public void add(Point2d point) {
		controlPoints.add(point);
	}

	/**
	 * Quita un punto de control
	 * 
	 * @param index
	 */
	public void remove(int index) {
		controlPoints.remove(index);
	}

	/**
	 * Devuelve el número de puntos de control
	 * 
	 * @return int
	 */
	public int getSize() {
		return controlPoints.size();
	}

	/**
	 * Interpola entre dos puntos
	 * 
	 * @param point
	 */
	private Point2d interpolate(Point2d a, Point2d b, double t) {
		double x = a.x + (b.x - a.x) * t;
		double y = a.y + (b.y - a.y) * t;
		return new Point2d(x, y);
	}

	/**
	 * Interpola la curva de Bezier
	 * 
	 * @param t
	 * @return Point2d
	 */
	public Point2d interpolate(double t) {

		// Evita null pointer exception
		if (controlPoints.size() == 0)
			return null;

		// Si solo hay un punto, devolver ese punto
		if (controlPoints.size() == 1)
			return new Point2d(controlPoints.get(0));

		// Crea una curva reducida a partir de las interpolaciones
		BezierCurve reducedCurve = new BezierCurve();
		for (int i = 0; i < controlPoints.size() - 1; i++) {
			Point2d a = controlPoints.get(i);
			Point2d b = controlPoints.get(i + 1);
			reducedCurve.add(interpolate(a, b, t));
		}
		return reducedCurve.interpolate(t);

	}

}
