package dataSets;

import javax.persistence.*;

/**
 * Created by Демьян on 09.01.2017.
 */
@Entity
@Table(name = "users")
public class UserDataSet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "password", updatable = false)
    private String password;

    @Column(name = "score")
    private int score;

    public UserDataSet() {
    }

    public UserDataSet(String name, String password) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(password);
        this.score = 0;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int add) {
        score = score + add;
    }
}
