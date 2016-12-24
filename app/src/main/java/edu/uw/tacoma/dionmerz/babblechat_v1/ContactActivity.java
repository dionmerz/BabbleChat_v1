package edu.uw.tacoma.dionmerz.babblechat_v1;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import edu.uw.tacoma.dionmerz.babblechat_v1.contact.ContactItem;

public class ContactActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener,
        ContentDetailFragment.OnFragmentInteractionListener {


    final static int PERM_CONTACTS_CODE = 143;

    ArrayList<ContactItem> myContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        // Checks the permission of reading the list of contacts from the mobile device.
        myContacts = new ArrayList<>();
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS);

        boolean permissionGranted = permissionCheck == PackageManager.PERMISSION_GRANTED;


        // If the permission is not already granted, ask for permission to access.
        if (!permissionGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERM_CONTACTS_CODE);
        } else {
            showContacts();
        }


        if (findViewById(R.id.fragment_container) != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("contactList", myContacts);

            ContactFragment fragment = new ContactFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }


    }

    /**
     * Builds a list of contacts after gaining access to read
     * contacts from the mobile device.
     */
    private void showContacts() {



        String currentNumber = "0";

        ContentResolver contentResolver = getContentResolver();


        // Creates a cursor object to query the Contacts list.
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


            // Creates a cursor object to query the phone number information of the specific contact.
            Cursor phoneNums = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);


            // Moves through each phone number that the current contact has. (mobile) (home)
            while (phoneNums.moveToNext()) {
                int type = phoneNums.getInt(phoneNums.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));


                switch (type) {
                    // If the type of phone number is Mobile then get the number.
                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                        currentNumber = phoneNums.getString(phoneNums.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                }
            }
            // Build a contact and add to list of contacts.
            ContactItem singleContact = new ContactItem(id, name, currentNumber);
            myContacts.add(singleContact);
            phoneNums.close();

        }
        cursor.close();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERM_CONTACTS_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    showContacts();

                } else {

                }
                return;
            }

        }
    }


    @Override
    public void onListFragmentInteraction(ContactItem item) {

        ContentDetailFragment contentDetailFragment;

        contentDetailFragment = new ContentDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ContentDetailFragment.DETAIL_ARG, item);
        args.putSerializable("contactList", myContacts);
        contentDetailFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, contentDetailFragment)
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
