package com.syz.cloud.simpleprovideruser.entity;




import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/8 0008.
 */
@Entity
@Table(name="user")
public class User {
    //因为使用的是springdataJPA 所以要加jpa注释
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Short age;

    @Column
    private BigDecimal balance;

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
