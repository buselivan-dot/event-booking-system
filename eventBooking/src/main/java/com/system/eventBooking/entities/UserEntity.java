package com.system.eventBooking.entities;

import com.system.eventBooking.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    //joining with BookingEntity table
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;

}
