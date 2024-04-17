//package ru.kata.spring.boot_security.service;
//
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.kata.spring.boot_security.dao.UserDao;
//import ru.kata.spring.boot_security.model.User;
//
//import java.util.List;
//
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserDao userDao;
//
//    @Autowired
//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Transactional
//    @Override
//    public void add(User user) {
//        userDao.add(user);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<User> getUsers() {
//        return userDao.getUsers();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public User getUserById(long id) {
//        return userDao.getUserById(id);
//    }
//
//    @Override
//    @Transactional
//    public void deleteUser(long id){
//        userDao.deleteUser(id);
//    }
//
//    /*
//    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
//    */
//    @Override
//    @Transactional
//    public void truncateTable() {
//        userDao.truncateTable();
//    }
//    @Override
//    @Transactional
//    public void fillUsersTable(){
//        userDao.fillUsersTable();
//    }
//}
