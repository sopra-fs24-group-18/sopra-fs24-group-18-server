package ch.uzh.ifi.hase.soprafs24.rest.dto.user;

import ch.uzh.ifi.hase.soprafs24.constant.UserStatus;

import java.time.LocalDate;

public class UserPointsGetDTO {
  private String username;
  private Long score;
  private Long addScore;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getAddScore() {
        return addScore;
    }

    public void setAddScore(Long addScore) {
        this.addScore = addScore;
    }
}
