package edu.lucasrech.catalog_authentication.repository;

import edu.lucasrech.catalog_authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
