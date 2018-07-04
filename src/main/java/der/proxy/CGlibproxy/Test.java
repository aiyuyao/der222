package der.proxy.CGlibproxy;

/**
 * Created by dev2 on 2018/7/5.
 */
public class Test {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        StudentDao proxyInstance =(StudentDao) new CGlibProxyFactory(studentDao).getProxyInstance();
        proxyInstance.save();
    }
}
