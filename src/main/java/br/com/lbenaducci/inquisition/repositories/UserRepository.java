package br.com.lbenaducci.inquisition.repositories;

import br.com.lbenaducci.inquisition.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}