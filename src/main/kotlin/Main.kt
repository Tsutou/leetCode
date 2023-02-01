import kotlin.math.abs

fun main(args: Array<String>) {
    val solution = Solution()

    println(solution.myAtoi("4193 with words"))
}

class Solution {

    //1. Two Sum
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        for (i in nums.indices) {
            if (map.containsKey(target - nums[i])) {
                val tmp = map[target - nums[i]]!!.toInt()
                return intArrayOf(tmp, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }

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

    //7. Reverse Integer
    fun reverse(x: Int): Int {
        val absX = abs(x).toString()
        return when {
            x <= Integer.MIN_VALUE -> return 0
            absX.toLong() >= Integer.MAX_VALUE || absX.reversed().toLong() >= Integer.MAX_VALUE -> return 0
            x < 0 -> absX.reversed().toInt().unaryMinus()
            else -> x.toString().reversed().toInt()
        }
    }

    //8. String to Integer (atoi)
    fun myAtoi(s: String): Int {
        val num = s.toIntOrNull()
        return if (num != null) {
            num
        } else {
            fun isSign(s: Char): Boolean {
                return s == '+' || s == '-'
            }

            val trimmed= s.dropWhile { it.isWhitespace() }

            val sign = trimmed.takeWhile { isSign(it) }
            val filteredLong = trimmed
                .dropWhile { isSign(it) }
                .takeWhile { it.isDigit() || it == '.'}

            val final = if (sign.isEmpty()) {
                filteredLong
            } else {
                sign + filteredLong
            }.toDoubleOrNull() ?: 0.0

            when {
                final > Integer.MAX_VALUE -> Integer.MAX_VALUE
                final < Integer.MIN_VALUE -> Integer.MIN_VALUE
                else -> final.toInt()
            }
        }
    }

    //9. Palindrome Number
    fun isPalindrome(x: Int): Boolean {
        return when {
            x in 0..9 -> true
            x < 0 || (x - (x % 10)) % 10 != 0 -> false
            else -> {
                x.toString() == x.toString().reversed()
            }
        }
    }

    //20. Valid Parentheses
    fun isValidParentheses(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        for (c in s) {
            when (c) {
                '(', '{', '[' -> stack.add(c)
                ')' -> if (stack.isEmpty() || stack.removeLast() != '(') return false
                '}' -> if (stack.isEmpty() || stack.removeLast() != '{') return false
                ']' -> if (stack.isEmpty() || stack.removeLast() != '[') return false
            }
        }
        return stack.isEmpty()
    }

    //35. Search Insert Position
    fun searchInsert(nums: IntArray, target: Int): Int {
        var last = 0
        nums.mapIndexed { index, i ->
            if (i == target) {
                return index
            }
            if (i < target) {
                last = index
            }
            if (i > target) {
                return if (index - last == 1) index else if (index == 0) last else last + 1
            }
            if (index == nums.lastIndex) {
                return last + 1
            }
        }
        return 0
    }

    //35. Search Insert Position (BinarySearchの場合)
    fun searchInsertBinarySearch(nums: IntArray, target: Int): Int {
        val result = -nums.binarySearch(target) - 1
        return if (result < 0) {
            -result - 1
        } else {
            result
        }
    }
}
