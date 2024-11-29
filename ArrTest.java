import java.util.Scanner;
public class ArrTest
{
    // method to test the class
    public static void main(String[] args)
    {
        int run = 1;//1000+100+10+1;
        int[][] m;
        int[] arr;
        boolean p = true;
        if (run >= 1000)
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
            Print.p("extreme minMax = " + Ex13.extreme(m, false));
        }
        if (run % 1000 >= 100)
        {
            Print.p("\n------ specialArr ------");
            arr = new int[]{4, -5, -3, 2, 1, 7, 0};
            Print.p("Result=", Ex13.specialArr(arr, 1));
            arr = new int[]{4, -5, -3, 2, 1, 7, 0, 9};
            Print.p("Result=", Ex13.specialArr(arr, 10));
            arr = new int[]{-6, -5, -3, 2, 1, 7, 0, -9};
            Print.p("Result=", Ex13.specialArr(arr, 10));
        }
        if (run % 100 >= 10)
        {
            Print.p("\n------ first ------");
            arr = new int[]{4, -5, -3, 2, 1, 7, 0};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
            arr = new int[]{1, -3, 6, 2, 0, 15};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
            arr = new int[]{1, 1, 1, 1};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
            arr = new int[]{0};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
            arr = new int[]{1, 2, 3, 4};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
            arr = new int[]{5, -1, 3, 1, 0, -2, 2};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
            arr = new int[]{7,8,9,11,12,14};
            Print.p("first +ve=" + Ex13.first(arr) + ", array=", arr);
        }
        if (run % 10 > 0)
        {
            Print.p("\n------ isSemiPalindropm ------");
            /**/
            arr = new int[] {1, 2, 3, 4, 5};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 2, 3, 5, 5};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 2, 3, 4, 4};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 2, 3, 3, 5};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 3, 2, 3, 5};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 3, 4};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 3, 10, 4};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 10, 3, 4};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 10, 10, 10, 10, 3, 10, 4};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {10, 10, 4, 3, 10};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {1, 1, 4, 10, 10, 4, 3, 10, 10};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            /**/
            arr = new int[] {1, 1, 4};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {4, 2, 2};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {2, 4, 2};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {-1, -1};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {-1};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
            arr = new int[] {};
            Print.p("isSemiPalindropm " + Ex13.longestNearlyPal(arr) + ", array=", arr);
        }
    }
    /*
    int fact = factorial(13);
    Print.p("fact = "+fact);
     */

    private static int factorial(int n)
    {
        if (n > 0)
            return n * factorial(n -1);
        return 1;
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

