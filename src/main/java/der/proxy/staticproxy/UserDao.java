package der.proxy.staticproxy;

/**
 * Created by dev2 on 2018/7/5.
 */
public class UserDao implements IUserDao{
    @Override
    public void save() {
        System.out.println("--------已保存数据-------");
    }

    @Override
    public void update() {
        System.out.println("--------已更新数据-------");
    }
}
