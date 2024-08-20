package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity;


import jakarta.persistence.*;

import java.util.*;

//Annotation kennzeichnet die Klasse als JPA-Entity(entspricht einer Datenbanktabelle)
@Entity
//spezifiziert die Tabelle in der Datenbank, die mit dieser Entity verknüpft ist
@Table(name = "users")
public class User {
    //Feld als Primärschlüssel kennzeichnen
    @Id
    //konfiguriert die Generierungsstrategie für den Primärschlüssel(IDENTITY automatische inkrement der id)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)//spezifiziert das die email(Spalte) einzigartig sein muss
    private String email;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();

    //Getter und Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}


/*
Kurze Erklärung
Klassendeklaration und Paket:
    package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity; : Definiert das Paket für diese Entitätsklasse.

Entity- und Table-Annotation:
    @Entity : Diese Annotation kennzeichnet die Klasse als JPA-Entität. Eine JPA-Entität repräsentiert ein Objekt, das in einer Datenbank gespeichert wird.
    @GenaratedVAlue(strategy = GeneratioType.IDENTITY) : Diese Annotation spezifiziert, dass der Wert des Primärschlüssels automatisch generiert wird. Die GenerationType.IDENTITY-Strategy bedeutet,
    dass der Primärschlüsselwert von Datenbank generiert wird, typischerweise durch eine Auto-Inkrement-Spalte.

Column-Annotation:
    @Column(unique = true): Diese Annotation spezifiziert, dass die Spalte email in der Datebank einzigartig sein muss. Das bedeutet, das kein anderer Benutzer dieselbe E-Mail haben kann.


Felder und Ihre Getter und Setter
    Felder:
    private Long id; : Primärschlüssel der Entität.
    private String email; : E-Mail- Adresse des Benutzers.
    private String username; : Benutzername des Benutzers.
    private String password; : Passwort des Benutzers.

    Getter und Setter Methoden:
        Diese Methoden ermöglichen den Zugriff auf die Felder der Entität und deren Modifikation.
        Z.zB.
            getId() ermöglicht den Zugriff auf den Wert des Feldes id, und setId(Long id)
            erlaubt die Festlegung eines neuen Wertes für id.


Zsammenfassung:
    Diese User-Klasse ist eine JPA-Entität, die eine Zeile in der users-Tabelle der Datenbak repräsentiert.
    Die Klasse enthält Felder für id, email, username und password.
    Das Feld id ist der Primärschlüssel und wir dautomatisch generiert. Das Feld email ist eindeutig, was bedeutet,
    das keine zwei Benutzer dieselbe E-Mail haben können. Die Getter- und Setter MEthoden werden bereitgestellt,um Zugriff auf die Felder der Entität
    und deren Modifikation zuu ermöglichen.
 */