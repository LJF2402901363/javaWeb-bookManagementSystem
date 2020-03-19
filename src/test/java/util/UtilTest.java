package util;

import org.junit.Test;

/**
 * @author 陌意随影
 * @create 2020-02-05 17:23
 * @desc 工具类的测试
 **/
public class UtilTest {
    @Test
    public void testColumsName(){
        String[] accounts = JdbcUtil.getTableColumnsName("account");
        for (String name:accounts){
            System.out.println(name);
        }
        String[] account1 = JdbcUtil.getTableColumnsName("book");
        for (String name:account1 ){
            System.out.println(name);
        }
    }
}
