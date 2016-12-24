package edu.uw.tacoma.dionmerz.babblechat_v1.contact;

import java.io.Serializable;

/**
 * The ContactItem class represents a single contact from the
 * mobile devices list of contacts. This information includes
 * Name, and mobile number.
 *
 * @author Andrew Merz
 */

public class ContactItem implements Serializable {
    public final String id;
    public final String name;
    public final String number;

    public ContactItem(String id, String theName, String theNumber) {
        this.id = id;
        this.name = theName;
        this.number = theNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}