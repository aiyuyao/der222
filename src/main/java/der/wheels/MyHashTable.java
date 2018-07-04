package der.wheels;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dev2 on 2018/7/4.
 */
public class MyHashTable {
    public static void main(String[] args) {
        Hashtable studentInfo = new Hashtable<String,String>();
        studentInfo.put("name","aiyuyao");
        HashMap newStudentInfo = new HashMap();
        newStudentInfo.put("name","ayy");
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String,String>();
        concurrentHashMap.put("name","der");
        concurrentHashMap.containsKey("name");
        concurrentHashMap.remove("name");
        concurrentHashMap.size();


    }
}
