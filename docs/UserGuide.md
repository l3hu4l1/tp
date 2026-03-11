---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# VendorVault User Guide

VendorVault is a **desktop app for managing your vendors and inventory all in one place**. It combines the speed of typing commands with the simplicity of a visual interface, allowing you to update products, their quantities, track vendors, and organise their contacts quickly and efficiently, all optimised for use via a Command Line Interface (CLI).

Spend less time searching through spreadsheets and switching between apps. VendorVault keeps your business information organised so you can focus on what matters most: growing your business.

<!-- * Table of Contents -->
<page-nav-print />

<br>

--------------------------------------------------------------------------------------------------------------------

<br>

## Quick start

Follow these steps to get VendorVault up and running:

1. Install Java (one-time setup)
   * VendorVault requires Java `17` or above to run. Full guide for installation [here](https://se-education.org/guides/tutorials/javaInstallation.html)
   * If you are familiar with the installation process, you can download it directly [here](https://www.oracle.com/asean/java/technologies/downloads/).<br>
   *  **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
<br><br>
1. Download the latest version of VendorVault [here](https://github.com/AY2526S2-CS2103T-W08-2/tp).
    * Specifically, choose to download the `.jar` file.
    * If necessary, move the file to a folder you want to use as the _home folder_ for VendorVault.
<br><br>
1. Open Command Prompt (Windows) or Terminal (Mac/Linux) and run the following commands
    ```
   cd PATH_TO_FOLDER_CONTAINING_JAR_FILE
   java -jar vendorvault.jar
    ```
   Replace `PATH_TO_FOLDER_CONTAINING_JAR_FILE` with the actual path to the folder you put the jar file in. For example, if you put the jar file in `C:\Users\John\Downloads`, you would run:
   ```
   cd C:\Users\John\Downloads
   java -jar vendorvault.jar
   ```
   If you are using a Mac and have the jar file in your Downloads folder, you can run:
   ```
   cd ~/Downloads
   java -jar vendorvault.jar
   ```
   VendorVault should start up and you should see a GUI similar to the below in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
<br><br>
1. Now, we're ready to use the app! At the top left of the app, you should see a command box with the text `Type a command here...`. This is where you can type in commands to interact with the app. You can also access the list of available commands by clicking on the `Help` menu at the top of the app or by pressing `F1` on your keyboard.
<br><br>
Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/TechSource Electronics p/61234567 e/sales@techsource.com a/15 Kallang Way, Singapore` : Adds a vendor contact named `TechSource Electronics` to VendorVault.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits VendorVault.

1. Refer to the [Features](#features) below for details of each command. Or [Command Summary](#command-summary) for a quick summary of all commands.

<br>

--------------------------------------------------------------------------------------------------------------------

<br>

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Managing Vendor Contacts

#### Adding a contact: `add`

Adds a contact to VendorVault.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<panel header="Why isn't it working?" type="seamless">
You can transfer your VendorVault data by copying the data file
from your old computer to the new one.
</panel>

A contact can have multiple phone numbers. To add multiple phone numbers, use:
`add n/NAME p/PHONE_NUMBER_1 [([SPECIFICATIONS)], p/PHONE_NUMBER_2 [(SPECIFICATIONS)] e/EMAIL a/ADDRESS [t/TAG]…​`<br>

<box type="tip" seamless>

**Tip:** A contact can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/CompanyName p/61234567 (Office), 87654321 (HP) e/contact@company.com a/123, Clementi Rd, 1234665 t/business`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

Duplicate detection is based on the email and phone number (and phone numbers excluding specification split according to `,`). Hence, adding a contact with the same email and phone number as an existing contact will be rejected.
More specifically, the following are considered duplicates of each other (due to one duplicate phone number):
* `add n/CompanyName p/61234567, 98765432 e/contact@company.com a/123, Clementi Rd, 1234665 t/business`
* `add n/CompanyName p/61234567, 12345678 e/contact@company.com a/123, Clementi Rd, 1234665 t/business`

#### Listing all contacts : `list`

Shows a list of all contacts in the address book.

Format: `list`

#### Editing a contact : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.

#### Locating contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* contacts matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

#### Deleting a contact : `delete`

Deletes the specified contact from the address book.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

### Managing Inventory

#### Adding a product: `addproduct`

Adds a product to the inventory.

Format: `addproduct id/IDENTIFIER n/NAME [q/QUANTITY]`

<box type="tip" seamless>

**Tip:** If quantity is not specified, it will default to 0.
</box>

Examples:
* `addproduct id/SKU-1003 n/Tray of Eggs q/30`
* `addproduct id/Pr1 n/HP LaserJet (M428fdw) q/5`
* `addproduct id/DE/5 n/PlayStation`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Undoing the previous command : `undo`

Undoes the previous command that changed the data.

Format: `undo`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: I accidentally deleted a contact/product. Can I undo that?<br>
**A**: Yes, you can undo the previous command that changed the data by using the `undo` command. For example, if you accidentally deleted a contact, simply enter `undo` and the contact will be restored.

**Q**: I edited the data file directly and now VendorVault is not working. What should I do?<br>
**A**: If you edited the data file and it caused VendorVault to behave unexpectedly, you can try the following steps:
1. Restore from backup: If you made a backup of the data file before editing, you can restore the original data file by replacing the edited file with the backup.
2. Start with a new data file: If you do not have a backup, you can delete the existing data file (or move it to a different location for safekeeping) and start VendorVault again. This will create a new, empty data file.

**Q**: How do I transfer my data to another Computer?<br>
**A**: You can transfer your VendorVault data by two files:
* Install VendorVault on the new computer (follow the [Quick Start](#quick-start) guide).
* Open the folder where VendorVault's `.jar` file is stored on your old computer.
* Look for the data files created by VendorVault (this file contains all your vendors and inventory).
    * Specifically, look for the `data` folder created by VendorVault, and the data file named `addressbook.json` and `inventory.json` inside that folder.
* Copy this data file to a USB drive or cloud storage (e.g., Google Drive, Dropbox).
* On the new computer, open the VendorVault folder.
* Replace the empty data file there with the one you copied from your old computer.
* Start VendorVault — your data should now appear exactly as before.

--------------------------------------------------------------------------------------------------------------------

## Troubleshooting

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Command                                                                | Example                                                                                                    | What it does                             |
|--------------------|------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------|
| **Add Contact**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ `               | `add n/TechSource Electronics p/61234567 e/sales@techsource.com a/15 Kallang Way, Singapore t/electronics` | Adds vendor contact                      |
| **Edit Contact**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` | `edit 2 n/TechSource Electronics p/61234567`                                                               | Edits specified fields of vendor contact |
| **Delete Contact** | `delete INDEX`                                                         | `delete 3`                                                                                                 | Deletes contact at index specified       |
| **List**           | `list`                                                                 |                                                                                                            | Lists all contacts                       |
| **Find Contact**   | `find KEYWORD [MORE_KEYWORDS]`                                         | `find TechSource`                                                                                          | Lists all contacts matching `KEYWORD`    |
| **Clear Contacts** | `clear`                                                                |                                                                                                            | Clears all contacts                      |
| **Add product**    | `addproduct id/IDENTIFIER n/NAME [q/QUANTITY]`e.g.,                    | `addproduct id/SKU-1003 n/Tray of Eggs q/30 `                                                              | Adds product                             |
| **Undo**           | `undo`                                                                 |                                                                                                            | Undoes previous command                  |
| **Help**           | `help`                                                                 |                                                                                                            |                                          |
