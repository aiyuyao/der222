package der.proxy.dynamicproxy;

import der.proxy.staticproxy.IUserDao;
import der.proxy.staticproxy.UserDao;

/**
 * Created by dev2 on 2018/7/5.
 */
public class Test {
    public static void main(String[] args) {
        IUserDao userDao = new UserDao();
        IUserDao proxy = (IUserDao) new DymaticProxyFactory(userDao).getProxyInstance();
        proxy.update();
        proxy.save();
    }
}
