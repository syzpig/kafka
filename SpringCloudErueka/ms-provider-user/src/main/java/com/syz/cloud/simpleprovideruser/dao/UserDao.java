package com.syz.cloud.simpleprovideruser.dao;

import com.syz.cloud.simpleprovideruser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/8 0008.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
