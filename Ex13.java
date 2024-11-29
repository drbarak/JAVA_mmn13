
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
     * @return   int[] specialArr - the special array
     */
    public static int[] specialArr(int[] arr, int med)
    {
        Print.p("org array: length=" + arr.length + ", "+ Arrays.toString(arr));
        int[] workArr = sortArr(arr);  // copy and sort the original array so not to change it
        // find the median and compare to input parameters
        int calcMed;
        calcMed = workArr[arr.length / 2];
        Print.p("Special array: median is " + calcMed + ", " + Arrays.toString(workArr));        
        if (med != calcMed)
            Print.p("Provided med = " + med + " is wrong, using calcMed = " + calcMed);
        int[] specialArr = new int[arr.length];
        specialArr[0] = calcMed;
        special(specialArr, workArr, 1, 1);
        return specialArr;
    }

    private static void special(int [] specialArr, int [] workArr, int i, int index)
    {
        if (i == workArr.length) return;
        if (i % 2 == 1) // index odd have a number smaller than its' neighbours
            specialArr[i] = workArr[index - 1]; 
        else
            specialArr[i] = workArr[workArr.length - index++]; 
        special(specialArr, workArr, ++i, index);
    }
    private static final int INVALID_NUM = -1;
    private static int nextNum;
    public static int first(int [] arr)
    {
        int[] workArr = sortArr(arr);  // copy the original array so not to change it
        if (p) Print.p(workArr);
        nextNum = INVALID_NUM;
        firstNum(workArr, 0);
        return (nextNum == INVALID_NUM ? 1 : nextNum + 1);
    }

    private static boolean firstNum(int [] workArr, int i)
    {
        if (i == workArr.length) return true;
        int num = workArr[i];
        if (p) Print.p(num, i, nextNum);
        if (num < 1) return firstNum(workArr, ++i);  // skip 0 and -ve numbers
        if (nextNum < num)
        {
            if (nextNum == INVALID_NUM)
            {
                if (num > 1)
                    return firstNum(workArr, workArr.length);
            }
            else if (num - nextNum > 1)
                return firstNum(workArr, workArr.length);
            nextNum = num;
        }
        return firstNum(workArr, ++i);
    }
    static boolean p = false;
    public static int longestNearlyPal(int[] arr)
    {
        // sub arrays from pos i to end
        if (p) Print.p(arr);
        return _isSemiPalindrom(arr, true, 0, 0);
        /*      
        for (int i = 0; i < arr.length; i++)
        {
        int[] arr2 = new int[arr.length - i];
        // sub-sub arrays from pos 0 to end-k
        for (int k = 0; k < arr.length - arr2.length + 1; k++)
        {
        // arraycopy(source_arr, int sourcePos, dest_arr, int destPos, int len)
        System.arraycopy(arr, k, arr2, 0, arr2.length);
        //for (int j = 0; j < arr2.length; j++) arr2[j] = arr[j + k];
        Print.p(i, k);
        Print.p(arr2);
        boolean r = _isSemiPalindrom(arr2, true);
        if (false && r)
        {
        if(p) Print.p(k);
        return arr2.length;
        }
        }
        }
        return 0;
         */
    }

    private static int _isSemiPalindrom(int[] arr, boolean internal, int i, int kk)
    {
        if (kk == i + 1)
        {
            i++;
            kk = 0;
        }
        if (i == arr.length) return 0;
        if (p) Print.p(i, kk);
        int[] arr2 = new int[arr.length - i];
        System.arraycopy(arr, kk, arr2, 0, arr2.length);
        if (p) Print.p(arr2);
        boolean rr = _isSemiPalindrom(arr2, true);
        if (p) Print.p(rr);
        if (rr)
        {
            if (p) Print.p(arr2.length);
            kk = arr.length;
            i = arr.length;
            return arr2.length;
        }
        if (true) return _isSemiPalindrom(arr, true, i, ++kk);
        for (int k = 0; k < arr.length - arr2.length + 1; k++)
        {
            /*
             * arraycopy(source_arr, int sourcePos,
            dest_arr, int destPos, int len)
             */
            System.arraycopy(arr, k, arr2, 0, arr2.length);
            /*
            for (int j = 0; j < arr2.length; j++)
            arr2[j] = arr[j + k];
             */
            Print.p(i, k);
            Print.p(arr2);
            boolean r = _isSemiPalindrom(arr2, true);
            if (false && r)
            {
                if(p) Print.p(k);
                //return arr2.length;
            }
        }
        return -3;
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
                    System.arraycopy(arr, i + 1, arr2, 0, arr2.length);
                    /*
                    for (int j = 0; j < arr2.length; j++)
                    arr2[j] = arr[i + j + 1];
                    Print.p(arr2);
                     */
                    boolean r = _isSemiPalindrom(arr2, false);
                    if (!r) // now drop the right digit of the pair and check again
                    {
                        System.arraycopy(arr, i, arr2, 0, arr2.length);
                        /*
                        for (int j = 0; j < arr2.length; j++)
                        arr2[j] = arr[i + j];
                         */
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
    private static int minMax = Integer.MAX_VALUE;
    private static int count = 0;
    public static int extreme(int[][] mat, boolean _p)
    {
        p = _p;
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
        Arrays.sort(workArr);
        // sort an array of integers - using 'selection sort'
        /*
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
         */
        return workArr;
    }
}
