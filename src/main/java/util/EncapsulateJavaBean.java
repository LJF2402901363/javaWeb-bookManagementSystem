package util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-31 21:02
 * @desc 封装JavaBean对象
 **/
public class EncapsulateJavaBean {
    /**
     * @Description :通过结果集的resultset数据来封装对应的实体类对象，并且返回一个对象的集合
     * @Date 21:06 2020/1/31 0031
     * @Param * @param collection
     * @param objClass
     * @param resultSet ：
     * @return void
     **/
    public static<T> void  encapsulateJavaBean(Collection<T> collection, Class<T> objClass, ResultSet resultSet){
        if (collection==null) return;
        Constructor<T> constructor =null;
        try {
            constructor = objClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        T obj =null;
        while (true){
            try {
                if (!resultSet.next()) break;
                //通过构造器实例化一个新的对象
                obj = constructor.newInstance();
                //获取对象的所有方法
                Method[] methods = objClass.getMethods();
                //遍历每个方法
                for (Method method:methods){
                    //获取每个方法的名称
                    String methodName = method.getName();
                    //如果是setter方法
                    if ("set".equals(methodName.substring(0,3))){
                        //获取方法的属性
                        String fieldName = methodName.substring(3).toLowerCase();
                        //获取参数类型的名称，setter方法只有一个
                        String parameterTypeName = method.getParameterTypes()[0].getName();
                      if ("int".equals(parameterTypeName)||"java.lang.Integer".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getInt(fieldName));
                      }else if ("short".equals(parameterTypeName)||"java.lang.Short".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getShort(fieldName));
                      }else if ("long".equals(parameterTypeName)||"java.lang.Long".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getLong(fieldName));
                      }else if ("byte".equals(parameterTypeName)||"java.lang.Byte".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getByte(fieldName));
                      }else if ("float".equals(parameterTypeName)||"java.lang.Float".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getFloat(fieldName));
                      }else if ("double".equals(parameterTypeName)||"java.lang.Double".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getDouble(fieldName));
                      }else if ("java.lang.String".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getString(fieldName));
                      }else if ("boolean".equals(parameterTypeName)||"java.lang.Boolean".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getBoolean(fieldName));
                      }else if ("Date".equals(parameterTypeName)||"java.util.Date".equalsIgnoreCase(parameterTypeName)){
                          method.invoke(obj,resultSet.getDate(fieldName));
                      }else{
                         //其它类型
                      }





                    }
                }
                 collection.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
    /**
     * @Description :通过键值对的paramMap来封装对应的实体类对象，并且该对象
     * @Date 21:06 2020/1/31 0031
     * @Param * @param collection
     * @param objClass
     * @param paramMap ：
     * @return void
     **/
    public static<T> T  encapsulateJavaBean( Class<T> objClass, Map<String,String> paramMap){
        if (paramMap==null||paramMap.size()==0||objClass==null) return  null;
        Constructor<T> constructor =null;
        T obj =null;
        try {
            constructor = objClass.getConstructor();
            obj=constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
   for (Map.Entry<String,String>entry:paramMap.entrySet()){
       //获取键值对中的属性名称
       String fileName_paramMap = entry.getKey();
       String value = entry.getValue();

       //获取所有的方法
       Method[] methods = objClass.getMethods();
       //遍历每个方法
       for (Method method:methods){
           //获取方法名
           String methodName = method.getName();
           if ("set".equals(methodName.substring(0,3))){
               //获取方法的属性名
               String fieldName=methodName.substring(3);
               if (fieldName.equalsIgnoreCase(fileName_paramMap)){
                   //获取setter方法参数的类型
                   String parameterTypeName = method.getParameterTypes()[0].getName();
                   try {
                       if ("int".equals(parameterTypeName)||"java.lang.Integer".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Integer.parseInt(value));
                       }else if ("short".equals(parameterTypeName)||"java.lang.Short".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Short.parseShort(value));
                       }else if ("long".equals(parameterTypeName)||"java.lang.Long".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Long.parseLong(value));
                       }else if ("byte".equals(parameterTypeName)||"java.lang.Byte".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Byte.parseByte(value));
                       }else if ("float".equals(parameterTypeName)||"java.lang.Float".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Float.parseFloat(value));
                       }else if ("double".equals(parameterTypeName)||"java.lang.Double".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Double.parseDouble(value));
                       }else if ("java.lang.String".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,value);
                       }else if ("boolean".equals(parameterTypeName)||"java.lang.Boolean".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,Boolean.parseBoolean(value));
                       }else if ("Date".equals(parameterTypeName)||"java.util.Date".equalsIgnoreCase(parameterTypeName)){
                           method.invoke(obj,DateUtil.StrToDate(value));
                       }else{
                           //其它类型
                       }
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }
           }
       }
   }
            return  obj;
    }

}
