package it.ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ticket.platform.model.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
