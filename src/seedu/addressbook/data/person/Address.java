package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final int BLOCK_INDEX = 0;
    public static final int STREET_INDEX = 1;
    public static final int UNIT_INDEX = 2;
    public static final int POSTAL_CODE_INDEX = 3;

    public final String value;
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        splitAddress(address);
        this.value = this.toString();
    }

    private void splitAddress(String address) {
        ArrayList<String> detailedAddress = new ArrayList<>(Arrays.asList(address.split(",")));
        if (detailedAddress.size() > BLOCK_INDEX) {
            this.block = new Block(detailedAddress.get(BLOCK_INDEX));
        } else {
            this.block = new Block("");
        }
        if (detailedAddress.size() > STREET_INDEX) {
            this.street = new Street(detailedAddress.get(STREET_INDEX));
        } else {
            this.street = new Street("");
        }
        if (detailedAddress.size() > UNIT_INDEX) {
            this.unit = new Unit(detailedAddress.get(UNIT_INDEX));
        } else {
            this.unit = new Unit("");
        }
        if (detailedAddress.size() > POSTAL_CODE_INDEX) {
            this.postalCode = new PostalCode(detailedAddress.get(POSTAL_CODE_INDEX));
        } else {
            this.postalCode = new PostalCode("");
        }
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String toString = block.getValue();
        if (!street.getValue().equals("")) {
            toString = toString + "," + street.getValue();
        }
        if (!unit.getValue().equals("")) {
            toString = toString + "," + unit.getValue();
        }
        if (!postalCode.getValue().equals("")) {
            toString = toString + "," + postalCode.getValue();
        }
        return toString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, street, unit, postalCode);
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
