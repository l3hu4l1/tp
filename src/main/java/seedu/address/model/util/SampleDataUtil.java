package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.product.Identifier;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.RestockThreshold;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code VendorVault} with sample data.
 */
public class SampleDataUtil {
    private static seedu.address.model.person.Name personName(String value) {
        return new seedu.address.model.person.Name(value);
    }

    private static seedu.address.model.product.Name productName(String value) {
        return new seedu.address.model.product.Name(value);
    }

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(personName("Adafruit Industries"), new Phone("64601234"), new Email("support@adafruit.com"),
                    new Address("151 Varick St, New York, NY 10013, USA"),
                    getTagSet("IOT")),
            new Person(personName("Cytron Technologies Pte. Ltd"), new Phone("65480668 (Office), 91234567 (Sales)"), new Email("sg.sales@cytron.io"),
                    new Address("09 Collyer Quay"),
                    getTagSet("electronics")),
            new Person(personName("TechSource Electronics"), new Phone("61234567"), new Email("sales@techsource.com"),
                    new Address("15 Kallang Way, Singapore"),
                    getTagSet("electronics", "IOT")),
            new Person(personName("Synapse Supply"), new Phone("+65 62981234, +60 169876543"), new Email("hello@synapse.sg"),
                    new Address("3 Kaki Bukit Road, Singapore 415978"),
                    getTagSet("wholesale")),
            new Person(personName("Rochor & Co."), new Phone("63330881, 69041288"), new Email("support.rochor@yahoo.com"),
                    new Address("Sim Lim Square #05-55"),
                    getTagSet()),
            new Person(personName("ByteLabs"), new Phone("65607845 (Mike)"), new Email("bytelabs@gmail.com"),
                    new Address("Ubi Techpark Lot 9"),
                    getTagSet("partner", "refurb")),
            new Person(personName("Soonheng Retail & Logistics"), new Phone("+60 22 3650 7080"), new Email("orders" +
                    "@soonheng.com.my"), new Address("Jalan Ciku No. 03, Kluang"), getTagSet()),
            new Person(personName("Audio House"), new Phone("68412122"), new Email("sales@ah.com"), new Address(
                    "Audio House Building #01-01"), getTagSet("electronics", "home"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Product[] getSampleProducts() {
        return new Product[] {
            new Product(new Identifier("DS-1001"), productName("AA Batteries 4 Pack"),
                    new Quantity("10"), new RestockThreshold("11")),
            new Product(new Identifier("DS-1002"), productName("LED Bulb 9W"),
                    new Quantity("45"), new RestockThreshold("40")),
            new Product(new Identifier("DS-1003"), productName("Toothpaste 120g"),
                    new Quantity("38"), new RestockThreshold("38")),
            new Product(new Identifier("DS-1004"), productName("Dish Sponge 3 Pack"),
                    new Quantity("52"), new RestockThreshold("20")),
            new Product(new Identifier("DS-1005"), productName("Notebook A5"),
                    new Quantity("70"), new RestockThreshold("0")),
            new Product(new Identifier("DS-1006"), productName("Laundry Detergent 1L"),
                    new Quantity("24"), new RestockThreshold("37"))
        };
    }

    public static ReadOnlyInventory getSampleInventory() {
        Inventory sampleInv = new Inventory();
        for (Product sampleProduct : getSampleProducts()) {
            sampleInv.addProduct(sampleProduct);
        }
        return sampleInv;
    }
}
