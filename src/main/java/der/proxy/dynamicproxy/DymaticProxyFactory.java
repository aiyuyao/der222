package der.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dev2 on 2018/7/5.
 */
public class DymaticProxyFactory {
    private Object target;
    public DymaticProxyFactory(Object object){
        this.target = object;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("--------开始事务2--------");
                        Object returnValue = method.invoke(target,args);
                        System.out.println("--------提交事务2--------");
                        return returnValue;
                    }
                });
    }
}
