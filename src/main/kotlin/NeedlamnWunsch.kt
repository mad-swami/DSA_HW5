package org.example

data class NWResult(
    val score: Int,
    val alignedA: String,
    val alignedB: String
)

class NeedlemanWunsch(
    private val matchScore: Int = 1,
    private val mismatchScore: Int = -1,
    private val gapScore: Int = -2
) {
    private fun sub(a: Char, b: Char): Int = if (a == b) matchScore else mismatchScore

    fun align(a: String, b: String): NWResult {
        val n = a.length
        val m = b.length

        val dp = Array(n + 1) { IntArray(m + 1) }
        val bt = Array(n + 1) { CharArray(m + 1) }

        for (i in 1..n) {
            dp[i][0] = i * gapScore
            bt[i][0] = 'U'
        }
        for (j in 1..m) {
            dp[0][j] = j * gapScore
            bt[0][j] = 'L'
        }

        for (i in 1..n) {
            for (j in 1..m) {
                val diag = dp[i - 1][j - 1] + sub(a[i - 1], b[j - 1])
                val up = dp[i - 1][j] + gapScore
                val left = dp[i][j - 1] + gapScore

                val best = maxOf(diag, up, left)
                dp[i][j] = best
                bt[i][j] = when (best) {
                    diag -> 'D'
                    up -> 'U'
                    else -> 'L'
                }
            }
        }

        val outA = StringBuilder()
        val outB = StringBuilder()
        var i = n
        var j = m

        while (i > 0 || j > 0) {
            when {
                i > 0 && j > 0 && bt[i][j] == 'D' -> {
                    outA.append(a[i - 1])
                    outB.append(b[j - 1])
                    i--; j--
                }
                i > 0 && (j == 0 || bt[i][j] == 'U') -> {
                    outA.append(a[i - 1])
                    outB.append('-')
                    i--
                }
                j > 0 -> {
                    outA.append('-')
                    outB.append(b[j - 1])
                    j--
                }
            }
        }

        return NWResult(
            score = dp[n][m],
            alignedA = outA.reverse().toString(),
            alignedB = outB.reverse().toString()
        )
    }
}
