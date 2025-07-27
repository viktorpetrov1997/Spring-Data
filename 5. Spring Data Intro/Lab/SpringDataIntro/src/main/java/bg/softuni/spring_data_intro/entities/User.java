package bg.softuni.spring_data_intro.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Account> accounts;

    public User()
    {
        this.accounts = new ArrayList<>();
    }

    public User(String username, int age)
    {
        this();
        this.username = username;
        this.age = age;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public List<Account> getAccounts()
    {
        return accounts;
    }

    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }
}
