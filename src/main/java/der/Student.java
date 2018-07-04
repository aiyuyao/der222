package der;

import java.util.ArrayList;

/**
 * Created by dev2 on 2018/5/31.
 */
public class Student {
    private long id;
    private String name;
    public static void main(String[] args) {
        ArrayList<Student> arrayList  = new ArrayList();
        Student student = new Student();
        student.id = 20134822;
        student.name = "aiyuyao";
        arrayList.add(student);


        ArrayList<Student> arrayList1 = (ArrayList<Student>) arrayList.clone();
        System.out.println(arrayList1.get(0).name="der");
        System.out.println(arrayList.get(0).id);
        System.out.println(arrayList.get(0).name);

    }

}
