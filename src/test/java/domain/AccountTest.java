package domain;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * @author 陌意随影
 * @create 2020-01-31 21:24
 * @desc 测试类
 **/
public class AccountTest {
    @Test
    public void testAccount() {
        Class<Account> accountClass = Account.class;
        Method[] methods = accountClass.getMethods();
        for (Method method:methods){

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length==1){
                System.out.println(parameterTypes[0].getName());
            }
        }
    }
}
