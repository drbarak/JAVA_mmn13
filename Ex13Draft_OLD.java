
/**
 * Write a description of class Ex13 here.
 *
 * @author (Zvika Barak)
 * @version (13.11.2024)
 */
import java.util.Arrays;
public class Ex13Draft
{
    /**
     * A method - accepts an array of whole numbers and return it as a special array
     *
     * @param    int[] arr - the array to arrange
     * @param    int med - the median of the array
     * @return   int[] specialArr - the special array
     */
    static boolean p = false;
    static int count = 0;
    private static int addToList(int len,int[] a, int newNum, int count, boolean addToMore, boolean endOfList)
    {
        int newPos = (addToMore && !endOfList ? len - count - 1 : count);
        if (addToMore)
        {
            if (newPos < len - 1 && newNum > a[newPos + 1])
            {
                a[newPos] = a[newPos + 1];
                newPos++;
            }
            a[newPos] = newNum;
        }
        else
        {
            if (newPos > 0 && newNum < a[newPos - 1])
            {
                a[newPos] = a[newPos - 1];
                newPos--;
            }
            a[newPos] = newNum;
        }
        return ++count;
    }
    private static int calcMedian(int[] org,int[] a)
    {
        //p = true;
        int median = org[0];
        int countLess = 0, countMore = 0;
        for (int i=1; i<org.length; i++)
        {
            count++;
            int curr = org[i];
            int maxCountLess = (i+1)/2;
            int maxCountMore = ((i+1) % 2 == 1 ? maxCountLess : maxCountLess - 1);
            if (p) Print.p("i=" + i + ",median="+median+ ",curr="+curr);
            if (p) Print.p("100, countLess="+countLess+",maxCountLess="+maxCountLess+
                ",countMore="+countMore+",maxCountMore=" + maxCountMore);
            if (curr < median)
            {
                if (p) Print.p("500, curr < median");
                if (countLess == maxCountLess) // can't add to Less list
                {
                    boolean found = false;
                    int maxIndex = countLess;
                    int newPos;
                    if (p) Print.p(1000, curr, median, maxIndex-1);
                    // find if in the Less list a number larger than curr then 
                    // median is added to the Less list and curr become the median
                    for (int j=countLess;j>0;j--)
                    {   // if found replace it in the Less list and use it as a median
                        // and add the median to the More list
                        count++;
                        if (a[j-1] > curr)
                        {
                            countMore = addToList(org.length, a, median, countMore, true, false);
                            median = a[j-1];
                            a[j-1] = curr;
                            found = true;
                            if (p) Print.p("found");
                            break;
                        }
                        else if (a[maxIndex-1] < a[j-1])
                            maxIndex = j-1;
                    }
                    if (!found)
                    {
                        if (p) Print.p("not found,"+maxIndex);
                        //a[org.length - countMore - 1] = median;
                        countMore = addToList(org.length, a, median, countMore, true, false);
                        median = curr;
                    }
                }
                else
                    countLess = addToList(org.length, a, curr, countLess, false, false);
            }
            else
            {
                if (countMore == maxCountMore) // can't add to Larger list
                {
                    boolean found = false;
                    int minIndex = org.length - countMore;
                    if (p) Print.p(2000, curr, median, minIndex);
                    // find if in the More list a number less than curr then 
                    // median is added to the Less list and curr become the median
                    for (int j=countMore;j>0;j--)
                    {   // if found, add median it to the Less list and curr becomes the median
                        count++;
                        if (a[org.length - j] > curr)
                        {
                            countLess = addToList(org.length, a, median, countLess, false, false);
                            median = curr;
                            found = true;
                            if (p) Print.p("found");
                            break;
                        }
                        else if (a[minIndex] > a[org.length - j])
                            minIndex = org.length - j;
                    }
                    if (!found)
                    {
                        // not found so replace curr with the minimum position in the
                        // More list, add median to the Less list and the minimum as median
                        if (p) Print.p("not found,"+minIndex);
                        countLess = addToList(org.length, a, median, countLess, false, false);
                        if (countMore > 0)
                        {
                            median = a[minIndex];
                            addToList(org.length, a, curr, minIndex, true, true);
                        }
                        else
                            median = curr;
                    }
                }
                else
                    countMore = addToList(org.length, a, curr, countMore, true, false);
            }
            if (p) Print.p("i=" + i + ",median="+median+ ", "+ Arrays.toString(a));
            if (p) Print.p("countLess="+countLess+",maxCountLess="+maxCountLess+
                ",countMore="+countMore+",maxCountMore=" + maxCountMore);
            //if (i == 6) break;
        }
        a[countLess] = median;
        return median;
    }//calcMedian
    public static int[] specialArrNoSort(int[] arr, int med)
    {
        if (arr.length < 1) return arr;
        count = 0;
        p = true;
        if (p) Print.p("org array: length=" + arr.length + ", "+ Arrays.toString(arr));
        int[] a = new int [arr.length];
        p = false;
        int calcMed = calcMedian(arr, a);
        p = true;
        if (p) Print.p("Calculated median is "+calcMed + ", "+ Arrays.toString(a));
        Print.p("count calcMed=" + count);
        int[] specialArr = new int[arr.length];
        specialArr[0] = calcMed;
        special(specialArr, a, 1, 1);
        Print.p("count=" + count);
        if (p) Print.p("Result from Special()=", specialArr);
        p = false;
        if (p) Print.p(Arrays.toString(a));
        if (p) Print.p(a[a.length / 2]);        // not allowed to use another array
        int medianIndex = a.length / 2;
        int last = a.length - 1;
        swap(a, 0, medianIndex);
        //p = true;
        boolean swapWithLast = false;
        for (int i=1;i<a.length-1;i++)
        {
            if (p) Print.p("i=" + i + "," +Arrays.toString(a));
            if (i < medianIndex)
            {
                if (i % 2 == 1)
                {
                    if (last<a.length - 1 && a[medianIndex] > a[a.length - 1])
                        swap(a, i, a.length - 1);
                    else
                        swap(a, i, medianIndex);
                }
                else
                    swap(a, i, last--);
            }
            else
            {
                if (p) Print.p("pass medianIndex, odd=" + (i % 2 == 1));
                if (p) Print.p(a.length-last-1, last-i+1, last, i);
                if (!swapWithLast && ((a.length-last-1 == last-i+1) || 
                        (a[a.length-1]>a[0]))) //a[0] is
                {
                    swapWithLast = true;
                    last = a.length - (a[a.length-1]>a[0] ? 2 : 1);
                }
                if (p) Print.p(""+swapWithLast);
                if (i % 2 == 1) // odd numbers should be less than median
                {
                    if (swapWithLast && i < last)
                    {
                        if (a[last] < a[i])
                            swap(a, i, last--);
                    }
                    else
                        swap(a, i, a.length - 1);
                }
                else
                {
                    if (a[i] < a[last])
                        swap(a, i, last--);
                    else if (i >= last)
                        swap(a, i, a.length - 1);
                }
            }
            if (p) Print.p("i=" + i + "," +Arrays.toString(a));
        }
        // take care of end of array, if needed
        if (a.length % 2 == 0 && a[a.length - 1] > a[a.length - 2])
            swap(a, a.length - 1, a.length - 2);
        return a;
    }
    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }
    public static int[] specialArr(int[] arr, int med)
    {
        if (arr.length < 1) return arr;
        count = 0;
        if (!true)
            return specialArrNoSort(arr, med);
        count = 0;
        int[] workArr = sortArr(arr);  // copy and sort the original array so not to change it
        Print.p("Selection sort count=" + count);
        // find the median and compare to input parameters
        int calcMed;
        calcMed = workArr[arr.length / 2];
        if (p) Print.p("Special array: median is " + calcMed + ", " + Arrays.toString(workArr));        
        if (med != calcMed)
            if (p) Print.p("Provided med = " + med + " is wrong, using calcMed = " + calcMed);
        int[] specialArr = new int[arr.length];
        specialArr[0] = calcMed;
        special(specialArr, workArr, 1, 1);
        Print.p("count=" + count);
        return specialArr;
    }
    private static void special(int [] specialArr, int [] workArr, int i, int index)
    {
        count++;
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
        //Arrays.sort(workArr);
        // sort an array of integers - using 'selection sort'
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
            swap(workArr, i, minIndex);
            /*
            int temp = workArr[i];
            workArr[i] = workArr[minIndex];
            workArr[minIndex] = temp; 
            */
        }
        return workArr;
    }
}