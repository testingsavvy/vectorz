/*
 * Copyright (c) 2009-2012, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * EJML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * EJML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EJML.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ejml.data;

import org.ejml.alg.dense.mult.MatrixDimensionException;


/**
 * A generic abstract class for matrices whose data is stored in a single 1D array of doubles.  The
 * format of the elements in this array is not specified.  For example row major, column major,
 * and block row major are all common formats.
 *
 * @author Peter Abeles
 */
public abstract class D1Matrix64F extends Matrix64F {
    /**
     * Where the raw data for the matrix is stored.  The format is type dependent.
     */
    public double[] data;

    /**
     * Used to get a reference to the internal data.
     *
     * @return Reference to the matrix's data.
     */
    public double[] getData() {
        return data;
    }

	/**
	 * Changes the internal array reference.
	 */
	public void setData( double[] data ) {
		this.data = data;
	}

    /**
     * Returns the internal array index for the specified row and column.
     *
     * @param row Row index.
     * @param col Column index.
     * @return Internal array index.
     */
    public abstract int getIndex( int row, int col );

    /**
     * Sets the value of this matrix to be the same as the value of the provided matrix.  Both
     * matrices must have the same shape:<br>
     * <br>
     * a<sub>ij</sub> = b<sub>ij</sub><br>
     * <br>
     *
     * @param b The matrix that this matrix is to be set equal to.
     */
    public void set( D1Matrix64F b )
    {
        if( rows != b.rows || cols != b.cols ) {
            throw new MatrixDimensionException("The two matrices do not have compatible shapes.");
        }

        int dataLength = (int)b.elementCount();

        System.arraycopy(b.data, 0, this.data, 0, dataLength);
    }

    /**
     * Returns the value of the matrix at the specified internal array index. The element at which row and column
     * returned by this function depends upon the matrix's internal structure, e.g. row-major, column-major, or block.
     *
     * @param index Internal array index.
     * @return Value at the specified index.
     */
    public double get( int index ) {
        return data[index];
    }

    /**
     * Sets the element's value at the specified index.  The element at which row and column
     * modified by this function depends upon the matrix's internal structure, e.g. row-major, column-major, or block.
     *
     * @param index Index of element that is to be set.
     * @param val The new value of the index.
     */
    public double set( int index , double val ) {
        // See benchmarkFunctionReturn.  Pointless return does not degrade performance.  Tested on JDK 1.6.0_21
        return data[index] = val;
    }

    /**
     * <p>
     * Adds the specified value to the internal data array at the specified index.<br>
     * <br>
     * Equivalent to: this.data[index] += val;
     * </p>
     *
     * <p>
     * Intended for use in highly optimized code.  The  row/column coordinate of the modified element is
     * dependent upon the matrix's internal structure.
     * </p>
     *
     * @param index The index which is being modified.
     * @param val The value that is being added.
     */
    public double plus( int index , double val ) {
        // See benchmarkFunctionReturn.  Pointless return does not degrade performance.  Tested on JDK 1.6.0_21
        return data[index] += val;
    }

    /**
     * <p>
     * Subtracts the specified value to the internal data array at the specified index.<br>
     * <br>
     * Equivalent to: this.data[index] -= val;
     * </p>
     *
     * <p>
     * Intended for use in highly optimized code.  The  row/column coordinate of the modified element is
     * dependent upon the matrix's internal structure.
     * </p>
     *
     * @param index The index which is being modified.
     * @param val The value that is being subtracted.
     */
    public double minus( int index , double val ) {
        // See benchmarkFunctionReturn.  Pointless return does not degrade performance.  Tested on JDK 1.6.0_21
        return data[index] -= val;
    }

    /**
     * <p>
     * Multiplies the specified value to the internal data array at the specified index.<br>
     * <br>
     * Equivalent to: this.data[index] *= val;
     * </p>
     *
     * <p>
     * Intended for use in highly optimized code.  The  row/column coordinate of the modified element is
     * dependent upon the matrix's internal structure.
     * </p>
     *
     * @param index The index which is being modified.
     * @param val The value that is being multiplied.
     */
    public double times( int index , double val ) {
        // See benchmarkFunctionReturn.  Pointless return does not degrade performance.  Tested on JDK 1.6.0_21
        return data[index] *= val;
    }

    /**
     * <p>
     * Divides the specified value to the internal data array at the specified index.<br>
     * <br>
     * Equivalent to: this.data[index] /= val;
     * </p>
     *
     * <p>
     * Intended for use in highly optimized code.  The  row/column coordinate of the modified element is
     * dependent upon the matrix's internal structure.
     * </p>
     *
     * @param index The index which is being modified.
     * @param val The value that is being divided.
     */
    public double div( int index , double val ) {
        // See benchmarkFunctionReturn.  Pointless return does not degrade performance.  Tested on JDK 1.6.0_21
        return data[index] /= val;
    }
}