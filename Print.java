import java.util.Arrays;
/**
 * Write a description of class Print here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Print
{
    public static void p(String s)
    {
        System.out.println(s);
    }
    public static void p(int s)
    {
        p(" " + s);
    }
    public static void p(boolean n)
    {
        p("" + n);
    }
    public static void p(int n1, int n2)
    {
        p("" + n1 + ", " + n2);
    }
    public static void p(int n1, int n2, int n3)
    {
        p("" + n1 + ", " + n2 + ", " + n3);
    }
    public static void p(int[] arr)
    {
        p(Arrays.toString(arr));
    }
    public static void p(String s, int[] arr)
    {
        p(s + Arrays.toString(arr));
    }
    public static void p(String s, int n)
    {
        p(s + ", " + n);
    }
}
