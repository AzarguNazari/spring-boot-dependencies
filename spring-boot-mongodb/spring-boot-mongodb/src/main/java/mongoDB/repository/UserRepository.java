package mongoDB.repository;

import mongoDB.model.User;

public interface UserRepository {
    void saveUser(User user);
    User findUserByUserName(String userName);
    long updateUser(User user);
    void deleteUserById(Long id);
}
