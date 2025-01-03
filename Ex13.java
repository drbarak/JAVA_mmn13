/**
 * A class to store methods solving exercise 13
 *
 * @author (Zvika Barak)
 * @version (27.12.2024, 03.01.2025)
 */
public class Ex13
{
    /**
     * A method that accepts an array of whole unique numbers and returns it 
     * as a special array where the numbers are ordered in such a way that the
     * 1st number is greater than the 2nd which is less than the 3rd which
     * is greater than the 4th which is less than the 5th, and so on.
     *
     * @param    int[] arr - the array to arrange
     * @param    int med - the median of the original array
     * @return   int[] specialArr - the special array
     * 
     * Time complexity of 1+2+2+2+[2+n*(1+1+1+1+1)]+1+[2+n*(1+1+1+1+1)]+1=
     *          13+10n-> O(n): two non nested loop
     *          the actions in each loop is counted in the [] expressions
     * Space complexity of 1+n+1+(1)*2 = 4+n -> O(n)
     */
    public static int[] specialArr(int[] arr, int med)
    {
        if (arr.length < 1) return arr;
        // In my tester I wrote a method to callculate the median and verify 
        // that the input value is correct, but we are not required to submit
        // it, so in the method it is assumed the value is correct.
        int calcMed = med;  // not really needed but it is here just to show that the median was checked
        int[] a = new int[arr.length];
        int index=0;
        for (int i=0; i<arr.length;i++) // set larger numbers at even positions
        {
            if (arr[i] >= calcMed)
            {
                a[index] = arr[i];
                index+=2;
            }
        }
        index=1;
        for (int i=0; i<arr.length;i++) // set smaller numbers at odd positions
        {
            if (arr[i] < calcMed)
            {
                a[index] = arr[i];
                index+=2;
            }
        }
        return a;
    }// specialArr()

    /**
     * A method to find the first positive integer (>0) not included in the
     * array of unsorted integers (may have -ve numbers)
     * 
     * Example: the first number of the array {1,-3,6,2,0,15} is 3
     * 
     * @param    int[] arr - the array to find
     * @return   int number - the first -ve integer not included in the array
     * 
     * Time complexity of 2+2+[2+n*(1+1+2+3+1+2+1+3+3+1)]+2+1+
     *          [2+n*(1+1+2+2+1+1)]+1+[2+n*(1+1+2+2+1)]+2+
     *          [2+n*(1+1+2+1+1)]+2+1+1+1=6+18n+5+8n+3+7n+4+6n+5=
     *          18+39n -> O(n): 4 non nested loops
     *          note: the inner while loop reduces length so together with the
     *          outer loop it sums to no more than n operations and most of
     *          the time it is even less
     * Space complexity of 1+1+4*(1)+1+1+1+1=10 -> O(1)
     */
    public static int first(int[] arr)
    {
        int len = arr.length;
        // Step 1: Remove all -ve and 0 numbers and numbers larger than len
        // because the numbers that matter are from 1 to len only
        int temp = 0;
        // Time complexity of this 1st loop [2+n*(1+1+2+3+1+2+1+3+3+1)]
        for (int i=0; i<arr.length;i++)
        {
            if (arr[i] <= 0 || arr[i] > len)
            {
                while (len > 0 && (arr[len-1] <= 0 || arr[len-1] > len))
                    len--;
                if (len == 0) return 1;   // none left
                if (i >= len)
                    i = arr.length;
                else if (arr[len-1] > 0 && len > 1 && arr[len-1] <= len)
                {
                    swap(arr, i, len-1, temp);
                    len--;
                }
            }
        }
        // Step 2: Ignore duplicates
        int savLen = len;
        int index;
        // Time complexity of this 2nd loop [2+n*(1+1+2+2+1+1)]
        for (int i=0; i<savLen; i++)
        {
            index = Math.abs(arr[i]) - 1;
            if (index < len && arr[index] > 0) // not a duplicate and not larger than modified length
                arr[index] = -arr[index];
            else
            {
                len--;
                arr[i] = savLen + 1;
            }
        }
        if (len < savLen)   // Step 3: found duplicates or larger numbers so remove them
        {
            // Time complexity of this 3rd loop [2+n*(1+1+2+2+1)]
            for (int i=0; i<savLen; i++)    // remove numbers larger than new length after removing duplicates
            {
                arr[i] = Math.abs(arr[i]);
                if (arr[i] > len && arr[i] < savLen + 1)
                    len--;
            }
        }
        int sum = 0;    // Step 4: sum up all numbers left in the array
            // Time complexity of this 4th loop [2+n*(1+1+2+1+1)]
        for (int i=0; i<savLen; i++)
        {
            temp = Math.abs(arr[i]); // in case we marked as duplicate
            if (temp <= len)  // in case it is larger than new reduced length
                sum += temp;
        }
        int sumOfSerie = (1 + len) * len / 2;
        if (sum == sumOfSerie)
            return len + 1;
        return sumOfSerie - sum;   // sum must be -ve becuase there are numbers larger than len
    } // first()
    /**
     * A helper method to swap 2 numbers in an array of integers
     * 
     * @param    int[] arr - the array with the numbers
     * @param    int i, j - the 2 indices of the numbers to swap
     * @param    temp - a temporary variable to store the original value of one
     *                  of number to swap
     * 
     * Time complexity of 3 -> O(1): no loops
     * Space complexity of 0 -> O(0)
     */
    private static void swap(int[] arr, int i, int j, int temp)
    {
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }
    
    /**
     * A method that accepts an array of whole numbers and return the length of the
     * longest continous semi-palindrom in the array.
     * A serie of numbers is a semi-palindrom if by dropping one number, which is not at one
     * of the edges, the sub-series is a palindrom.
     * Example: {1,2,2,1} is a palindrom and {1,3,2,2,1} is a semi-palindrom becuase
     * dropping the "3" will result in a palindrom.
     * 
     * @param    int[] arr - the array to work on
     * @return   int - the longest semi-palindrom within the array
     */
    public static int longestNearlyPal(int[] arr)
    {
        return isSemiPalindrome(arr, true, 0, 0);
    }
    /**
     * A helper method to create a sub-array, based on parameters i, k, and check if the 
     * sub-array is semi-palindrom.
     * 
     * @param   int[] arr - the array to work on
     * @param   boolean internal - tells whether the pair we are checking to see if they are
     *                      equal, is internal to the array, or at the edge, because we are not
     *                      allowed to drop a number at the edge of the sub-array to see
     *                      if the reduced sub-array is a semi-palindrom
     * @param   int numsToRemove - the number of numbers to drop from the array to create the sub-arrays
     * @param   int index - the starting position within the array to create the sub-array
     * 
     * @return  length of longest semi-palindrom (since we start with then longest sub-array, 
     *              and reduce it's length as long as we do not find it to be semi-palindrom,
     *              then, the moment we find it to be semi-palindrom then it is the longest,
     *              because all other sub-array will be shorter.
     */
    private static int isSemiPalindrome(int[] arr, boolean internal, int numsToRemove, int index)
    {
        if (index == numsToRemove + 1) // finished to remove numsToRemove numbers
        {
            numsToRemove++; // new length to remove
            index = 0;      // start again from the left of the sub-array
        }
        int len2 = arr.length - numsToRemove; // the reduced length of the sub-array
        if (len2 < 2)           // down to array of length 0 or 1 --> not found
        {
            index = numsToRemove = arr.length;
            return len2;
        }
        int[] arr2 = new int[len2];
        arrayCopy(arr, index, arr2, 0, len2);
        if (isSemiPalindrome(arr2, true, 0))
        {
            index = numsToRemove = arr.length;  // found semi-palindrome
            return len2;
        }
        /**
         * If we reached here then the array/sub-array we are checking, is not semi-palindrome.
         * Therefore, reduce the array length by 1 and check if the two pssibleb sub-arrays
         * with that length (initially, dropping the first digit, and if it is not the 
         * semi-palindrome then dropping last digit).
         * If still did not find a semi-palindrom, reduce the array length by 2 and check all
         * sub-arrays of that length (drop 2 first numbers, then 1 from the begining and 1 from
         * the end, then 2 from the end), and continue reducing the length of the array by 1,
         * if still did not find a semi-palindrome, until left with sub-array of length 1, 
         * which is by definition a palindrom, which means it is also a semi-palindrome.
         * 
         * @param    int[] arr - the array to work on
         * @return   int - the longest semi-palindrom within the array
         */
        return isSemiPalindrome(arr, true, numsToRemove, index + 1);
    }
    /**
     * A helper method to check if the sub-array is semi-palindrom.
     * 
     * @param   int[] arr - the array to work on
     * @param   boolean internal - tells whether the pair we are checking to see if they are
     *                      equal, is internal to the array, or at the edge, because we are not
     *                      allowed to drop a number at the edge of the sub-array to see
     *                      if the reduced sub-array is a semi-palindrom
     * @param   int numsToRemove - the number of numbers to drop from the array to create the sub-arrays
     * @param   int index - the starting position within the array to create the sub-array
     * 
     * @return  true if it is semi-palindrom, else it returns false
     */
    private static boolean isSemiPalindrome(int[] arr, boolean internal, int i)
    {
        if (i == arr.length / 2) return true;   // finished checking all the pairs of this array
        // compare 2 digits - one from the left and the other from the right of the array,
        // the position of the digits is shifted by the index i
        if (arr[i] != arr[arr.length - 1 - i])
        {
            // remove one number, if the array is internal to the original and check again
            if (i > 0 && internal)
            {
                // This code checks for SemiPalindrome - if it is removed then only true 
                // Palindrome are looked for.
                // It drops the left number of the pair of numbers that are not equal and
                // checks again, then the right number of the pair and checks again
                int len2 = arr.length-2*i-1;
                if (len2 > 1)
                {
                    int[] arr2 = new int[len2];
                    arrayCopy(arr, i + 1, arr2, 0, len2);
                    boolean r = isSemiPalindrome(arr2, false, 0);
                    if (!r) // now drop the right digit of the pair and check again
                    {
                        arrayCopy(arr, i, arr2, 0, len2);
                        r = isSemiPalindrome(arr2, false, 0);
                    }
                    if (!r) return false;
                }
            }
            else
                return false;
        }
        return isSemiPalindrome(arr, internal, i+1); // this sub-array is not semi-palindrom
                                    // so go back and check more internal pair of digits
    }
    /**
     * A helper method to copy source array to a target array from position sourceIndex in the
     * source array to position targetIndex of the target array, for length of
     * numItemsTocopy items
     * 
     * @param    int[] source - the source array to copy from
     * @param    int sourceIndex - the starting position in the source array
     * @return   int[] target - the target array to copy to
     * @param    int targetIndex - the starting position in the target array
     * @param    int numItemsTocopy - number of items to copy
     */
    private static void arrayCopy(int[] source, int sourceIndex, int[] target, int targetIndex, int numItemsTocopy)
    {
       for (int i=sourceIndex; i<numItemsTocopy+sourceIndex ; i++)
           target[targetIndex++] = source[i];
    }
    /**
     * A method that accepts a 2-dimensional array (named mat[][]) of whole
     * +ve numbers and goes over all possible paths in this array, starting
     * from cell (0,0) to cell (mat.length-1, mat.length-1), such that one
     * can move from cell (i,j) to any of it's four neigbours by moving up,
     * down, left and right, but not diagonally.
     * The method finds the largest number for each path, and returns the 
     * smallest value of these numbers (all numbers in mat[][] are > 0).
     * 
     * @param    int[][] mat - the 2-dimensional array to work on
     * @return   int - the smallest number from the serie of the maximum 
     *                  numbers in each possible path
     */
    public static int extreme(int[][] mat)
    {
        return findPaths(mat.length-1, mat.length-1, -1, Integer.MAX_VALUE, mat);
    }
    /**
     * The recursive method that goes over all possible paths in the 
     * 2-dimensional array mat[][] and find the maximum number of each path
     * and returns the minimum number from that this serie.
     * 
     * @param    int x, y - the 2 indices of the cell in the matrix mat
     * @param    int max - the maximum number so far in the current path
     * @param    int min - the minimum between maximum numbers in all paths
     * @param    int[][] m - the 2-dimensional array to work on
     * 
     * @return   int - the smallest number from the serie of the maximum 
     *                  numbers in each possible path
     */
    private static int findPaths(int x, int y, int max, int min, int[][] m)
    {
        int mxy = m[x][y];
        if (min < mxy) return min; // no use to check this path because this 
                    // number is greater than the min that was already found
        if (mxy < 0) return min; // check if we visited this point already
        if (max < mxy) max = mxy;
        if (x == 0 && y == 0)
        { // update if less since we look for minimum between all the max(es)
            if (min > max) min = max;
            return min;
        }
        m[x][y] = -mxy; // marked as visited already
        int num;
        if (x > 0 && m[x-1][y]>0) // prevents left after right because it returns to prev point
        {
            num = findPaths(x - 1, y, max, min, m);    // up
            if (min > num) min = num;
        }
        if (x < m.length-1 && m[x+1][y]>0)
        {
            num = findPaths(x + 1, y, max, min, m);     // down
            if (min > num) min = num;
        }
        if (y > 0 && m[x][y-1]>0)
        {
            num = findPaths(x, y-1, max, min, m);    // left
            if (min > num) min = num;
        }
        if (y < m.length-1 && m[x][y+1]>0) 
        {
            num = findPaths(x, y+1, max, min, m);     // right
            if (min > num) min = num;
        }
        if (m[x][y] < 0) m[x][y] = -m[x][y]; // restore value
        return min;
    }
}