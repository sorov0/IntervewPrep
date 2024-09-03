package DSAlgo.strings;

public class CheckIfAStringIsRotatedByTwoPlaces {

    //https://www.geeksforgeeks.org/problems/check-if-string-is-rotated-by-two-places-1587115620/1

    public static boolean isRotated(String str1, String str2)
    {
        // Your code here
        // amazon , azonam
        // amazon : lr -> az + onam = azonam
        // amazon : rr -> on + amaz = onamaz

        int n = str1.length();
        int m = str2.length();
        if(n!=m || n<=1 || m<=1) return false;

        String leftRot = str1.substring(2) + str1.substring(0 , 2);
        String rightRot = str1.substring(m-2) + str1.substring(0 , m-2);
        if(leftRot.equals(str2) || rightRot.equals(str2)) return true;
        return false;
    }

    public static void main(String[] args) {
        String str1 = "amazon";
        int m = str1.length();
        String leftRot = str1.substring(2) + str1.substring(0 , 2);
        String rightRot = str1.substring(m-2) + str1.substring(0 , m-2);
        System.out.println(leftRot);
        System.out.println(rightRot);
    }
}
