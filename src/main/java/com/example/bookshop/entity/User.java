package com.example.bookshop.entity;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "users")
@Data
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @Fetch(FetchMode.SUBSELECT)
    private List<Book> favoriteBooks;
    @Override
    public String toString() {
        if (favoriteBooks == null) {
            favoriteBooks = new ArrayList<>();
        }
        return "User{" +
                "id=" + getId() + ", username='" + getUsername() + '\'' + ", firstName='" + getFirstName() + '\'' + ", lastName='" + getLastName() + '\'' + ", email='" + getEmail() + '\'' + ", password='" + getPassword() + '\'' + ", favoriteBooks=" + favoriteBooks + '}';
    }
}
