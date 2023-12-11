package be.tsapasmi.factorymanagement.dal;

import be.tsapasmi.factorymanagement.domain.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsByIdAndEnabledIs(long id, boolean value);

    @Modifying
    @Transactional
    @Query("""
    UPDATE User u
    SET u.enabled = :value
    WHERE u.id = :userId
""")
    void toggleUser(long userId, boolean value);

    @Query("""
    SELECT COUNT(u) = 0 FROM User u
    JOIN ProductStep ps ON ps.createdBy = u
    JOIN Product p ON ps.product = p
    JOIN Batch b ON p.batch = b
    WHERE u.id = :userId
    AND ps.finished = false
    AND ps.paused = false
    AND 1 < (
        SELECT COUNT(DISTINCT b.id) FROM User u
    JOIN ProductStep ps ON ps.createdBy = u
    JOIN Product p ON ps.product = p
    JOIN Batch b ON p.batch = b
    WHERE u.id = :userId
    AND ps.finished = false
    AND ps.paused = false
    )
""")
    boolean isUserAvailable(long userId);

}
