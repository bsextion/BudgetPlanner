//package com.cal.budget.repo;
//
//import com.cal.budget.dao.User;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface UserRepo extends MongoRepository<User, String > {
//   @Query(value = "{'bills.name': ?0}", fields = "{'bills' : 0")
//    User findUserByBillId(String id);
//
//   User fixndUserById(String id);
//
//
//
//
//}
