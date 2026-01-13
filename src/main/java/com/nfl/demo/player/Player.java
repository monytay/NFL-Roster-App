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

    public Player() {
    }

    public Player(Long id, String team, String position, Integer jerseyNumber, String status, String firstName, String lastName, LocalDate birthDate, Integer height, Integer weight, String college, Integer yearsExp, Integer rookieYear, String draftClub) {
        this.id = id;
        this.team = team;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.college = college;
        this.yearsExp = yearsExp;
        this.rookieYear = rookieYear;
        this.draftClub = draftClub;
    }

    public Player(String name){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getYearsExp() {
        return yearsExp;
    }

    public void setYearsExp(Integer yearsExp) {
        this.yearsExp = yearsExp;
    }

    public Integer getRookieYear() {
        return rookieYear;
    }

    public void setRookieYear(Integer rookieYear) {
        this.rookieYear = rookieYear;
    }

    public String getDraftClub() {
        return draftClub;
    }

    public void setDraftClub(String draftClub) {
        this.draftClub = draftClub;
    }
}
