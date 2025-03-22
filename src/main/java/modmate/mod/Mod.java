package modmate.mod;

import modmate.mod.attribute.ModAttributes;

/**
 * Represents a mod with a specific name, code, description, and associated attributes.
 * This class stores the name, code, description, and attributes of the mod
 * and provides methods to access each of these properties.
 */
public class Mod {

    /**
     * The name of the mod.
     */
    private final String name;

    /**
     * The code of the mod (e.g., CS101).
     */
    private final String code;

    /**
     * A brief description of the mod.
     */
    private final String description;

    /**
     * The attributes associated with the mod, such as mod level, credits, etc.
     */
    private final ModAttributes attributes;

    /**
     * Constructs a new Mod object with the specified name, code, description, and attributes.
     *
     * @param name The name of the mod.
     * @param code The code of the mod.
     * @param description A brief description of the mod.
     * @param attributes The attributes associated with the mod.
     */
    public Mod(String name, String code, String description, ModAttributes attributes) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.attributes = attributes;
    }

    /**
     * Returns the name of the mod.
     *
     * @return The name of the mod.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the code of the mod.
     *
     * @return The code of the mod.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the description of the mod.
     *
     * @return The description of the mod.
     */
    public String getDetailedString() {
        return "Code: " + code +
                "\nName: " + name +
                "\nDescription: " + description +
                "\nFaculty: " + attributes.getFaculty() +
                "\nAvailability: " + attributes.listAvailableSemesters() +
                "\nUnits: " + attributes.getUnits() +
                "\nGraded: " + attributes.isGraded() +
                "\nPrerequisites: " + attributes.getPrerequisites() +
                "\nWorkload: " + attributes.getWorkload() +
                "\nClass Slots: " + attributes.listClassSlots();
    }

    /**
     * Returns a string representation of the mod.
     *
     * @return A string representation of the mod.
     */
    public String toString() {
        return code + ": " + name + "\n    " + description + "\n    " + attributes.getFaculty();
    }
}
