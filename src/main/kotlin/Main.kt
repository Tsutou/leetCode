fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {

    // 4. Median of Two Sorted Arrays
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val mergedArray = nums1 + nums2
        mergedArray.sort()
        val totalSize = mergedArray.size
        val isOdd = totalSize % 2 != 0
        return if (isOdd) {
            val midIndex = (totalSize - 1) / 2
            mergedArray[midIndex].toDouble()
        } else {
            val midIndex = totalSize / 2
            (mergedArray[midIndex - 1] + mergedArray[midIndex]) / 2.toDouble()
        }
    }

    // 5. Longest Palindromic Substring
    fun longestPalindrome(s: String): String {
        val size = s.length
        repeat(size) { index ->
            val window = s.windowedSequence(
                size - index
            ).filter {
                it.first() == it.last()
            }.firstOrNull {
                it == it.reversed()
            } ?: return@repeat

            return window
        }

        return ""
    }
}