import org.example.Matrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.measureTime

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

    /**
     * For the purpose of testing Strassen's method versus regular matrix multiplication on various size problems
     */
    @Test
    fun multiplicationRuntimes() {
        val sizes = listOf(4, 16, 256, 1024)
        val matrixArray1 = mutableListOf<Matrix>()
        val matrixArray2 = mutableListOf<Matrix>()

        for (size in sizes) {
            val matrix1 = Matrix(size)
            val matrix2 = Matrix(size)
            for (i in 0 until size) {
                for (j in 0 until size) {
                    matrix1.setValue(i ,j, Random.nextDouble())
                    matrix2.setValue(i, j, Random.nextDouble())
                }
            }
            matrixArray1.add(matrix1)
            matrixArray2.add(matrix2)
        }

        val regularRuntimes = mutableListOf<Double>()
        val strassenRuntimes = mutableListOf<Double>()
        for (index in matrixArray1.indices) {
            val matrix1 = matrixArray1[index]
            val matrix2 = matrixArray2[index]
            val regularRuntime = measureTime {
                matrix1 * matrix2
            }
            regularRuntimes.add(regularRuntime.toDouble(DurationUnit.SECONDS))
            val strassenRuntime = measureTime {
                matrix1.strassenMultiply(matrix2)
            }
            strassenRuntimes.add(strassenRuntime.toDouble(DurationUnit.SECONDS))
        }

        for (index in sizes.indices) {
            val size = sizes[index]
            val regularRuntime = regularRuntimes[index]
            val strassenRuntime = strassenRuntimes[index]
            println("The runtime for regular multiplication on a dataset of size $size was $regularRuntime seconds.\n")
            println("The runtime for strassen multiplication on a dataset of size $size was $strassenRuntime seconds.\n")
        }

        assert(true)
    }

}