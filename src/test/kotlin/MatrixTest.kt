import org.example.Matrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
            assertEquals(matrix.matrixSize, 2)
        }
    }

}