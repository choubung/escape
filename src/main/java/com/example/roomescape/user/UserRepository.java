package com.example.roomescape.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
}
