package der.reflect;

/**
 * Created by dev2 on 2018/5/21.
 */
public class PartionOnStack {
    class User{
        private int id;
        private String name;
        public User(){}
    }
    public  void foo() {
        User user=new User();
        user.id=1;
        user.name="sixtrees";
    }
    public static void main(String[] args) {
        PartionOnStack pos=new PartionOnStack();
        pos.foo();
    }

}

class Test{
    public static void main(String[] args) {
        PartionOnStack partionOnStack = new PartionOnStack();
        PartionOnStack.User user = partionOnStack.new User();
    }
}