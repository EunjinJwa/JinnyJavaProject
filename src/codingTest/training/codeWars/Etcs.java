package codingTest.training.codeWars;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Kassy.
 * Date: 2019-12-07
 */
public class Etcs {
    public static void main(String[] args) {
        System.out.println(summation(8));
        System.out.println(longest("aretheyhere", "yestheyarehere"));
    }

    public int sum(List<?> mixed) {
        mixed.stream().mapToInt(i -> Integer.parseInt((String) i)).sum();
        return mixed.stream().mapToInt(i -> Integer.parseInt((String) i)).sum();
    }

    public static int summation(int n) {
        return IntStream.rangeClosed(1,n).sum();
}

    public static String longest (String s1, String s2) {

        StringBuffer resultStr = new StringBuffer();
        s1.concat(s2).chars().distinct().sorted().forEach(i -> resultStr.append((char) i));
        return resultStr.toString();
    }


}
