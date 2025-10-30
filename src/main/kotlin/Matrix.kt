package org.example

import kotlin.math.log

/**
 * Create, manipulate, and store square matrix of size n.
 *
 * Matrix creates a square matrix of size n thus with dimensions n x n. Getting values from the matrix can be done
 * through get by specifying row and column indices. Matrix operations such as addition and multiplication can be done
 * through operators (e.g., + , *). Provided is also functions to divide the matrix into n/2 x n/2 matrices.
 *
 * @param size of matrix to be made, matrix will be matrixSize
 */
class Matrix(val size: Int) {
    private val matrixArray: Array<DoubleArray> = Array(size) {DoubleArray(size)}

    /**
     * Set a value at a given row and column index.
     *
     * Given a row and column place, stores the value at the specific index.
     *
     * @param row row of the matrix
     * @param column column of the matrix
     * @param value value to be set at the specific row column index
     * @throws IllegalArgumentException if the rows or column input is out of bounds of the matrix
     */
    fun setValue(row: Int, column: Int, value: Double) {
        if (row < size && column < size) {
            matrixArray[row][column] = value
        } else {
            throw IllegalArgumentException("Input out of bounds!")
        }
    }

    /**
     * Get a value from the matrix given specific row and column indices.
     *
     * Given a row and column place, returns the value stored at that specific index.
     *
     * @param row row of the matrix
     * @param column of the matrix
     * @return the value of the matrix at the specific row and column index
     * @throws IllegalArgumentException if the input row or column is out of bounds of the original matrix
     */
    fun getValue(row: Int, column: Int): Double {
        if (row < size && column < size) {
            return matrixArray[row][column]
        } else {
            throw IllegalArgumentException("Input out of bounds!")
        }
    }

    /**
     * Add a new matrix to the current matrix.
     *
     * Given a matrix add it to the current matrix object and return as a new matrix.
     *
     * @param matrix Matrix object to be added
     * @return a new matrix that is the sum of the two matrices
     * @throws IllegalArgumentException if the input matrix is not of the same size they cannot be added
     */
    operator fun plus(matrix: Matrix): Matrix {
        if (matrix.size != this.size) {
            throw IllegalArgumentException("Input matrix must be of equal size!")
        }
        val addedMatrix = Matrix(size)
        for(row in 0 until matrix.size - 1) {
            for(col in 0 until matrix.size - 1) {
                val addedVal = this.matrixArray[row][col] + matrix.getValue(row, col)
                 addedMatrix.setValue(row, col, addedVal)
            }
        }
        return addedMatrix
    }

    /**
     * Divide the matrix object into four new n/2 x n/2 matrices.
     *
     * Takes a matrix object, assuming its size is a power of 2, and splits it into four n/2 x n/2 matrices. The matrices
     * are then returned in an array in clockwise order from top left of the original matrix.
     *
     * @return an array of matrix objects
     */
    fun divide(): Array<Matrix> {
        // assume dimension n is power of 2
        // maybe I will come back later and add padding but for now I am assuming the above

        require(log(size.toDouble(), 2.0).mod(2.0) == 0.0) {"The size of the matrix to be divided must be of a power to 2."}
        // the four matrices we divide the original into
        // they go in clockwise order from top left
        val matrixSizes = size / 2

        val matrix1 = Matrix(matrixSizes)
        val matrix2 = Matrix(matrixSizes)
        val matrix3 = Matrix(matrixSizes)
        val matrix4 = Matrix(matrixSizes)

        // create an array of the matrices for each retrieval later
        val matrixArray: Array<Matrix> = arrayOf(matrix1, matrix2, matrix3, matrix4)

        // go through all rows of our matrix
        for(i in 0 until size) {
            // go through all columns of our matrix
            for(j in 0 until size) {
                // get the value at the specific index of our matrix
                val matrixVal = this.getValue(i, j)

                // if we are in the first half of the rows then we assign to the matrices 1 and 2
                if(i <= matrixSizes - 1 ) {
                    // if we are in first half of the columns then we assign to 1
                    if (j <= matrixSizes - 1) {
                        matrix1.setValue(i, j, matrixVal)
                    // otherwise we assign to 2
                    } else {
                        matrix2.setValue(i, j - matrixSizes, matrixVal)
                    }

                // opposite case of first two matrices, e.g. we are in the second half of the rows
                } else {
                    // same thing we did with first two, check if we are in first half of columns or not
                    if (j <= matrixSizes - 1) {
                        matrix3.setValue(i - matrixSizes, j, matrixVal)
                    } else {
                        matrix4.setValue(i - matrixSizes, j - matrixSizes, matrixVal)
                    }
                }
            }
        }
        return matrixArray
    }
}