package org.furia.chatbot.repository;

import jakarta.transaction.Transactional;
import org.furia.chatbot.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Roles, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into user_roles (user_id, role_id) values (:user_id, :role_id)", nativeQuery = true)
    void insertRole (@Param("user_id") Long userId, @Param("role_id") Long roleId);

}
