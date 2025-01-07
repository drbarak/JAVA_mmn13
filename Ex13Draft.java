public class Ex13Draft
{
    static boolean p = false;
    static int count = 0;

    /**
     * WAITING FOR AN ANSWER REGARDING ABILITY TO USE A TEMP ARRAY TO STORE
     * GLOBAL VAR
     * Answer - no temp array
     */
    /**
     * A method that accepts a 2-dimensional array (named mat[][]) of whole
     * +ve numbers and goes over all possible paths in this array, starting
     * from cell (0,0) to cell (mat.length-1, mat.length-1), such that one
     * can move from cell (i,j) to any of it's four neigbours by moving up,
     * down, left and right, but not diagonally.
     * The method finds the largest number for each path, and returns the 
     * smallest value of these numbers (all numbers in mat[][] are > 0).
     * 
     */
    public static int extreme(int[][] mat)
    {
        //int[] minMax = new int []{Integer.MAX_VALUE};  // to store the min sim 
        if (mat.length < 2) return Integer.MIN_VALUE; // must be at least size 2x2
        for (int i=0; i<mat.length;i++) // check matrix is square
            if (mat[i].length != mat.length) 
                return Integer.MIN_VALUE;   // invalid: return min to know what is the problem
        boolean neg = false;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<mat.length;i++) // find min, max and if there -ve numbers
        {
            for (int j=0; j<mat.length;j++)
            {
                if (mat[i][j] <= 0) neg = true;
                if (mat[i][j] > max) max = mat[i][j];
                if (mat[i][j] < min) min = mat[i][j];
            }
        }
        int shift = 0;
        count = 0;
        if (neg)    // found -ve member so min < 0
        {
            if (max - min < Integer.MAX_VALUE)
            {
                shift = 1 - min;    // shift mat so all +ve
                for (int i=0; i<mat.length;i++)
                    for (int j=0; j<mat.length;j++)
                        mat[i][j] += shift;
            }
            else
                return Integer.MAX_VALUE;   // invalid: return max to know what the problem
        }
        /*
        int mat00 = mat[0][0];  // we are not allowed to create an extra array or global variable
        //mat[0][0] = Integer.MAX_VALUE;
        String[] minPath = new String[]{""};
        findPaths(0, 0, "", -1, mat, mat00, minPath);
        Print.p(minPath[0]);
        if (true) return -1;
        //p = true;
        int min = findPaths(0, 0, -1, Integer.MAX_VALUE, mat, mat00);
        Print.p(mat);
        int result = mat[0][0]; // save return value
        mat[0][0] = mat00; // restore original value
        //Print.p(minPath[0],max); 
        */
        //int min;
        //p = true;
        min = findPaths(0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, mat);
        if (shift > 0)  // restore mat and min
        {
            min -= shift;
            for (int i=0; i<mat.length;i++)
                for (int j=0; j<mat.length;j++)
                    mat[i][j] -= shift;
        }
        if (p) Print.p("count = " + count);
        return min;
        //return result;
        //Print.p(minPath[0],minMax[0]); 
        //return minMax[0];
    }
    // assume only +ve number in mat[][] so can marked visited cell by making
    // the number -ve, thus we do not need the "path" string
    // but we lose the ability to save the minPath to see it
    // Also, the method below will not find the min between the max of the paths
    // because it can not keep the global value of that min - it can find
    // the max for a given path, but does not have with what to compare
    private static int findPaths(int x, int y, int max, int min, int[][] m)
    {
        // check boundries
        count++;
        String point = makePt(x, y);
        if (p) Print.p(point, count);
        //restore the boundries check before calling the method to prevent 
        //  unneeded calls to the method - NO: it makes the code more combersome
        if (x < 0 || x >= m.length || y < 0 || y >= m.length) return min;
        //int mxy = (x == 0 && y == 0 ? mat00 : m[x][y]);
        int mxy = m[x][y];
        if (min < mxy) return min; // no use to check this path because a number greater than the min was already found
        //if (p) Print.p("max=" + max + ",value=" + mxy);
        if (p) Print.p(m);
        if (p) Print.p(",value=" + mxy);
        if (mxy < 0) return min; // checks if we visited this point already
        if (max < mxy) max = mxy;
        if (x == m.length-1 && y == m.length-1)
        {
            if (p) Print.p(100, max, min);
            if (min > max) min = max;
            if (p) Print.p(200, max, min);
            //m[x][y] = -m[x][y]; // marked as visited already
            //if (m[0][0] > max)                m[0][0] = max;
            //if (p) Print.p("Path = [" + path + "] max=" + max + ", minMax=" + m[0][0] + ", count=" + ++count);
            return min;
        }
        //if (x == 0 && y == 0)            mat00 = -mat00;
        //else
        m[x][y] = -mxy; // marked as visited already
        //int max = mxy;
        int num1;
        //if (x > 0 && m[x-1][y]>0)// && path.indexOf(makePt(x - 1, y)) < 0) // prevents left after right because it returns to prev point
        //{
            if (p) Print.p("calling U " + point);
            num1 = findPaths(x-1, y, max, min, m);    // up
            //if (min > num) min = num;
            if (p) Print.p("returning U " + point);
        //}
        //if (x < m.length-1 && m[x+1][y]>0)// && path.indexOf(makePt(x + 1, y)) < 0) 
        //{
            if (p) Print.p("calling D " + point);
            int num2 = findPaths(x+1, y, max, min, m);     // down
            //if (min > num) min = num;
            if (p) Print.p("returning D " + point);
        //}
        //if (y > 0 && m[x][y-1]>0)// && path.indexOf(makePt(x, y - 1)) < 0)
        //{
            if (p) Print.p("calling L " + point);
            int num3 = findPaths(x, y-1, max, min, m);    // left
            //if (min > num) min = num;
            if (p) Print.p("returning L " + point);
        //}
        //if (y < m.length-1 && m[x][y+1]>0)// && path.indexOf(makePt(x, y + 1)) < 0) 
        //{
            if (p) Print.p("calling R " + point);
            int num4 = findPaths(x, y+1, max, min, m);     // right
            //if (min > num) min = num;
            if (p) Print.p("returning R " + point);
        //}
        if (m[x][y] < 0) m[x][y] = -m[x][y]; // restore value
        if (p) Print.p(1000, max, mxy, m[x][y]);
        return Math.min(Math.min(num1, num2), Math.min(num3, num4));
        //return min;
    }
    private static void findPaths(int x, int y, String path, int max, 
                        int[][] m, int mat00, String[] minPath)
    {
        String point = makePt(x, y);
        path += point;
        int mxy = (x == 0 && y == 0 ? mat00 : m[x][y]);
        if (p) Print.p(path + ",max=" + max + ",value=" + mxy);
        if (max < mxy) max = mxy;
        if (x == m.length - 1 && y == m.length - 1)
        {
            if (m[0][0] > max)
            {
                m[0][0] = max;
                minPath[0] = path;
            }
            if (p) Print.p("Path = [" + path + "] max=" + max + ", minMax=" + m[0][0] + ", count=" + ++count);
            return;
        }
        if (x > 0 && path.indexOf(makePt(x - 1, y)) < 0) // prevents left after right because it returns to prev point
        {
            if (p) Print.p("calling U " + point);
            findPaths(x - 1, y, path + ",", max, m, mat00, minPath);    // up
            if (p) Print.p("returning U " + point);
        }
        if (x < m.length - 1 && path.indexOf(makePt(x + 1, y)) < 0) 
        {
            if (p) Print.p("calling D " + point);
            findPaths(x + 1, y, path + ",", max, m, mat00, minPath);     // down
            if (p) Print.p("returning D " + point);
        }
        if (y > 0 && path.indexOf(makePt(x, y - 1)) < 0)
        {
            if (p) Print.p("calling L " + point);
            findPaths(x, y - 1, path + ",", max, m, mat00, minPath);    // left
            if (p) Print.p("returning L " + point);

        }
        if (y < m.length - 1 && path.indexOf(makePt(x, y + 1)) < 0) 
        {
            if (p) Print.p("calling R " + point);
            findPaths(x, y + 1, path + ",", max, m, mat00, minPath);     // right
            if (p) Print.p("returning R " + point);
        }
    }
    
    /**
     * A method to find the first positive integer (>0) not included in the
     * array of unsorted integers (may have -ve numbers)
     * 
     * Example: the first number of the array {1,-3,6,2,0,15} is 3
     */
    /**
     * WAITING FOR AN ANSWER REGARDING SPACE COMPLEXITY WHEN HAS A TEMP VAR
     * DEFINED IN THE RECURSIVE METHOD - DOES IT INCREASE IT FOR EACH CALL TO
     * THE METHOD OR ADDS ONLY 1 TO IT
     * answer: we do not count space complexity in recursion since the issue
     * of the stack takes a lot of space and it is complex and it is beyond 
     * the level of this course
     */
    private static int firstLoopRec(int[] arr, int len, int i, int step, int savLen, int sum)
    {
        //if (p) Print.p("i="+i+", len="+len+", step="+step+", savLen="+savLen+", ", arr);
        switch(step){
            case 1: // remove all -ve and 0 numbers and numbers larger than len
                if (i == arr.length)
                {
                    step++;
                    i = -1;
                    savLen = len;
                    break;
                }
                if (arr[i] <= 0 || arr[i] > len)
                {
                    while (len > 0 && (arr[len-1] <= 0 || arr[len-1] > len))
                        len--;
                    if (len == 0) return 1;   // none left
                    if (i >= len)
                        i = arr.length-1;
                    else if (arr[len-1] > 0 && len > 1 && arr[len-1] <= len)
                    {
                        int temp = 0;
                        swap(arr, i, len-1, temp);
                        len--;
                    }
                }
                break;
            case 2: // check if there are duplicates so we need to ignore them
                if (i == savLen)
                {
                    i = -1;
                    if (len == savLen) step++;   // no duplicates so skip that step
                    step++;
                    break;
                }
                int index = Math.abs(arr[i]) - 1;
                if (index < len && arr[index] > 0) // not a duplicate and not larger than len
                    arr[index] = -arr[index];
                else
                {
                    len--;
                    arr[i] = savLen + 1;
                }
                break;
            case 3:
                if (i == savLen)
                {
                    i = -1;
                    step++;
                    break;
                }
                arr[i] = Math.abs(arr[i]);
                if (arr[i] > len && arr[i] < savLen + 1)
                    len--;
                break;
            case 4:
                if (i == savLen)
                {
                    int sumOfSerie = (1 + len) * len / 2;
                    if (sum == sumOfSerie)
                        return len + 1;
                    return sumOfSerie - sum;   // sum must be -ve becuase there are numbers larger than len
                }
                int temp;
                temp = Math.abs(arr[i]);
                if (temp <= len)
                    sum += temp;
        }
        return firstLoopRec(arr, len, i+1, step, savLen, sum);
    }
    // helper method to swap 2 numbers in an array of integers
    private static void swap(int[] arr, int i, int j, int temp)
    {
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }
    public static int firstLoop(int[] arr)
    {
        int len = arr.length;
        if (p) Print.p(arr);
        int[] a = arr.clone();
        if (false) return firstLoopRec(a, arr.length, 0, 1, arr.length, 0);
        p = false;
        count = 0;
        for (int i=0; i<arr.length;i++) // remove all -ve and 0 numbers
        {
            if (arr[i] <= 0 || arr[i] > len)
            {
                if (p) Print.p("i="+i+", len="+len+", ", arr);
                while (len > 0 && (arr[len-1] <= 0 || arr[len-1] > len))
                {
                    len--;
                    count++;
                    Print.p(count);
                }
                if (len == 0) return 1;   // no +ve numbers
                if (p) Print.p("100 len="+len+", ", arr);
                if (i >= len)
                    i = arr.length;
                else if (arr[len-1] > 0 && len > 1 && arr[len-1] <= len)
                {
                    MyLibrary.swap(arr, i, len-1);
                    len--;
                }
                if (p) Print.p("110 len="+len+", ", arr);
            }
            else
                count++;
            /*
            if (false && i < len && arr[i] > len)
            {
                if (p) Print.p(">i="+i+", len="+len+", ", arr);
                while (len > 0 && arr[len-1] > len)
                    len--;
                if (len == 0) return 1;   // no number less then len
                if (p) Print.p(" len="+len+", ", arr);
                if (i >= len)
                    i = arr.length;
                else if (arr[len-1] < len && len > 1)
                {
                    MyLibrary.swap(arr, i, len-1);
                    len--;
                }
                if (p) Print.p(" len="+len+", ", arr);
            }
            */
        }
        if (p) Print.p(arr.length, count);
        p = false;
        if (p) Print.p("1000, len="+len+", ", arr);
        int savLen = len;
        //p = true;
        /*
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
        */
        //p = true;
        if (p) Print.p("2000, len="+len+", ", arr);
        /*
        p = false;
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
        p = false;
        for (int i=0; i<savLen; i++)
        {
            index = Math.abs(arr[i]) - 1;
            if (p) Print.p(""+i+", "+index+", "+len+", ", arr);
            if (index < len && arr[index] > 0) // not a duplicate and not larger than len
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
        //p = true;
        if (p) Print.p("3000, len="+len+", ", arr);
        if (p) Print.p(len, savLen);
        if (len < savLen)   // found duplicates or larger numbers
        {
            for (int i=0; i<savLen; i++)    // remove numbers larger than new length after removing duplicates
            {
                arr[i] = Math.abs(arr[i]);
                if (arr[i] > len && arr[i] < savLen + 1)
                    len--;
            }
        }
        if (p) Print.p("4000, len="+len+", ", arr);
        p = false;
        sum = 0;
        int temp;
        for (int i=0; i<savLen; i++)
        {
            temp = Math.abs(arr[i]);
            if (temp <= len)
                sum += temp;
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
    /*
     * In the recursion method I sorted first which means time complexity
     * of order O(n2) or O(nlogn) if we use quickSort() which requires writing
     * here the method, and we did not learn it yet.
     * That is why I used the firstLoop() method, which has O(n) time complexity
     */
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
        count = 0;
        */
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
        count = 0;
        int indexMore=0, indexLess=1;
        for (int i=0; i<arr.length;i++) // set larger numbers at even positions
        {
            count++;
            if (arr[i] >= calcMed)
            {
                a[indexMore] = arr[i];
                indexMore+=2;
            }
            else if (arr[i] < calcMed)
            {
                a[indexLess] = arr[i];
                indexLess+=2;
            }
        }
        Print.p("O(n) count=" + count);
        /*
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
        */
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
        p = false;
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
     * @return   
     */
    private static int isSemiPalindrome(int[] arr, boolean internal, int numsToRemove, int index)
    {
        if (p) Print.p("in method 1, numsToRemove=" + numsToRemove + ", index=" + index, arr);
        if (index == numsToRemove + 1) // finished to remove numsToRemove numbers
        {
            numsToRemove++; // new length to remove
            index = 0;      // start again from the left of the sub-array
        }
        int len2 = arr.length - numsToRemove; // the reduced length of the sub-array
        if (len2 < 2)           // down to array of length 0 or 1 --> finished the process - not found
        {
            index = numsToRemove = arr.length;
            if (p) Print.p("end0 ", len2);
            return len2;
        }
        if (p) Print.p("numsToRemove="+numsToRemove+", index="+index+", len2="+len2);
        int[] arr2 = new int[len2];
        arrayCopy(arr, index, arr2, 0, len2);
        if (p) Print.p("1. To check for duplicate calls ", arr2);
        /*
         * comment the next line not to use arrayCopy() and uncomment the line after
         */
        //if (isSemiPalindrome(arr2, true, 0))
        if (_isSemiPalindrome(arr, true, 0, index, index + len2))
        {
        arr2 = new int[len2]; // just for the printing here
        arrayCopy(arr, index, arr2, 0, len2);
        if (p) Print.p("end ", arr2);
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
    // same as above but with extra debugging prints
    /*
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
    */
    /* same as next method but without copying to an extra array - just use start/end indices*/
    private static boolean _isSemiPalindrome(int[] arr, boolean internal, int index, int start, int end)
    {
        int n = end - start;
        int[] arr2 = new int[n-index]; // just for the printing here
        arrayCopy(arr, index+start, arr2, 0, arr2.length);
        if (p) Print.p("in method 4, i=" + index + " ", arr2);
        if (p) Print.p(index, start, n, end);
        if (index == n / 2) return true;   // finished checking all the pairs of this array
        // compare 2 digits - one from the left and the other from the right of the array,
        // the position of the digits is shifted by the index i
        if (p) Print.p(index, start, arr[index + start], arr[n - 1 - index + start]);  
        if (arr[index + start] != arr[n - 1 - index + start])
        {
            // remove one number, if the array is internal to the original and check again
            if (index > 0 && internal)
            {
                // This code checks for SemiPalindrome - if it is removed then only true 
                // Palindrome are looked for.
                // It drops the left number of the pair of numbers that are not equal and
                // checks again, then the right number of the pair and checks again
                int len2 = n - 2*index - 1;
                if (len2 > 1)
                {
                    arr2 = new int[len2];
                    arrayCopy(arr, index + 1, arr2, 0, len2);
                    if (p) Print.p("in method 4A, i=" + index + " ", arr2);
                    if (p) Print.p(start, arr[index + 1 + start], arr[len2 + index+1 + start-1]);
                    if (p) Print.p(len2, arr2[0], arr2[len2-1]);
                    boolean r = _isSemiPalindrome(arr, false, 0, index + 1 + start, len2 + start+index+1);
                    if (!r) // now drop the right digit of the pair and check again
                    {
                        arrayCopy(arr, index, arr2, 0, len2);
                        if (p) Print.p("in method 4B, i=" + index + " ", arr2);
                    if (p) Print.p(start, arr[index + start], arr[len2 + index+start-1]);
                        r = _isSemiPalindrome(arr, false, 0, index + start, len2 + index+start);
                    }
                    if (!r) return false;
                }
            }
            else
                return false;
        }
        if (p) Print.p("calling again "+(index+1)+","+start+","+end);
        return _isSemiPalindrome(arr, internal, index+1, start, end); // this sub-array is not semi-palindrom
    }
    // this method uses arraycopy()
    private static boolean isSemiPalindrome(int[] arr, boolean internal, int i)
    {
        if (p) Print.p("in method 3, i=" + i + " ", arr);
        if (p) Print.p(-10, i, arr.length);
        if (i == arr.length / 2) return true;
        // compare 2 digits - one from the left and the other from the right of the array
        // the position of the digits is shifted by the index i
        if (p) Print.p(-20,arr[i], arr[arr.length - 1 - i]);
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
                    arrayCopy(arr, i + 1, arr2, 0, len2);
                    if (p) Print.p("in method 3A, i=" + i + " ", arr2);
                    if (p) Print.p(-30, arr2[0], arr2[len2-1]);
                    boolean r = isSemiPalindrome(arr2, false, 0);
                    if (!r) // now drop the right digit of the pair and check again
                    {
                        arrayCopy(arr, i, arr2, 0, len2);
                        if (p) Print.p("in method 3B, i=" + i + " ", arr2);
                        r = isSemiPalindrome(arr2, false, 0);
                    }
                    if (!r) return false;
                }
            }
            else
                return false;
        }
        if (p) Print.p("calling again "+(i+1));
        return isSemiPalindrome(arr, internal, i+1);
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