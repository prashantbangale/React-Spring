/*
 * package com.ReactFrom.Model;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name = "student") public class StudentD {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * private String firstname; private String lastname; private String email;
 * private String number; private String contact; private String gender; private
 * String languages; private String subject; private String resumeName; private
 * String date;
 * 
 * @jakarta.persistence.Column(length = 2000) private String about;
 * 
 * public StudentD() { }
 * 
 * public Long getId() { return id; }
 * 
 * public String getFirstname() { return firstname; }
 * 
 * public void setFirstname(String firstname) { this.firstname = firstname; }
 * 
 * public String getLastname() { return lastname; }
 * 
 * public void setLastname(String lastname) { this.lastname = lastname; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * public String getNumber() { return number; }
 * 
 * public void setNumber(String number) { this.number = number; }
 * 
 * public String getContact() { return contact; }
 * 
 * public void setContact(String contact) { this.contact = contact; }
 * 
 * public String getGender() { return gender; }
 * 
 * public void setGender(String gender) { this.gender = gender; }
 * 
 * public String getLanguages() { return languages; }
 * 
 * public void setLanguages(String languages) { this.languages = languages; }
 * 
 * public String getSubject() { return subject; }
 * 
 * public void setSubject(String subject) { this.subject = subject; }
 * 
 * public String getResumeName() { return resumeName; }
 * 
 * public void setResumeName(String resumeName) { this.resumeName = resumeName;
 * }
 * 
 * public String getDate() { return date; }
 * 
 * public void setDate(String date) { this.date = date; }
 * 
 * public String getAbout() { return about; }
 * 
 * public void setAbout(String about) { this.about = about; } }
 * 
 */


package com.ReactFrom.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class StudentD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String number;
    private String contact;
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String languages;

    private String subject;
    private String resumeName;
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String about;

    public StudentD() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getLanguages() { return languages; }
    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getResumeName() { return resumeName; }
    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
}
