//package com.cal.budget.service;
//
//import com.cal.budget.dao.User;
//import com.cal.budget.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private UserRepo userRepo;
//
//    @Autowired
//    public void setUserRepo(UserRepo userRepo){
//        this.userRepo = userRepo;
//    }
//
//    public void addUser(User user){
//        userRepo.save(user);
//    }
//
//    public List<User> getUsers() {
//        return (List<User>) userRepo.findAll();
//    }
//
//    public User getUser(String employeeId) {
//        return userRepo.findUserById(employeeId);
//    }
//
//    public void removeUser(String employeeId){
//        userRepo.deleteById(employeeId);
//    }
//
//    public User getUserByBill(String id){
//       return userRepo.findUserByBillId(id);
//    }
//}
