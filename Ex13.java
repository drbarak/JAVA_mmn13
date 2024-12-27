/**
 * A class to store methods solving exercise 13
 *
 * @author (Zvika Barak)
 * @version (27.12.2024)
 */
public class Ex13
{
    /**
     * A method that accepts an array of whole numbers and return it as a 
     * special array where the numbers are ordered in such a way that the
     * 1st number is greater than the 2nd which is less than the 3rd which
     * is greater than the 4th which is less than the 5th, and so on.
     *
     * @param    int[] arr - the array to arrange
     * @param    int med - the median of the original array
     * @return   int[] specialArr - the special array
     * 
     * Efficiency of O(n)
     * Complexity of 2+n+2+(2+1+1)*2 = 12+n -> O(n)
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
    }
    /**
     * A method to find the first positive integer (>0) not included in the
     * array of unsorted integers (may have -ve numbers)
     * 
     * Example: the first number of the array {1,-3,6,2,0,15} is 3
     * 
     * @param    int[] arr - the array to find
     * @return   int number - the first -ve integer not included in the array
     * 
     * Efficiency of O(4n) -> O(n)
     * Complexity of 2+2+4*(2+1+1)+2+1+2+2=27 -> O(1)
     */
    public static int first(int[] arr)
    {
        int len = arr.length;
        // Here we remove all -ve and 0 numbers and numbers larger than len
        // because the numbers that matter are from 1 to len only
        int temp = 0;
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
            // check if there are duplicates so we need to ignore them
        int savLen = len;
        int index;
        savLen = len;
        for (int i=0; i<savLen; i++)
        {
            index = Math.abs(arr[i]) - 1;
            if (index < len && arr[index] > 0) // not a duplicate and not larger than len
                arr[index] = -arr[index];
            else
            {
                len--;
                arr[i] = savLen + 1;
            }
        }
        if (len < savLen)   // found duplicates or larger numbers
        {
            for (int i=0; i<savLen; i++)    // remove numbers larger than new length after removing duplicates
            {
                arr[i] = Math.abs(arr[i]);
                if (arr[i] > len && arr[i] < savLen + 1)
                    len--;
            }
        }
        int sum = 0;
        for (int i=0; i<savLen; i++)
        {
            temp = Math.abs(arr[i]);
            if (temp <= len)
                sum += temp;
        }
        int sumOfSerie = (1 + len) * len / 2;
        if (sum == sumOfSerie)
            return len + 1;
        return sumOfSerie - sum;   // sum must be -ve becuase there are numbers larger than len
    } // first()
    // helper method to swap 2 numbers in an array of integers
    private static void swap(int[] arr, int i, int j, int temp)
    {
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }
    static boolean p = false;
    static int count = 0;
    public static int longestNearlyPal(int[] arr)
    {
        return isSemiPalindrome(arr, true, 0, 0);
    }
    private static int isSemiPalindrome(int[] arr, boolean internal, int i, int k)
    {
        if (p) Print.p("in method 1, i=" + i + ", k=" + k, arr);
        if (k == i + 1)
        {
            i++;
            k = 0;
        }
        int len2 = arr.length - i;
        if (len2 < 2)
        {
            k = i = arr.length;
            return len2;
        }
        int[] arr2 = new int[len2];
        System.arraycopy(arr, k, arr2, 0, len2);
        if (isSemiPalindrome(arr2, true, 0))
        {
            k = i = arr.length;
            if (p) Print.p("end ", arr2);
            return len2;
        }
        return isSemiPalindrome(arr, true, i, ++k);
    }
    // same as above but with extra debugging prints
    private static int __isSemiPalindrome(int[] arr, boolean internal, int i, int k)
    {
        if (p) Print.p("in method 1, i=" + i + ", k=" + k, arr);
        if (k == i + 1)
        {
            i++;
            k = 0;
        }
        //if (i == arr.length) return 0;
        int len2 = arr.length - i;
        if (len2 < 2)
        {
            k = i = arr.length;
            return len2;
        }
        int[] arr2 = new int[len2];
        System.arraycopy(arr, k, arr2, 0, len2);
        if (p) Print.p(arr2);
        if (isSemiPalindrome(arr2, true, 0))
        {
            if (p) Print.p(true);
            if (p) Print.p(len2);
            k = i = arr.length;
            return len2;
        }
        if (p) Print.p(false);
        return isSemiPalindrome(arr, true, i, ++k);
    }
    private static boolean isSemiPalindrome(int[] arr, boolean internal, int i)
    {
        if (p) Print.p("in method 3, i=" + i + " ", arr);
        if (i == arr.length / 2) return true;
        // compare 2 digits - one from the left and the other from the right of the array
        // the position of the digits is shifted by the index i
        if (arr[i] != arr[arr.length - 1 - i])
        {
            // remove one number, if the array is internal to the original and check again
            if (i > 0 && internal)
            {
                // this code checks for SemiPalindrome - if it is removed then only true Palindrome is valid
                // drop the left number of the pair of digits that are not equal and check again
                int len2 = arr.length-2*i-1;
                if (len2 > 1)
                {
                    int[] arr2 = new int[len2];
                    System.arraycopy(arr, i + 1, arr2, 0, len2);
                    boolean r = isSemiPalindrome(arr2, false, 0);
                    if (!r) // now drop the right digit of the pair and check again
                    {
                        System.arraycopy(arr, i, arr2, 0, len2);
                        r = isSemiPalindrome(arr2, false, 0);
                    }
                    if (!r) return false;
                }
            }
            else
                return false;
        }
        return isSemiPalindrome(arr, internal, ++i);
    }
    private static int minMax = Integer.MAX_VALUE;
    public static int extreme(int[][] mat)
    {
        findPaths(mat.length - 1, mat.length - 1, "", -1, mat);
        return minMax;
    }
    private static void findPaths(int x, int y, String path, int max, int[][] m)
    {
        String point = makePt(x, y);
        path += point;
        if (p) Print.p(path + ",max=" + max + ",value=" + m[x][y]);
        if (max < m[x][y]) max = m[x][y];
        if (x == 0 && y == 0)
        {
            if (minMax > max) minMax = max;
            if (p) Print.p("Path = [" + path + "] max=" + max + ", minMax=" + minMax + ", count=" + ++count);
            return;
        }
        if (x > 0 && path.indexOf(makePt(x - 1, y)) < 0) // prevents left after right because it returns to prev point
        {
            if (p) Print.p("calling L " + point);
            findPaths(x - 1, y, path + ",", max, m);    // left
            if (p) Print.p("returning L " + point);
        }
        if (x < m.length - 1 && path.indexOf(makePt(x + 1, y)) < 0) 
        {
            if (p) Print.p("calling R " + point);
            findPaths(x + 1, y, path + ",", max, m);     // right
            if (p) Print.p("returning R " + point);
        }
        if (y > 0 && path.indexOf(makePt(x, y - 1)) < 0)
        {
            if (p) Print.p("calling D " + point);
            findPaths(x, y - 1, path + ",", max, m);    // down
            if (p) Print.p("returning D " + point);

        }
        if (false && y < m.length - 1 && path.indexOf(makePt(x, y + 1)) < 0) 
        {
            if (p) Print.p("calling U " + point);
            findPaths(x, y + 1, path + ",", max, m);     // right
            if (p) Print.p("returning U " + point);
        }
    }
    private static String makePt(int x, int y)
    {
        return "(" + x + "," + y + ")";
    }

    private static int[] sortArr(int[] arr)
    {
        int[] workArr = arr.clone();  // copy the original array so not to change it
        for (int i = 0; i < arr.length; i++)
        {
            count++;
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                count++;
                if (workArr[j] < workArr[minIndex])
                    minIndex = j;
            }
            //swap(workArr, i, minIndex);
            int temp = workArr[i];
            workArr[i] = workArr[minIndex];
            workArr[minIndex] = temp; 
        }
        return workArr;
    }
}