package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.controller;


import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity.Role;
import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity.RoleName;
import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.entity.User;
import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.repository.RoleRepository;
import com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import  java.util.*;
//Zeigt an das die Klasse ein WebController ist und makiert diese als MVC Controller
@Controller
public class HomeController {


    //Automatische Einspeisung der UserRepository-Instanz
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;







    //Get-Anfrage an die Root-URL("/")
    @GetMapping("/")
    public String index(Model model) {
// Überprüfen ob es bereits ein BEnutzer gibt
        if(userRepository.count() == 0){

            return "redirect:/register";//Kein Benutzer vorhanden,direkt zur Registrierung ansonsten zur Startseite(index)
        }
        return "index";
    }

    //Get-Anfrage an die URL ("/login")
    @GetMapping("/login")
        public String login(){
        return "login";
        }
    //Get-Anfrage an die URL ("/register")
    @GetMapping("/register")
    public String Registrierung() {
        return "register";
    }

    @GetMapping("/greeting")
    public String greeting(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Holt den Benutzernamen des aktuell authentifizierten Benutzers

        model.addAttribute("username", username);
        return "greeting";
    }

    //Post-Anfrage an die URL "/login"
    @PostMapping("/login")
    public String Anmeldung(@RequestParam String username, @RequestParam String password, Model model) {
        //Versuch einen Benutzer anhand des angegebenen Benutzernamens zu finden
        Optional<User> optionalUser = userRepository.findByUsername(username);
        //Überprüft ob der Benutzer existiert und das Passwort übereinstimmt

        if (optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword())){
            model.addAttribute("username", username); // Benutzername für die nächste Seite hinzufügen
            return "redirect:/greeting?username=" + username; // Weiterleitung zur Begrüßungsseite
        }
        else {
            model.addAttribute("error", "ungültiger Benutzername oder Passwort");
            //gibt die "login" Ansicht zurück
            return "login";
        }
    }
    @PostMapping("/register")
    public String Registrieren(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
       //Überprüft ob die angegebenen Passwörter übereinstimmen
        if(!password.equals(confirmPassword)) {
            //Fügt eine Fehlermeldung zum Model hinzu
            model.addAttribute("error", "Passwörter stimmmen nicht überein");
            //gibt die "register" Ansicht zurück
            return "register";

        }





        //Erstellen eines neuen Benutzers
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);  // Passwort verschlüsseln



        //Speichert den neuen Benutzer in der Datenbank
        userRepository.save(user);

        //Fügt eine Erfolgsmeldung zum Model hinzu
        model.addAttribute("message", "registrierung erfolgreich!");
        //gibt die "index" Ansicht zurück
        return "index";
    }
}

/*kurze Erklärung:

Klassendeklaration und Package:

    package com.Host_Life_Cycle_Management.Host_Life_Cycle_Management.controller; :Definiert das Paket für diese Controller-Klasse.

Imports:

    Verschiedene Importe werden für Annotationen und Klassen aus dem Spring Framework verwendet sowie für die Handhabung der User-Entität und des UserRepository.
H   omeController-Klasse:

    Annotiert mit @Controller, was anzeigt, dass diese Klasse ein Web-Controller ist.
Autowired Repository:
    @Autowirde private UserRepository userRepository; : Spring injiziert eine Instanz des UserRepository, um mit der Datenbank zu interagieren

Index-Methode:
    @GetMapping("/"): Mapped Get-Anfragen an die Root URL.
    public String index(Model model): Gibt die index Ansicht zurück.

Login-Methoden:
    @GetMapping("/login") : Mapped Get-Anfrage an die URL "login".
    public String login(): Gibt die login-Ansicht zurück.
    @PostMapping("/login"): Mapped POST-Anfragen an die URL "/login".
    public String Anmeldung(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model):
        Behandelt die Registrierungs-Logik.
        Überprüft, ob die Passwörter übereinstimmen.
        Erstellt eine nneue User-Entität, setzt deren Eigenschaften und speichert sie in der Datenbank.
        Fügt Meldungen zum Modell hinzu und gibt die entsprechende Ansicht zurück.
Wichtige Punkte:
Spring MVC Annotation:
    @Controller: Makiert die Klasse als Spring MVC-Controller.
    @GetMapping und @PostMapping: Mappen HTTP Get- und Post-Anfragen auf Handler-Methoden.
    @RequestParam: Bindet Anfangsparameter an Methodenargumente.
    @Autowired: Ermöglicht die Depency Injection.

Model:
    Wird verwendet, um Daten zwishcen dem Controller und der Ansicht übergeben.
UserRepository:
    Benutzerdefiniertes Repository-Interface zur Durchführung von Datenbankkoperationen auf User-Entitäten.
     Dieser Code demonstriert ein einfaches Login- und Registrierungssystem mit Spring MVC,
     bei dem Benutzer

     */