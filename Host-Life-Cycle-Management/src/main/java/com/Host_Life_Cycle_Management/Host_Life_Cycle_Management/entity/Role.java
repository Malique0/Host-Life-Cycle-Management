package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity;


import jakarta.persistence.*;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "roles")

public class Role {

   @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

   @Enumerated(EnumType.STRING)
    private RoleName name;

   @ManyToMany(mappedBy = "roles")
   private Set<User> users;

   //Getter und Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
