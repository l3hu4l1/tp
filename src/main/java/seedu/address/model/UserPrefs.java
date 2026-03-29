package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    public static final int DEFAULT_RESTOCK_THRESHOLD_VALUE = 0;

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path productsFilePath = Paths.get("data", "inventory.json");
    private Path aliasFilePath = Paths.get("data", "alias.json");
    private int defaultRestockThresholdValue = DEFAULT_RESTOCK_THRESHOLD_VALUE;

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setProductsFilePath(newUserPrefs.getProductsFilePath());
        setAliasFilePath(newUserPrefs.getAliasFilePath());
        setDefaultRestockThresholdValue(newUserPrefs.getDefaultRestockThresholdValue());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getProductsFilePath() {
        return productsFilePath;
    }

    public void setProductsFilePath(Path productsFilePath) {
        requireNonNull(productsFilePath);
        this.productsFilePath = productsFilePath;
    }

    public Path getAliasFilePath() {
        return aliasFilePath;
    }

    public void setAliasFilePath(Path aliasFilePath) {
        requireNonNull(aliasFilePath);
        this.aliasFilePath = aliasFilePath;
    }

    public int getDefaultRestockThresholdValue() {
        return defaultRestockThresholdValue;
    }

    public void setDefaultRestockThresholdValue(int defaultRestockThresholdValue) {
        if (defaultRestockThresholdValue < 0) {
            throw new IllegalArgumentException("Default restock threshold must be non-negative.");
        }
        this.defaultRestockThresholdValue = defaultRestockThresholdValue;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefs)) {
            return false;
        }

        UserPrefs otherUserPrefs = (UserPrefs) other;
        return guiSettings.equals(otherUserPrefs.guiSettings)
                && addressBookFilePath.equals(otherUserPrefs.addressBookFilePath)
                && productsFilePath.equals(otherUserPrefs.productsFilePath)
                && aliasFilePath.equals(otherUserPrefs.aliasFilePath)
                && defaultRestockThresholdValue == otherUserPrefs.defaultRestockThresholdValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, productsFilePath, aliasFilePath,
                defaultRestockThresholdValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nProducts file location: " + productsFilePath);
        sb.append("\nAlias file location: " + aliasFilePath);
        sb.append("\nDefault restock threshold: " + defaultRestockThresholdValue);
        return sb.toString();
    }

}
