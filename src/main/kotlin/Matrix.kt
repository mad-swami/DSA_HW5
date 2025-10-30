package org.example

/**
 * Create, manipulate, and store square matrix of size n.
 *
 * Matrix creates a square matrix of size n thus with dimensions n x n. Getting values from the matrix can be done
 * through get by specifying row and column indices. Matrix operations such as addition and multiplication can be done
 * through operators (e.g., + , *). Provided is also functions to divide the matrix into n/2 x n/2 matrices.
 */
class Matrix(size: Int) {
    val matrixArray: Array<DoubleArray> = Array(size) {DoubleArray(size)}
    val matrixSize = size

    fun set(row: Int, column: Int, value: Double) {
        matrixArray[row][column] = value
    }

    fun get(row: Int, column: Int): Double {
        return matrixArray[row][column]
    }

    fun divide(): Array<Matrix> {
        // assume dimension n is power of 2
        // maybe I will come back later and add padding but for now I am assuming the above

        // the four matrices we divide the original into
        // they go in clockwise order from top left
        val matrix1: Matrix = Matrix(matrixSize / 2)
        val matrix2: Matrix = Matrix(matrixSize / 2)
        val matrix3: Matrix = Matrix(matrixSize / 2)
        val matrix4: Matrix = Matrix(matrixSize / 2)

        // create an array of the matrices for each retrieval later
        val matrixArray: Array<Matrix> = arrayOf(matrix1, matrix2, matrix3, matrix4)

        // go through all rows of our matrix
        for(i in 0 until matrixSize) {
            // go through all columns of our matrix
            for(j in 0 until matrixSize) {
                // get the value at the specific index of our matrix
                val matrixVal = this.get(i, j)

                // if we are in the first half of the rows then we assign to the matrices 1 and 2
                if(matrixSize / (i + 1) <= matrixSize / 2) {
                    // if we are in first half of the columns then we assign to 1
                    if (matrixSize / (j + 1) <= matrixSize / 2) {
                        matrix1.set(i, j, matrixVal)
                    // otherwise we assign to 2
                    } else {
                        matrix2.set(i, j, matrixVal)
                    }

                // opposite case of first two matrices, e.g. we are in the second half of the rows
                } else {
                    // same thing we did with first two, check if we are in first half of columns or not
                    if (matrixSize / (j + 1) <= matrixSize / 2) {
                        matrix3.set(i, j, matrixVal)
                    } else {
                        matrix4.set(i, j, matrixVal)
                    }
                }
            }
        }
        return matrixArray
    }
}