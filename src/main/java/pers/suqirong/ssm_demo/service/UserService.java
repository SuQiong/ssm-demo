package pers.suqirong.ssm_demo.service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pers.suqirong.ssm_demo.dao.UserDao;
import pers.suqirong.ssm_demo.domain.User;  
  
  
@Service
public class UserService { 
    @Resource  
    private UserDao userDao;  
    
    public User getUserById(int userId) {  
        return this.userDao.selectByPrimaryKey(userId);  
    }  
  
}