package com.nfl.demo.player;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="player_data")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String team;
    private String position;
    @Column(name = "jersey_number")
    private Integer jerseyNumber;
    private String status;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private Integer height;
    private Integer weight;
    private String college;
    @Column(name = "years_exp")
    private Integer yearsExp;
    @Column(name = "rookie_year")
    private Integer rookieYear;
    @Column(name = "draft_club")
    private String draftClub;
}
