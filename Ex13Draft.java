
/**
 * Write a description of class Ex13 here.
 *
 * @author (Zvika Barak)
 * @version (13.11.2024)
 */
public class Ex13Draft
{
    static boolean p = false;
    static int count = 0;

    /**
     * A method to find the first positive integer (>0) not included in the
     * array of unsorted integers (may have -ve numbers)
     * 
     * Example: the first number of the array {1,-3,6,2,0,15} is 3
     */
    public static int firstLoop(int[] arr)
    {
        int len = arr.length;
        Print.p(arr);
        p = false;
        for (int i=0; i<arr.length;i++) // remove all -ve and 0 numbers
        {
            if (arr[i] <= 0)
            {
                if (p) Print.p("i="+i+", len="+len+", ", arr);
                while (len > 0 && arr[len-1] <= 0)
                    len--;
                if (len == 0) return 1;   // no +ve numbers
                if (p) Print.p(" len="+len+", ", arr);
                if (i >= len)
                    i = arr.length;
                else if (arr[len-1] > 0 && len > 1)
                {
                    MyLibrary.swap(arr, i, len-1);
                    len--;
                }
                if (p) Print.p(" len="+len+", ", arr);
            }
        }
        if (p) Print.p("1000, len="+len+", ", arr);
        int savLen = len;
        //p = true;
        for (int i=0; i<savLen; i++)    // remove all numbers larger than len
        {
            if (arr[i] > len)
            {
                if (p) Print.p("i="+i+", len="+len+", ", arr);
                while (len > 0 && arr[len-1] > len)
                    len--;
                if (len == 0) return 1;   // no number less then len
                if (p) Print.p(" len="+len+", ", arr);
                if (i >= len)
                    i = savLen;
                else if (arr[len-1] < len && len > 1)
                {
                    MyLibrary.swap(arr, i, len-1);
                    len--;
                }
                if (p) Print.p(" len="+len+", ", arr);
            }
        }
        //p = true;
        if (p) Print.p("2000, len="+len+", ", arr);
        /*
        int sum = (1 + len) * len / 2;  // sum of arthimetic serie
        //p = true;
        if (p) Print.p("3000, sum="+sum+", ", arr);

        for (int i=0; i<len;i++)
            sum -= arr[i];
        if (sum == 0)
            return len + 1;
        */
            // check if there are duplicates so we need to ignore them
        int sum;
        int index;
        savLen = len;
        //p = false;
        for (int i=0; i<savLen; i++)
        {
            index = Math.abs(arr[i]) - 1;
            if (p) Print.p(""+i+", "+index+", ", arr);
            if (arr[index] > 0) // not a duplicate
            {
                if (p) Print.p(i, index, arr[index], len);
                arr[index] = -arr[index];
            }
            else
            {
                len--;
                arr[i] = savLen + 1;
            }
        }
        if (p) Print.p(len, savLen);
        for (int i=0; i<savLen; i++)    // remove numbers larger than new length after removing duplicates
        {
            arr[i] = Math.abs(arr[i]);
            if (arr[i] > len && arr[i] < savLen + 1)
                len--;
        }
        if (p) Print.p("4000, len="+len+", ", arr);
        sum = 0;
        for (int i=0; i<savLen; i++)
        {
            if (arr[i] <= len)
                sum += arr[i];
        }
        if (p) Print.p("5000, len="+len+", sum="+sum+", ", arr);
        int sumOfSerie = (1 + len) * len / 2;
        if (p) Print.p(sum, sumOfSerie);
        if (sum == sumOfSerie)
            return len + 1;
        return sumOfSerie - sum;   // sum must be -ve becuase there are numbers larger than len
        /*
        p = true;
        int nextNum = 0;
        for (int i=0; i<len;i++)
        {
            if (arr[i] <= len && arr[i] > nextNum)
            {
                if (nextNum + 1 == arr[i])
                    nextNum++;
            }
            if (p) Print.p(i, nextNum, len, arr[i]);
        }
        return nextNum + 1;
        */
    }
    private static final int INVALID_NUM = -1;
    private static int nextNum;
    public static int firstRec(int[] arr)
    {
        int[] workArr = sortArr(arr);  // copy the original array so not to change it
        if (p) Print.p(workArr);
        nextNum = INVALID_NUM;
        firstNum(workArr, 0);
        return (nextNum == INVALID_NUM ? 1 : nextNum + 1);
    }
    private static boolean firstNum(int[] workArr, int i)
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
    /**
     * A method - accepts an array of whole numbers and return it as a special array
     *
     * @param    int[] arr - the array to arrange
     * @param    int med - the median of the array
     * @return   int[] specialArr - the special array
     */
    public static int[] specialArr(int[] arr, int med)
    {
        if (arr.length < 1) return arr;
        count = 0;
        //if (true) return specialArrNoSort(arr, med);
        /*
        specialArrNoSort(arr, med);
        */
        count = 0;
        int[] specialArr = sortArr(arr);  // copy and sort the original array so not to change it
        Print.p("Selection sort count=" + count);
        // find the median and compare to input parameters
        int calcMed;
        calcMed = specialArr[arr.length / 2];
        p = true;
        if (p) Print.p("Special array: median is " + calcMed + ", ", specialArr);
        if (med != calcMed)
            if (p) Print.p("Provided med = " + med + " is wrong, using calcMed = " + calcMed);
        special(specialArr, 0, arr.length / 2);
        Print.p("Selection sort + special count=" + count);
Print.p("array: ", arr);
        p = false;
        int[] a = new int[arr.length];
        int index=0;
        count = 0;
        for (int i=0; i<arr.length;i++) // set larger numbers
        {
            count++;
            if (p) Print.p("" + (arr[i] > calcMed) + "," + arr[i]+ ","+calcMed);
            if (arr[i] >= calcMed)
            {
                a[index] = arr[i];
                index += 2;
            }
            if (p) Print.p("i="+i+",array1: ", a);
        }
        index=1;
        for (int i=0; i<arr.length;i++) // set smaller numbers at odd positions
        {
            count++;
            if (p) Print.p("" + (arr[i] > calcMed) + "," + arr[i]+ ","+calcMed);
            if (arr[i] < calcMed)
            {
                a[index] = arr[i];
                index += 2;
            }
            if (p) Print.p("i="+i+",array2: ", a);
        }
        Print.p("O(2n) count=" + count);
        if (true) return a;
        return specialArr;
    }
    private static void special(int[] specialArr, int i, int medianIndex)
    {
        count++;
        if (i < specialArr.length)
        {
            MyLibrary.swap(specialArr, i, (i/2 + medianIndex));
            i += 2;
            special(specialArr, i, medianIndex);
        }
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
            //count++;
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                count++;
                if (workArr[j] < workArr[minIndex])
                    minIndex = j;
            }
            MyLibrary.swap(workArr, i, minIndex);
            /*
            int temp = workArr[i];
            workArr[i] = workArr[minIndex];
            workArr[minIndex] = temp; 
            */
        }
        return workArr;
    }
    /********************************************************************
     * helper methods for earlier versions of specialArr()
     */
    private static int addToList(int len,int[] a, int newNum, int count, int maxIndexLess, boolean addToMore, boolean endOfList)
    {
        if (p) Print.p("in addToList: "+addToMore+", " +endOfList);
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
                if (p) Print.p("in addToList0: "+a[newPos - 1]+", " +newNum+", " +newPos);
                if (newPos-1 == maxIndexLess)
                    maxIndexLess = newPos;
                a[newPos] = a[newPos - 1];
                newPos--;
            }
            else if (newNum > a[maxIndexLess])
                maxIndexLess = newPos;
            a[newPos] = newNum;
            if (p) Print.p("in addToList1: "+maxIndexLess+", " +newNum+", " +newPos);
        }
        return maxIndexLess;
    }
    private static int calcMedian(int[] org,int[] a)
    {
        //p = true;
        int median = org[0];
        int countLess = 0, countMore = 0;
        int maxIndexLess = 0;  // init values
        for (int i=1; i<org.length; i++)
        {
            count++;
            int curr = org[i];
            int maxCountLess = (i+1)/2;
            int maxCountMore = ((i+1) % 2 == 1 ? maxCountLess : maxCountLess - 1);
            if (p) Print.p("i=" + i + ",median="+median+ ",curr="+curr);
            if (p) Print.p("100, countLess="+countLess+",maxCountLess="+maxCountLess+
                ",countMore="+countMore+",maxCountMore=" + maxCountMore);
            if (p) Print.p("200, maxIndexLess="+maxIndexLess);
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
                    if (a[maxIndexLess] > curr)
                    {   // if found replace it in the Less list and use it as a median
                        // and add the median to the More list
                        maxIndexLess = addToList(org.length, a, median, countMore, maxIndexLess, true, false);
                        countMore++;
                        found = true;
                        if (p) Print.p("found");
                        median = a[maxIndexLess];
                        a[maxIndexLess] = curr;
                        maxIndexLess = 0;
                        for (int k=0;k<countLess;k++)
                        {
                            count++;
                            if (a[k] > a[maxIndexLess])
                              maxIndexLess = k;
                        }
                    }
                    if (!found)
                    {
                        if (p) Print.p("not found,"+maxIndex);
                        //a[org.length - countMore - 1] = median;
                        maxIndexLess = addToList(org.length, a, median, countMore, maxIndexLess, true, false);
                        countMore++;
                        median = curr;
                    }
                }
                else
                {
                    maxIndexLess = addToList(org.length, a, curr, countLess, maxIndexLess, false, false);
                    countLess++;
                }
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
                            maxIndexLess = addToList(org.length, a, median, countLess, maxIndexLess, false, false);
                            countLess++;
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
                        maxIndexLess = addToList(org.length, a, median, countLess, maxIndexLess, false, false);
                        countLess++;
                        if (countMore > 0)
                        {
                            median = a[minIndex];
                            maxIndexLess = addToList(org.length, a, curr, minIndex, maxIndexLess, true, true);
                        }
                        else
                            median = curr;
                    }
                }
                else
                {
                    maxIndexLess = addToList(org.length, a, curr, countMore, maxIndexLess, true, false);
                    countMore++;
                }
            }
            if (p) Print.p("i=" + i + ",median="+median+ ", ", a);
            if (p) Print.p("countLess="+countLess+",maxCountLess="+maxCountLess+
                ",countMore="+countMore+",maxCountMore=" + maxCountMore);
            if (p) Print.p("maxIndexLess="+maxIndexLess);
            //if (i == 6) break;
        }
        a[countLess] = median;
        return median;
    }//calcMedian
    public static int[] specialArrNoSort(int[] arr, int med)
    {
        count = 0;
        if (arr.length < 1) return arr;
        p = true;
        if (p) Print.p("org array: length=" + arr.length + ", ", arr);
        int[] a = new int [arr.length];
        p = false;
        int calcMed = calcMedian(arr, a);
        p = true;
        if (p) Print.p("Calculated median is "+calcMed + ", ", a);
        Print.p("count calcMed=" + count);
        p = false;
        if (p) Print.p(a);
        int medianIndex = a.length / 2;
        special(a, 0, medianIndex);
        Print.p("count after preparing special recursively without extra array=" + count);
        if (true) return a;
        //p = true;
        for (int i=0;i<a.length-1;i+=2)
        {
            count++;
            if (p) Print.p("i="+i+", a[i]="+a[i]+", medPos="+medianIndex+",", a);
            MyLibrary.swap(a, i, (i/2 + medianIndex));
            if (p) Print.p("i=" + i + ",", a);
        }
        Print.p("count after preparing special without extra array=" + count);
        return a;
    }
}