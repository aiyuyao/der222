package der.proxy.staticproxy;

/**
 * Created by dev2 on 2018/7/5.
 */
public class UserDaoProxy implements IUserDao {

    private IUserDao iUserDao;

    /*这里注入实际调用的方法*/
    public UserDaoProxy(IUserDao iUserDao){
        this.iUserDao=iUserDao;
    }

    @Override
    public void save() {
        System.out.println("-------开始事务--------");
        iUserDao.save();                                                    //在不改动原有代码的前提下，扩展了目标对象的功能
        System.out.println("-------结束事务--------");
    }

    @Override
    public void update() {

    }
}
