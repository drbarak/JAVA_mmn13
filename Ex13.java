
/**
 * Write a description of class Ex13 here.
 *
 * @author (Zvika Barak)
 * @version (13.11.2024)
 */
import java.util.Arrays;

public class Ex13
{
    /**
     * A method - accepts an array of whole numbers and return it as a special arry
     *
     * @param    int[] arr - the array to arrange
     * @param    int med - the median of the array
     * @return   int[] specialArr - the special arry
     */
    public static int[] specialArr(int[] arr, int med)
    {
        p("org array: length=" + arr.length + ", "+ Arrays.toString(arr));
        int[] workArr = sortArr(arr);  // copy the original array so not to change it
        // find the median and compare to input parameters
        int calcMed;
        calcMed = workArr[arr.length / 2];
        p("Special array: median is " + calcMed + ", " + Arrays.toString(workArr));        
        if (med != calcMed)
            p("Provided med = " + med + " is wrong, using calcMed = " + calcMed);
        int[] specialArr = new int[arr.length];
        specialArr[0] = calcMed;
        int index = 1;
        for (int i = 1; i < arr.length; i++)
        {
            if (i % 2 == 1) // index odd have a number smaller than its' neighbours
                specialArr[i] = workArr[index - 1]; 
            else
                specialArr[i] = workArr[arr.length - index++]; 
        }
        return specialArr;
    }
    public static int first(int [] arr)
    {
        final int INVALID_NUM = -1;
        int[] workArr = sortArr(arr);  // copy the original array so not to change it
        int nextNum = INVALID_NUM;
        for (int num: workArr)
        {
            if (num < 1) continue;
            if (nextNum < num)
            {
                if (nextNum == INVALID_NUM)
                {
                    if (num > 1)
                        return 1;
                }
                else if (num - nextNum > 1)
                    return nextNum + 1;
                nextNum = num;
            }
        }
       return (nextNum == INVALID_NUM ? 1 : nextNum + 1);
    }
    public static int longestNearlyPal(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int[] arr2 = new int[arr.length - i];
            for (int k = 0; k < arr.length - arr2.length + 1; k++)
            {
                for (int j = 0; j < arr2.length; j++)
                    arr2[j] = arr[j + k];
                boolean r = isSemiPalindrom(arr2);
                if (r)
                    return arr2.length;
            }
        }
        return 0;
    }
    private static boolean isSemiPalindrom(int[] arr)
    {
        // call the method flaging that a number at the edge can not be dropped
        return _isSemiPalindrom(arr, true);
    }
    private static boolean _isSemiPalindrom(int[] arr, boolean internal)
    {
        if (arr.length < 2) return true;    // a single number is Palindrom so no need to check
        // loop over all possibles sub-arrays of original array
        for (int i = 0; i < arr.length / 2; i++)
        {
            // compare 2 digits - one from the left and the other from the right of the array
            // the position of the digits is shifted by the index i
            if (arr[i] != arr[arr.length - 1 - i])
            {
                // remove one number, if the array  is internal to the original and check again
                if (i > 0 && internal)
                {
                    // drop the left number of the pair of digits that are not equal and check again
                    int[] arr2 = new int[arr.length-2*i-1];
                    for (int j = 0; j < arr2.length; j++)
                        arr2[j] = arr[i + j + 1];
                    boolean r = _isSemiPalindrom(arr2, false);
                    if (!r) // now drop the right digit of the pair and check again
                    {
                        for (int j = 0; j < arr2.length; j++)
                            arr2[j] = arr[i + j];
                        r = _isSemiPalindrom(arr2, false);
                    }
                    if (!r) return false;
                }
                else
                    return false;
            }
        }
        return true;
    }
    
    public static int extreme(int[][] mat)
    {
        int n = mat.length;
        //p(n + " " + mat[0].length + ", " + mat[1].length);
        return 0;
    }

            // sort an array of integers - using 'selection sort'
    private static int[] sortArr(int[] arr)
    {
        int[] workArr = arr.clone();  // copy the original array so not to change it
        for (int i = 0; i < arr.length; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                if (workArr[j] < workArr[minIndex])
                    minIndex = j;
            }
            int temp = workArr[i];
            workArr[i] = workArr[minIndex];
            workArr[minIndex] = temp; 
        }
        return workArr;
    }
    private static void p(String s)
    {
        System.out.println(s);
    }
    private static void p(int n)
    {
        p("" + n);
    }
}
