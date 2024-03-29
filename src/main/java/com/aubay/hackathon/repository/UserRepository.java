package com.aubay.hackathon.repository;

import com.aubay.hackathon.model.table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {

    UserTable findByEmail(String username);

    @Query("SELECT SUM(user.access) FROM USER user")
    Long getAllAccess();
}
