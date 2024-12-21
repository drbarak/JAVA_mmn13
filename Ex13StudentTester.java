
/**
 * 20441
 * 2025A
 * StudentTester Ex13 
 *
 */

public class Ex13StudentTester
{
    public static void main(String[] args){
        System.out.println ("********** Question 1 **********\n");
        int[] arr1 = {4, -5, -3, 1, 2, 7, 0};
        int med1 = 1;
        System.out.println("Checking method 'specialArr' on array "+toString(arr1)+" and med = " + med1);
        int[] studentResult1;
        studentResult1 = Ex13.specialArr(arr1, med1);
        System.out.println("Result is: "+toString(studentResult1));
        int result=checkIfSpecial(studentResult1);
        if(result>=0)
        {
            System.out.println ("Error!!! The array is not a special array." );
            System.out.println("The first problematic index is : "+result);

        }
        System.out.println();

        int[] arr2 = {4, -5, -3, 1, 2, 7, 9, 0};
        int med2 = 2;
        System.out.println("Checking method 'specialArr' on array "+toString(arr2)+" and med = " + med2);
        int[] studentResult2;
        studentResult2 = Ex13.specialArr(arr2, med2);

        System.out.println("Result is: "+ toString(studentResult2));
        result=checkIfSpecial(studentResult2);
        if(result>=0)
        {
            System.out.println ("Error!!! The array is not a special array." );
            System.out.println("The first problematic index is : "+result);

        }
        System.out.println();
        System.out.println("Note that these are not the only special arrays that can be created");
        System.out.println();

        System.out.println ("********** Question 2 **********\n");
        int[] arr = new int[]{1, -3, 6, 2, 0, 15};
        System.out.println("Checking method 'first' on array " + toString(arr));
        int studentResult = Ex13.first(arr);
        System.out.println("Result is: "+studentResult);
        System.out.println();

        arr = new int[]{1, 1, 1, 1};
        System.out.println("Checking method 'first' on array " + toString(arr));
        studentResult = Ex13.first(arr);
        System.out.println("Result is: "+studentResult);
        System.out.println();

        arr = new int[]{1, 2, 3, 4};
        System.out.println("Checking method 'first' on array " + toString(arr));
        studentResult = Ex13.first(arr);
        System.out.println("Result is: "+studentResult);
        System.out.println();

        arr = new int[]{5, -1, 3, 1, 0, -2, 2};
        System.out.println("Checking method 'first' on array " + toString(arr));
        studentResult = Ex13.first(arr);
        System.out.println("Result is: "+studentResult);
        System.out.println();

        arr = new int[]{7, 8, 9, 11, 12, 14};
        System.out.println("Checking method 'first' on array " + toString(arr));
        studentResult = Ex13.first(arr);
        System.out.println("Result is: "+studentResult);
        System.out.println();

        System.out.println ("********** Question 3 **********\n");
        int[] array = {1, 1, 4, 10, 10, 4, 3, 10, 10};
        System.out.println("Checking method 'longestNearlyPal' on array " + toString(array));
        int studentResult3 = Ex13.longestNearlyPal(array);
        System.out.println("Result is: "+studentResult3);
        System.out.println();

        System.out.println ("********** Question 4 **********\n");

        int[][] a={{1,2},{3,4}};
        System.out.println("Checking method 'extreme' on array ");
        print(a);
        int studentResult4 = Ex13.extreme(a);
        System.out.println("Result is: "+studentResult4);
        System.out.println();

        int[][] b={{1,3},{4,2}};
        System.out.println("Checking method 'extreme' on array ");
        print(b);
        studentResult4 = Ex13.extreme(b);
        System.out.println("Result is: "+studentResult4);
        System.out.println();

        int[][] c={{4,5,8,2},{3,12,7,16},{13,1,10,14},{15,11,9,6}};
        System.out.println("Checking method 'extreme' on array ");
        print(c);
        studentResult4 = Ex13.extreme(c);
        System.out.println("Result is: "+studentResult4);
        System.out.println();

        int[][] d={{4,5,8,2},{3,12,16,7},{13,1,10,14},{15,11,9,6}};
        System.out.println("Checking method 'extreme' on array ");
        print(d);
        studentResult4 = Ex13.extreme(d);
        System.out.println("Result is: "+studentResult4);
        System.out.println();

    }

    private static String toString(int[] arr)
    {
        String s = "{";
        for(int i=0; i<arr.length-1; i++)
            s+=arr[i]+", ";
        return s+arr[arr.length-1]+"}";
    }

    private static void print(int[][] arr)
    {
        for(int i=0; i<arr.length; i++)
        {
            for(int j = 0; j < arr[i].length-1; j++)
                System.out.print(arr[i][j]+"\t");
            System.out.println(arr[i][arr[i].length-1]);
        }
    }

    private static int checkIfSpecial(int []arr){
        int i;

        for(i=0;i<arr.length-1;i++)
            if(i%2==0)//even place
            {
                if(arr[i]<arr[i+1])//incorrect
                    return i;
            }
            else//odd place
                if(arr[i]>arr[i+1])//incorrect
                    return i;

        return -1;//special array
    }
}
