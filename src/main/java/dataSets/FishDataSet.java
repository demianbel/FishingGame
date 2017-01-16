package dataSets;



import javax.persistence.*;

/**
 * Created by Демьян on 09.01.2017.
*/
@Entity
@Table(name = "fishes")
public class FishDataSet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    @Column(name = "image_path", unique = true )
    private String imagePath;

    @Column(name = "score")
    private int score;

    @Column(name = "chance")
    private int chance;

    public FishDataSet() {
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(byte chance) {
        this.chance = chance;
    }
}
