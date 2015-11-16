
package Modele;

/**
 *
 * @author febenbou
 */
public enum SeatClass {

    FIRST("First"), BUSI("Busi"), ECO("Eco");
    private String name;

    SeatClass(String name) {
        this.setName(name);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
