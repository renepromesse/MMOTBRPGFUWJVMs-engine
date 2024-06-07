import java.io.Serializable;

public class Helmet extends Armour implements Serializable {
    private static final long serialVersionUID = 1L;
    /* Class Variables */

    /* Constructors */
    public Helmet(String name, int durability, int defenseRating) {
        super(name, durability, defenseRating);
    }
}