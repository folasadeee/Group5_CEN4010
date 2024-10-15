package com.example.bookstore.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "credit_cards")

public class CreditCard {
    @Id
    @Column (name = "card_number", length = 16)
    private String cardNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserProfile userId;

    @Column (name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "cvv", length = 3)
    private String cvv;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public UserProfile getUserId() {
        return userId;
    }

    public void setUserID(UserProfile userId) {
        this.userId = userId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
