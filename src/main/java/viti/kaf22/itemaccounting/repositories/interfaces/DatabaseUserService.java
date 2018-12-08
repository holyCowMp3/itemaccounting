package viti.kaf22.itemaccounting.repositories.interfaces;

import viti.kaf22.itemaccounting.models.User;
import viti.kaf22.itemaccounting.models.enums.UserRole;

import java.util.Date;
import java.util.List;


public interface DatabaseUserService {

    List<User> getAllUsers();

    User findById(long id);

    User add(User user);

    User update(User user);

    void deleteUserById(long id);

    User getByEmail(String email);

    List<User> getAllBySurname(String surname);

    List<User> getAllByRole(UserRole role);

    List<User> getAllByLastSeenDate(Date lastSeen);

    User registerNewUserAccount(User model) throws Exception;

    User validateUser(User user);


}
