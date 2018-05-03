package com.syz.cloud.simpleconssumermovie.entity;




import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class User implements Serializable {
    //因为使用的是springdataJPA 所以要加jpa注释
    private Long id;

    private String username;

    private String name;

    private Short age;

    private BigDecimal balance;

    public User(){
        this.name="张三";
        this.age=24;
        this.balance=new BigDecimal(12.322);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
