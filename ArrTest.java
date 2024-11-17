import java.util.Scanner;
import java.util.Arrays;

public class ArrTest
{
    // method to test the class
    public static void main(String[] args)
    {
        //p("Enter 4 numbers, seperated by space for array with 4 numbers");
        /*
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[4];
        arr[0] = scan.nextInt();
        arr[1] = scan.nextInt();
        arr[2] = scan.nextInt();
        arr[3] = scan.nextInt();
         */
        /*
        int[] arr = {4, -5, -3, 2, 1, 7, 0};
        int[] newArr = Ex13.specialArr(arr, 10);
        p("specialArr result=" + Arrays.toString(newArr));
        int[] arr2 = {4, -5, -3, 2, 1, 7, 0, 9};
        newArr = Ex13.specialArr(arr2, 10);
        p("specialArr result=" + Arrays.toString(newArr));
        int[] arr3 = {-6, -5, -3, 2, 1, 7, 0, -9};
        newArr = Ex13.specialArr(arr3, 10);
        p("specialArr result=" + Arrays.toString(newArr));
         */
        /*
        int[] newArr = {4, -5, -3, 2, 1, 7, 0};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        newArr = new int[]{1, -3, 6, 2, 0, 15};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        newArr = new int[]{1, 1, 1, 1};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        newArr = new int[]{0};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        newArr = new int[]{1, 2, 3, 4};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        newArr = new int[]{5, -1, 3, 1, 0, -2, 2};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        newArr = new int[]{7,8,9,11,12,14};
        p("first +ve=" + Ex13.first(newArr) + ", array=" + Arrays.toString(newArr));
        */
        /*
        int fact = factorial(13);
        p("fact = "+fact);
         */
        /*
        int[] arr = {4, 10, 10,  3, 4};
        arr = new int[] {4, 10, 10, 3, 10, 4};
        arr = new int[] {4, 10, 10, 10, 3, 4};
        arr = new int[] {4, 10, 10, 10, 10, 3, 10, 4};
        arr = new int[] {10, 10, 4, 3, 10};
        //    System.out.println("is Pila " + isSemiPalindropm(arr, -1, -1) + ", array=" + Arrays.toString(arr)); 

        arr = new int[] {1, 1, 4, 10, 10, 4, 3, 10, 10};
        //arr = new int[] {1, 1, 4};
        p("is Pila " + Ex13.longestNearlyPal(arr) + ", array=" + Arrays.toString(arr));
        */
       int[][] m = {{-1, -2, -3}, {-4, -5, -6}, {-7, -8, -9}};
       p("extreme " + Ex13.extreme(m));
       for (int row = 0; row < m.length; row++)
           p(Arrays.toString(m[row]));
/*           
       char[][] c = {{'X',   'O',   'X'},   {'O',   'O',   'X'},   {'X',   'X',   'O'}};
       for (int row = 0; row < c.length; row++)
           p(Arrays.toString(c[row]));
           */
       /*
       for (int i = 0; i < m[0].length; i++)
       {
           p("" + m[0][i] + ", " +  m[1][i]);
       }
       */
       final int RIGHT_DOWN = 1;
       final int LEFT_UP = -1;
        final int POINT1 = 0;
        final int POINT2 = 1;

       int row = 0;
         while (row < m[0].length)
         {
             int j = 0;
             while (j < m[1].length)
             {
                 p(row, j, m[row][j]);
                 int[] pt = {row, j};
                 int[][] next2Pts = next2Points(pt, m[0].length);
                 p(Arrays.toString(next2Pts[POINT1]));
                 p(Arrays.toString(next2Pts[POINT2]));
                 j += RIGHT_DOWN;
             }
             row += RIGHT_DOWN;
         }
         int[] endPoint = {m[0].length - 1, m[0].length -1};
         nextPointLeft(endPoint);
         nextPointUp(endPoint);
    }
        final static int ROW = 0;
        final static int COLUMN = 1;
    private static int[][] next2Points(int[] point, int endPoint)
    {
        int[][] next2Points = new int[2][2];
        final int POINT1 = 0;
        final int POINT2 = 1;
        next2Points[POINT1][ROW] = point[ROW];
        if (point[COLUMN] + 1 < endPoint) // move right
            next2Points[POINT1][COLUMN] = point[COLUMN] + 1;
        else
            next2Points[POINT1][COLUMN] = -1;
        next2Points[POINT2][COLUMN] = point[COLUMN];
        if (point[ROW] + 1 < endPoint) // move down
            next2Points[POINT2][ROW] = point[ROW] + 1;
        else
            next2Points[POINT2][ROW] = -1;
        return next2Points;
    }
    private static int[] nextPointLeft(int[] point)
    {
        int[] newPoint = {point[ROW], point[COLUMN]};
        p(Arrays.toString(point));
        if (point[ROW] <= 0)
        {
            newPoint[ROW] = -1;
            return newPoint;
        }
        nextPointUp(newPoint);
        newPoint[ROW]--;
        return nextPointLeft(newPoint);
    }
    private static int[] nextPointUp(int[] point)
    {
        int[] newPoint = {point[ROW], point[COLUMN]};
        p(Arrays.toString(point));
        if (point[COLUMN] <= 0)
        {
            newPoint[COLUMN] = -1;
            return newPoint;
        }
        newPoint[COLUMN]--;
        return nextPointUp(newPoint);
    }

    private static int factorial(int n)
    {
        if (n > 0)
            return n * factorial(n -1);
        return 1;
    }

    private static void p(String s)
    {
        System.out.println(s);
    }

    private static void p(int s)
    {
        p(" " + s);
    }
    private static void p(int n1, int n2)
    {
        p("" + n1 + ", " + n2);
    }
    private static void p(int n1, int n2, int n3)
    {
        p("" + n1 + ", " + n2 + ", " + n3);
    }
}
