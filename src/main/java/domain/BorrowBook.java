package domain;

import java.util.Date;

/**
 * @author 陌意随影
 * @create 2020-02-09 19:56
 * @desc 借书情况的实体类
 **/
public class BorrowBook {
    //借书记录的id主键
    private  int id;
    //借阅人的id
    private  int accountId;
    //书籍的id
    private  int bookId;
    //借书时间
    private Date  borrowTime;
    //还书时间
    private  Date returnTime;

    public BorrowBook() {
    }

    public BorrowBook(int id, int accountId, int bookId, Date borrowTime, Date returnTime) {
        this.id = id;
        this.accountId = accountId;
        this.bookId = bookId;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public String toString() {
        return "BorrowBook{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", bookId=" + bookId +
                ", borrowTime=" + borrowTime +
                ", returnTime=" + returnTime +
                '}';
    }
}
