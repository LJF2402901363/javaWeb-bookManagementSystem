package domain;

import java.util.Date;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:34
 * @desc 书籍类
 **/
public class Book {
    // id | name         | author | price | type | status | destription | sbn
    //书籍的唯一主键，由数据库自动生成
    private  int id;
    //书籍名称
    private  String name;
    //作者
    private  String author;
    //价格
    private double price;
    //类型
     private String type;
     //状态
    private int status;
    /**状态被借阅*/
    public final static int STATUS_BORROWED=2;
    /**状态正常*/
    public final static int STATUS_NORMAL=1;
    /**状态异常*/
    public final static int STATUS_ERRO=0;
    //描述
    private String  description;
    //版本号
    private String sbn;
    //创建时间
    private Date createTime;
    public Book() {
    }

    public Book(int id, String name, String author, double price, String type, int status, String description, String sbn, Date createTime) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
        this.status = status;
        this.description = description;
        this.sbn = sbn;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static int getStatusNormal() {
        return STATUS_NORMAL;
    }

    public static int getStatusErro() {
        return STATUS_ERRO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSbn() {
        return sbn;
    }

    public void setSbn(String sbn) {
        this.sbn = sbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", sbn='" + sbn + '\'' +
                '}';
    }
}
