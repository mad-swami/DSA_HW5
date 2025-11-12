import org.example.Matrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.expect

class MatrixTest {
    @Test
    fun set() {
    }

    @Test
    fun get() {
    }

    @Test
    fun divide() {
        val testMatrix = Matrix(4)

        val matrixArray = testMatrix.divide()

        for (matrix in matrixArray) {
            assertEquals(matrix.size, 2)
        }
    }

    @Test
    fun times() {
        val size = 2

        val matrixVals1 = arrayOf(2.0, 3.0, 4.0, 9.0)
        val testMatrix1 = Matrix(size)
        var row = 0; var col = 0
        // this is so poorly written lmao, sorry but I'm not going to rewrite
        // idk what I was thinking, I just need two for loops
        for (i in 0 until matrixVals1.size) {
            if (i % 2 == 0) {
                testMatrix1.setValue(row, col, matrixVals1[i])
                col ++
            } else {
                testMatrix1.setValue(row, col, matrixVals1[i])
                row ++; col --
            }
        }

        val matrixVals2 = arrayOf(4.0, 8.0, 2.0, 5.0)
        val testMatrix2 = Matrix(size)
        row = 0; col = 0
        for (j in 0 until matrixVals2.size) {
            if (j % 2 == 0) {
                testMatrix2.setValue(row, col, matrixVals2[j])
                col ++
            } else {
                testMatrix2.setValue(row, col, matrixVals2[j])
                row ++; col --
            }
        }

        val productMatrix = testMatrix1 * testMatrix2

        val expectedVals = arrayOf(14.0, 31.0, 34.0, 77.0)

        var checkVal = 0
        // why I didn't write the first two for loops like this, idk
        for (i in 0 until size ) {
            for (j in 0 until size ) {
                assertEquals(expectedVals[checkVal], productMatrix.getValue(i, j))
                checkVal ++
            }
        }
    }

    @Test
    fun strassenMultiply() {
        val size = 2

        val matrixVals1 = arrayOf(2.0, 3.0, 4.0, 9.0)
        val testMatrix1 = Matrix(size)
        var index = 0
        for (i in 0 until size ) {
            for (j in 0 until size ) {
                testMatrix1.setValue(i, j, matrixVals1[index])
                index ++
            }
        }

        val matrixVals2 = arrayOf(4.0, 8.0, 2.0, 5.0)
        val testMatrix2 = Matrix(size)
        index = 0
        for (i in 0 until size ) {
            for (j in 0 until size ) {
                testMatrix2.setValue(i, j, matrixVals2[index])
                index ++
            }
        }

        val productMatrix = testMatrix1.strassenMultiply(testMatrix2)

        val expectedVals = arrayOf(14.0, 31.0, 34.0, 77.0)
        var checkVal = 0
        // why I didn't write the first two for loops like this, idk
        for (i in 0 until size ) {
            for (j in 0 until size ) {
                assertEquals(expectedVals[checkVal], productMatrix.getValue(i, j))
                checkVal ++
            }
        }

    }

}