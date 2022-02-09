package vcs.lt.project;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "plan")
    private String plan;
//
//    @Convert(converter = HashMapConverter.class)
//    private Map<String, Object> plan;

    public User() {
        this.username = "";
        this.password = "";
        this.plan = "";
    }

    public User(String username, String password) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.plan = "";
    }

    public User(int id, String username, String password, String plan) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.plan = plan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", plan='" + plan + '\'' +
                '}';
    }
}
