import java.util.Arrays;
public class Tester
{
    // method to test the class
    private static int checkIfSpecial(int []arr){
        int i;
        for(i=0;i<arr.length-1;i++)
        {
            if(i%2==0)//even place
            {
                if(arr[i]<arr[i+1])//incorrect
                    return i;
            }
            else//odd place
            {
                if(arr[i]>arr[i+1])//incorrect
                    return i;
            }
        }
        return -1;//special array
    }
    
    public static void main()
    {
        int[] run = {0,0,0,0};
        int[][] m;
        int[] arr;
        boolean p = true;
        if (run[3] == 1)
        {
            m = new int[][]{{1, 3}, {4, 2}};
            m = new int[][]{{1, 2}, {3, 4}};
            m = new int[][]{{4,5,8,2}, {3,12,7,16}, {13,1,10,14}, {15,11,9,6}};
            m = new int[][]{{4,5,8,2}, {3,12,16, 7}, {13,1,10,14}, {15,11,9,6}};
            m = new int[][]{{4,1,9,3,25}, {24,23,22,21,5}, {13,12,15,16,14}, {17,11,18,19,20}, {10,2,8,7,6}};
            m = new int[][]{{4,1,9,3,25}, {24,23,2,21,5}, {13,12,15,16,22}, {17,11,18,19,20}, {10,14,8,7,6}};
            m = new int[][]{{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}};
            Print.p("------ extreme ------");
            for (int row = 0; row < m.length; row++)
                Print.p("row " + row, m[row]);
            Print.p("extreme minMax = " + Ex13Draft.extreme(m));
        }
        if (run[2] == 1)
        {
            Print.p("\n------ specialArr ------");
            int a[][] = new int[117][];
            a[0] = new int[]{2, -5, -3, 0, 1, 4, 7};
            a[1] = new int[]{2, -5, -3, 1, 4, 7, 0};
            a[2] = new int[]{2, -5, -3, 1, 4, 7, 0, 10,3,-8};
            a[3] = new int[]{1,2};
            a[4] = new int[]{5};
            a[5] = new int[]{};
            a[6] = new int[]{4, -5, -3, 2, 1, 7, 0, 9};
            a[7] = new int[]{-6, -5, -3, 2, 1, 7, 0, -9};
            a[8] = new int[]{-5, 1, -3, 2, -2, 7, 0};
            a[9] = new int[]{4, -5, -3, 1, 2, 7, 0};
            a[10] = new int[]{4, -5, -3, 1, 2, 7, 9, 0};
            a[11] = new int[]{25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7, 6, 5, 4, 3, 2, 1};
            a[12] = new int[]{1,2,3,4,5,6,7, 8, 9, 10, 11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
            int last = 2;
            for (int j=0,k=98;j<last;j+=2,k+=10000)
            {
                a[13+j] = new int[k + 1]; for (int i=k; i>=0; i--)  a[13+j][k-i] = i + 1;
                a[13+j+1] = new int[k + 1 +1]; for (int i=k; i>=0; i--)  a[13+j+1][k-i] = i + 1;
            }
            for (int i=13+last-1;i<13+last;i++)
            {
                int med = 0;
                if (a[i].length > 0)
                {
                    int[] workArr = a[i].clone();  // copy the original array so not to change it
                    Arrays.sort(workArr);
                    med = workArr[workArr.length/2];
                    Print.p("med=" + med +", array: " + Arrays.toString(workArr));                
                }
                int[] studentResult1= Ex13.specialArr(a[i], med);
                //int[] studentResult1= Ex13Draft.specialArr(a[i], med);
                //int[] studentResult1= Ex13Draft.specialArrNoSort(a[i], 1);
                Print.p(""+i+", med=" + med + ", Result=", studentResult1);
                int result=checkIfSpecial(studentResult1);
                if(result>=0)
                {
                    System.out.println ("Error!!! The array is not a special array." );
                    System.out.println("The first problematic index is : "+result);
                }
            }
        }
        if (run[1] == 1)
        {
            Print.p("\n------ first ------");
            arr = new int[]{-2,-3,0,1,20};
            arr = new int[]{4, -5, -3, 2, 1, 7, 0};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 3);
            arr = new int[]{4, -5, -3, 2, 1, 7, 0, 4, 4, 4};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 3);
            arr = new int[]{1, -3, 6, 2, 0, 15};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 3);
            arr = new int[]{1, 1, 1, 1};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 2);
            arr = new int[]{0};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 1);
            arr = new int[]{1, 2, 3, 4};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 5);
            arr = new int[]{7,8,9,11,12,14};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 1);
            arr = new int[]{7, 6, 5, 4, 3, 2, 1};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 8);
            arr = new int[]{5, -1, 3, 1, 0, -2, 2};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 4);
            arr = new int[]{1, 2, 4, 4};
            Print.p("first +ve=" + Ex13Draft.firstLoop(arr) + ", test="+ 3);
        }
        if (run[0] == 1)
        {
            Print.p("\n------ isSemiPalindrome ------");
            arr = new int[] {1, 2, 3, 4, 5};
            /**/
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 2, 3, 5, 5};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 2, 3, 4, 4};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 2, 3, 3, 5};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 3, 2, 3, 5};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 3, 4};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 3, 10, 4};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 10, 3, 4};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 10, 10, 3, 10, 4};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {10, 10, 4, 3, 10};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            /**/
            arr = new int[] {1, 1, 4, 10, 10, 4, 3, 10, 10};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            /**/
            arr = new int[] {1, 1, 4};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 2, 2};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {2, 4, 2};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {-1, -1};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {-1};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {};
            Print.p("isSemiPalindrome " + Ex13Draft.longestNearlyPal(arr) + ", array=", arr);
            /**/
        }
    }
}
/* test numOfPaths
//p(numOfPaths(1, 1));
//int n = numOfPaths(SIZE, SIZE);
//int[] arr = new int[n];
//numOfPaths(_size, _size);
//p("Enter 4 numbers, seperated by space for array with 4 numbers");
 * 
private static int _size = 2;
private static int _count = 0;
private static int _route = 0;
private static int[] lastX = new int[100], lastY = new int[100];
private static int numOfPaths(int x, int y, int _route)
{
Print.p(x, y, _count);
if (x == 0 && y == 0) return 0;
if (x != 0 && y != 0)
{
if (p) Print.p("_route=" + _route + ", x=" + 
x + ", y=" + y + ", lastX=" + lastX[_route] + ", lastY=" + lastY[_route]);
lastX[_route] = x;
lastY[_route] = y;
_route++;
}
if (p) Print.p("_route2=" + _route);
//        if (x == 0 || y == 0) return 1;
//if (x == _size && y == _size) return 0;
//if (x < 0 || y < 0 || x > MAX || y > MAX) return 0;
if (x > 0) 
{
if (p) Print.p("calling 1");
numOfPaths(x - 1, y, _route);    // left
if (x - 1 == 0 && y == 0) _count++;
if (p) Print.p("returning 1");
}
if (x < _size) 
{
if (p) Print.p("before calling 3 _route=" + (_route - 1) + ", x=" + 
x + ", y=" + y + ", lastX=" + lastX[_route - 1] + ", lastY=" + lastY[_route - 1]);
if (x != lastX[_route - 1] && y != lastY[_route - 1])
{
if (p) Print.p("calling 3");
numOfPaths(x + 1, y, _route);    // right
if (x + 1 == 0 && y == 0) _count++;
if (p) Print.p("returning 3");
}
}
if (y > 0)
{
if (p) Print.p("calling 2");
numOfPaths(x, y - 1, _route);    // down
if (x == 0 && y - 1 == 0) _count++;
if (p) Print.p("returning 2");

}
//if (x < _size) numOfPaths(x + 1, y);  // right
//if (y < MAX) numOfPaths(x, y + 1);    // up
Print.p("_count = " + _count);
return 0;
}
private static int numOfPathsOrg(int x, int y)
{
if (x == 0 && y == 0) return 0;
if (x == 0 || y == 0) return 1;
return numOfPathsOrg(x - 1, y) + numOfPathsOrg(x, y - 1);
}
 */