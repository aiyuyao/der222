package der;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dev2 on 2018/5/23.
 */
public class RegexTest {
    public static void main(String[] args) {
        String str = new String("湘DSSSAD");
        Pattern pattern = Pattern.compile("^(([\\u4e00-\\u9fa5]{1}[A-Z]{1})[-]?|([wW][Jj][\\u4e00-\\u9fa5]{1}[-]?)|([a-zA-Z]{2}))([A-Za-z0-9]{5}|[DdFf][A-HJ-NP-Za-hj-np-z0-9][0-9]{4}|[0-9]{5}[DdFf])$");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }

    }
}
