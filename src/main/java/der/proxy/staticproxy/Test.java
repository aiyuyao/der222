package der.proxy.staticproxy;

/**
 * Created by dev2 on 2018/7/5.
 */
public class Test {
    public static void main(String[] args) {
        IUserDao userDao = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        proxy.save();
    }
}
