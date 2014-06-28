package mikera.vectorz.impl;

/**
 * Base class for all vectors backed by a single final double[] array
 * @author Mike
 *
 */
public abstract class AArrayVector extends ASizedVector {
	private static final long serialVersionUID = -6271828303431809681L;

	protected final double[] data;
	
	protected AArrayVector(int length, double[] data) {
		super(length);
		this.data=data;
	}
	
	/**
	 * Computes an index into the underlying array for a given vector index
	 */
	protected abstract int index(int i);

}
