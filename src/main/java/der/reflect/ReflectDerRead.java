package der.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Hashtable;

/**
 * Created by dev2 on 2018/5/21.
 */
public class ReflectDerRead {
    public static void main(String[] args) throws Exception {
        Class stuClass = Class.forName("com.der.reflect.Student");
        Constructor constructor = stuClass.getConstructor(String.class,String.class);
        Student student = (Student) constructor.newInstance("der","12345678");
        Field[] fields = stuClass.getDeclaredFields();

        for (Field field:fields){
            System.out.println(field.getName()+" : ");
            field.setAccessible(true);
            System.out.println(field.get(student));
        }
    }
}

class Student{
    public String username;
    private String password;
        public Student (String username,String password){
        this.username = username;
        this.password = password;
    }

    public void changePassword(){
        System.out.println("new password : "+this.password);

        Hashtable hashtable = new Hashtable();
        hashtable.keySet();
    }
}