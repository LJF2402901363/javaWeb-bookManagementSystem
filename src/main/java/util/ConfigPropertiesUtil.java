package util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:57
 * @desc 配置文件的工具类
 **/
public class ConfigPropertiesUtil {
    private static Properties[] properties= new Properties[2];
    static {

        Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/table.properties");
        for (int i=0;i < properties.length;i++){
            properties[i]=new Properties();
        }
        try {
            properties[0].load( Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/druid.properties"));
            properties[1].load( Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/tableName.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getPropertiesValueByName(String name){
        for (int i=0;i < properties.length;i++){
            if (properties[i].containsKey(name)){
                return properties[i].getProperty(name);
            }
        }
      return null;
    }

}
