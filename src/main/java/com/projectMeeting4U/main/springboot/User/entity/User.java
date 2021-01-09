package com.projectMeeting4U.main.springboot.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectMeeting4U.main.springboot.Location.entity.CurrentLocation;
import com.projectMeeting4U.main.springboot.Meeting.entity.MeetingUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private String userId;

    private String name;

    private String password;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @OneToOne
    @JoinColumn(name = "current_loc_id")
    private CurrentLocation currentLocation;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private Set<MeetingUser> meetingUsers = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public User() {}

    public User(
            String userId,
            String name,
            String password,
            String email,
            String phoneNumber,
            String homeAddress,
            CurrentLocation currentLocation,
            List<String> roles
    ) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.currentLocation = currentLocation;
        this.roles = roles;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() { return id; }

    public String getUserId() { return userId; }

    public String getName() { return name; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getHomeAddress() { return homeAddress; }

    public CurrentLocation getCurrentLocation() { return currentLocation; }

    public List<String> getRoles() { return roles; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt;}

    @JsonIgnore
    public Set<MeetingUser> getMeetingUsers() { return meetingUsers; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        return this.roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
