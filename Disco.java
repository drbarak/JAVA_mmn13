
/**
 * Write a description of class Disco here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Disco
{
    public static void main()
    {
        boolean[] a = {false, true, false, false, true};
        boolean[] b = {false, false, true, false, false};
        Print.p("" + disco(a, b, 0));
    }

    private static boolean disco(boolean[] a, boolean[] b, int i)
    {
        if(equalArrays(a, b, 0)) return true;
        if(i == a.length) return false;
        press(a, i);
        if(disco(a, b, i+1)) return true;
        press(a, i);
        return disco(a, b, i+1);
    }

    private static boolean equalArrays(boolean[] a, boolean[] b, int i)
    {   
        if(i == a.length)
            return true;
        if(a[i] != b[i])
            return false;
        return equalArrays(a, b, i+1);
    }

    private static void press(boolean[] a, int i)
    {
        a[i] = !a[i];
        if(i > 0)
            a[i-1] = !a[i-1];
        if(i < a.length-1)
            a[i+1] = !a[i+1];
    }
}
