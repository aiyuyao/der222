package der.proxy.CGlibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * Created by dev2 on 2018/7/5.
 */
public class CGlibProxyFactory implements MethodInterceptor {

    private Object target;

    public CGlibProxyFactory(Object object){
        this.target = object;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--------开始事务-------");
        Object returnValue = method.invoke(target,objects);
        System.out.println("--------提交事务-------");
        return returnValue;
    }
}
