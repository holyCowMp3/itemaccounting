package viti.kaf22.itemaccounting.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import viti.kaf22.itemaccounting.models.User;
import viti.kaf22.itemaccounting.models.enums.UserRole;
import viti.kaf22.itemaccounting.repositories.UserRepository;
import viti.kaf22.itemaccounting.repositories.interfaces.DatabaseUserService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DatabaseUserServiceImpl implements DatabaseUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public User findById(long id) {
        return  userRepository.getOne(id);
    }

    @Override
    public User add(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }


    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }


    @Override
    public List<User> getAllBySurname(String surname) {
        return userRepository.getAllBySurname(surname);
    }

    @Override
    public List<User> getAllByRole(UserRole role) {
        return userRepository.getAllByRole(role);
    }

    @Override
    public List<User> getAllByLastSeenDate(Date lastSeen) {
        return userRepository.getAllByLastSeenDate(lastSeen);
    }


    @Transactional
    @Override
    public User registerNewUserAccount(User user) throws Exception {
        if (emailExist(user.getEmail())) {
           throw new Exception();
        }
        User newUser = new User();
        System.out.println(user.getPassword());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setLastSeenDate(new Date());
        newUser.setEmail(user.getEmail());
        newUser.setRole(UserRole.USER_UNVERIFIED);
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setThirdname(user.getThirdname());
        return add(newUser);


    }

    @Override
    public User validateUser(User user) {
        user.setRole(UserRole.USER_VERIFIED);
        userRepository.save(user);
        return user;
    }
    private boolean emailExist(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}
