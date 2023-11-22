package lab.user.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lab.contracts.Repository;
import lab.user.entities.User;

public interface UserRepository extends Repository<User,UUID> {
    Optional<User> findByName(String name);
}
