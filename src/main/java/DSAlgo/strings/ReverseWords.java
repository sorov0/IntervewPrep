package DSAlgo.strings;

public class ReverseWords {

    //Stack can also be used to solve this question
    //https://www.geeksforgeeks.org/problems/reverse-words-in-a-given-string5459/1

    String reverseWords(String str)
    {

        String arr[] = str.split("\\.");
        String ans = "";
        for(int i = arr.length - 1 ; i >= 0 ; i--){
            ans = ans + arr[i];
            if(i>0) ans = ans + ".";
        }
        return ans;

    }

}
