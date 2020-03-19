package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:03
 * @desc 账户的实体类
 **/
public class Account implements Serializable {
    /**性别男*/
    public final static String SEX_FEMALE="男";
    /**性别女*/
    public final static String SEX_MALE="女";
    /**中性*/
    public final static String SEX_UNKNOWN="中性";
    /**状态正常*/
    public final static int STATUS_NORMAL=1;
    /**状态异常*/
    public final static int STATUS_ERRO=0;
    /**游客*/
    public final static int TYPE_TOURIST=1;
    /**普通用户*/
    public final static int TYPE_USER=2;
    /**管理员*/
    public final static int TYPE_ADMINISTRATOR=3;
    //唯一属性id，由数据库自动生成
    private int id;
    //用户名
    private String name;
    //性别
    private String sex;
    //密码
    private String password;
    //创建日期
    private Date createTime;
    //状态
    private int status;
    //角色类型
    private  int type;
    /**游客*/
    public final int TYPE_ORDINARY_TOURIST=0;
    /**普通用户*/
    public final int TYPE_ORDINARY_USER=1;
    /**管理员*/
    public final int TYPE_ORDINARY_ADMINISTRATOR=2;
    //爱好
    private String hobby;
    //签名
    private String signature;
    //年龄
   private int age;
    public Account() {
    }

    public Account(int id, String name, String sex, String password, Date createTime, int status, int type, String hobby, String signature, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.createTime = createTime;
        this.status = status;
        this.type = type;
        this.hobby = hobby;
        this.signature = signature;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date  getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

}
