package mikera.arrayz.impl;

import java.nio.DoubleBuffer;

import mikera.arrayz.INDArray;
import mikera.vectorz.util.ErrorMessages;
import mikera.vectorz.util.IntArrays;

public class ImmutableArray extends BaseNDArray {
	private ImmutableArray(int dims, int[] shape, int[] strides) {
		this(new double[(int)IntArrays.arrayProduct(shape)],shape.length,0,shape,strides);
	}
	
	private ImmutableArray(double[] data, int dimensions, int offset, int[] shape, int[] stride) {
		super(data,dimensions,offset,shape,stride);
	}
	
	private ImmutableArray(int[] shape, double[] data) {
		this(shape.length, shape, IntArrays.calcStrides(shape), data);
	}

	private ImmutableArray(int dims, int[] shape, double[] data) {
		this(dims, shape, IntArrays.calcStrides(shape), data);
	}
	
	public static INDArray wrap(double[] data, int[] shape) {
		long ec=IntArrays.arrayProduct(shape);
		if (data.length!=ec) throw new IllegalArgumentException("Data array does not have correct number of elements, expected: "+ec);
		return new ImmutableArray(shape.length,shape,data);
	}

	private ImmutableArray(int dims, int[] shape, int[] strides, double[] data) {
		this(data,dims,0,shape,strides);
	}
	
	@Override
	public int dimensionality() {
		return dimensions;
	}

	@Override
	public int[] getShape() {
		return shape;
	}
	
	@Override
	public int[] getShapeClone() {
		return shape.clone();
	}

	@Override
	public long[] getLongShape() {
		long[] lshape = new long[dimensions];
		IntArrays.copyIntsToLongs(shape, lshape);
		return lshape;
	}

	public int getStride(int dim) {
		return stride[dim];
	}

	@Override
	public int getIndex(int... indexes) {
		int ix = offset;
		for (int i = 0; i < dimensions; i++) {
			ix += indexes[i] * getStride(i);
		}
		return ix;
	}

	@Override
	public double get(int... indexes) {
		return data[getIndex(indexes)];
	}

	@Override
	public void set(int[] indexes, double value) {
		throw new UnsupportedOperationException(ErrorMessages.immutable(this));
	}

	@Override
	public INDArray slice(int majorSlice) {
		throw new UnsupportedOperationException("TODO: slices not yet supported on immutable arrays");
	}

	@Override
	public INDArray slice(int dimension, int index) {
		throw new UnsupportedOperationException("TODO: slices not yet supported on immutable arrays");
	}

	@Override
	public int sliceCount() {
		return shape[0];
	}

	@Override
	public long elementCount() {
		return IntArrays.arrayProduct(shape);
	}

	@Override
	public boolean isView() {
		return false;
	}
	
	@Override
	public void toDoubleBuffer(DoubleBuffer dest) {
		dest.put(data);
	}
	
	@Override
	public void getElements(double[] dest, int offset) {
		System.arraycopy(data, 0, dest, offset, data.length);
	}

	@Override
	public INDArray exactClone() {
		return new ImmutableArray(this.shape,data.clone());
	}

	public static INDArray create(INDArray a) {
		int[] shape=a.getShape();
		int n=(int)IntArrays.arrayProduct(shape);
		double[] data = new double[n];
		return ImmutableArray.wrap(data, shape);
	}

	@Override
	public double[] getArray() {
		throw new UnsupportedOperationException("Array access not supported by Immutablearray");
	}
}