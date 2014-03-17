package com.example.searchy.Loaders;

import java.util.ArrayList;
import com.example.searchy.Objects.Person;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class PersonLoader {
	private Context con;
	
	public PersonLoader(Context con) {
		this.con = con;
	}

	public ArrayList<Person> fetchContacts() {

		String phoneNumber = null;
		String email = null;

		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		String _ID = ContactsContract.Contacts._ID;
		String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
		String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

		Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

		Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
		String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
		String DATA = ContactsContract.CommonDataKinds.Email.DATA;
		
		ArrayList<Person> personList = new ArrayList<Person>();
		Person person = new Person();

		ContentResolver contentResolver = con.getContentResolver();

		Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);	

		// Loop for every contact in the phone
		if (cursor.getCount() > 0) {

			while (cursor.moveToNext()) {

				String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
				String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));
				
				person.setPersonId(contact_id);
				person.setName(name);
				
				int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

				if (hasPhoneNumber > 0) {

					// Query and loop for every phone number of the contact
					Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);

					while (phoneCursor.moveToNext()) {
						phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
						person.addPhone(phoneNumber);
					}

					phoneCursor.close();

					// Query and loop for every email of the contact
					Cursor emailCursor = contentResolver.query(EmailCONTENT_URI,	null, EmailCONTACT_ID+ " = ?", new String[] { contact_id }, null);

					while (emailCursor.moveToNext()) {

						email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
						person.setEmail(email);
					}
					emailCursor.close();
				}
				personList.add(person);
			}

 		}
		return personList;
	}
}
