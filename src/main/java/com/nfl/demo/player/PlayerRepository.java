package com.nfl.demo.player;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    void deleteByFirstName(String firstName);

    Optional<Player> findByFirstName(String firstName);

}
