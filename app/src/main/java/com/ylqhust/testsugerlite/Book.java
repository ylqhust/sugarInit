package com.ylqhust.testsugerlite;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by apple on 15/12/30.
 */
public class Book extends SugarRecord {
    @Unique
    public String bookId;
    public String bookName;
    public int price;
    public long createDate;

    public Book(String bookId, String bookName, long createDate, int price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.createDate = createDate;
        this.price = price;
    }
}
