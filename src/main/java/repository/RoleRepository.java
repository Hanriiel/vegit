package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {


}
