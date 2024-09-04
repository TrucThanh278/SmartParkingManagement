package com.ou.pojo;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.ToString;

@ToString
@Entity
public class VerificationToken {

    private static long EXPIRATION_TIME_MINUTES = 60 * 24; // 24 hours

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;

    public VerificationToken() {
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION_TIME_MINUTES);
    }

    public static long getEXPIRATION_TIME_MINUTES() {
        return EXPIRATION_TIME_MINUTES;
    }

    public static void setEXPIRATION_TIME_MINUTES(long expirationTimeMinutes) {
        EXPIRATION_TIME_MINUTES = expirationTimeMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
