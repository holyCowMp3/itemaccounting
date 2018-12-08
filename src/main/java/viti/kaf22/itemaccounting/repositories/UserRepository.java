package viti.kaf22.itemaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viti.kaf22.itemaccounting.models.User;
import viti.kaf22.itemaccounting.models.enums.UserRole;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User getByEmail(String email);

    List<User> getAllBySurname(String surname);

    List<User> getAllByRole(UserRole role);

    List<User> getAllByLastSeenDate(Date lastSeen);


}
