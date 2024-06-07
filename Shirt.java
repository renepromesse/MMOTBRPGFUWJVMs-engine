import java.io.Serializable;

public class Shirt extends Armour implements Serializable {
    private static final long serialVersionUId = 1L;
    /* Class Variables */

    /* Constructors */
    public Shirt(String name, int durability, int defenseRating) {
        super(name, durability, defenseRating);
    }
}