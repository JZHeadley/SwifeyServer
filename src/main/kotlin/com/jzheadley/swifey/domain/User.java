package com.jzheadley.swifey.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Nullable
    private String userId;

    private String firstName;
    private String lastName;
    private Date dob;
    private Timestamp creationDate;
    private Integer numSwipes;
    private String messagingId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phoneId")
    private Phone phone;

    @OneToMany(mappedBy = "checkedInUser")
    @JsonIgnoreProperties({"checkedInUser"})
    private Set<CheckIn> checkIns;

    @ManyToMany
    @JoinTable(
            name = "Followings",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "followerId")
    )
    @JsonIgnoreProperties({"followers", "following", "checkIns"})
    private Set<User> followers;

    @ManyToMany(mappedBy = "followers")
    @JsonIgnoreProperties({"followers", "following", "checkIns"})
    private Set<User> following;

    public User() {
    }

    public User(String userId, String firstName, String lastName, Date dob, Timestamp creationDate, Integer numSwipes, String messagingId, Phone phone, Set<CheckIn> checkIns, Set<User> followers, Set<User> following) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.creationDate = creationDate;
        this.numSwipes = numSwipes;
        this.messagingId = messagingId;
        this.phone = phone;
        this.checkIns = checkIns;
        this.followers = followers;
        this.following = following;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumSwipes() {
        return numSwipes;
    }

    public void setNumSwipes(Integer numSwipes) {
        this.numSwipes = numSwipes;
    }

    public String getMessagingId() {
        return messagingId;
    }

    public void setMessagingId(String messagingId) {
        this.messagingId = messagingId;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Set<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(Set<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", creationDate=" + creationDate +
                ", numSwipes=" + numSwipes +
                ", messagingId='" + messagingId + '\'' +
                ", phone=" + phone +
                ", checkIns=" + checkIns +
//                ", followers=" + followers +
//                ", following=" + following +
                '}';
    }
}