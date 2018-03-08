package za.ac.cut.assignment_ii;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Edit_Contact extends AppCompatActivity {

    EditText contact_name;
    EditText phone_number;
    EditText location;
    EditText wed_address;
    Button save;
    LinearLayout layout1,layout2,layout3;

    FirebaseUser firebaseUser;
     FirebaseAuth firebaseAuth;
     DatabaseReference databaseReference;
     FirebaseDatabase firebaseDatabase;
     ValueEventListener childEventListener;
    UserContact userContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__contact);

        contact_name = (EditText)findViewById(R.id.contact_name);
        phone_number = (EditText)findViewById(R.id.phone_number);
        location = (EditText)findViewById(R.id.location);
        wed_address = (EditText)findViewById(R.id.wed_address);
        save = (Button)findViewById(R.id.save);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout)findViewById(R.id.layout3);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("UserContact");

//        databaseHelper = new DatabaseHelper(getApplicationContext());
//        sqLiteDatabase = databaseHelper.getReadableDatabase();
//        cursor = databaseHelper.getInformations(sqLiteDatabase);
//
//    }
//
//    public void Search(View view){
//
//        String name = contact_name.getText().toString();
//        String phone;
//        String location1;
//        String address;
//
//        if(!(name.isEmpty())) {
//
//            if (cursor.moveToFirst()) {
//
//                do {
//                    if (Objects.equals(name, cursor.getString(1))){
//
//                        phone = cursor.getString(2);
//                        location1 = cursor.getString(0);
//                        address = cursor.getString(3);
//
//                        contact_name.setText(phone);
//                        location.setText(location1);
//                        wed_address.setText(address);
//
//                    }
//                } while (cursor.moveToNext());
//            }
//        }else {
//            Toast.makeText(this, "Please enter name!", Toast.LENGTH_SHORT).show();
//        }
    }


    public void Save(View view){

        String keyUser = userContact.getContactUID();

        final String phone = contact_name.getText().toString();
        final String location1 = location.getText().toString();
        final String web = wed_address.getText().toString();

        UserContact userContact = new UserContact(keyUser, phone, location1, web);

        databaseReference.child("phoneNumber").setValue(phone);
        databaseReference.child("location").setValue(location1);
        databaseReference.child("address").setValue(web);

        save.setVisibility(View.GONE);
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);

//        databaseReference.setValue(userContact);

    }

    public void goBack(View view){
        finish();
    }

    public void Search(View view) {


        final String name = contact_name.getText().toString();

         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                     Log.i("info", snapshot.toString());

                     userContact = snapshot.getValue(UserContact.class);

                     if(Objects.equals(name, userContact.getName())){

                         save.setVisibility(View.VISIBLE);
                         layout1.setVisibility(View.VISIBLE);
                         layout2.setVisibility(View.VISIBLE);
                         layout3.setVisibility(View.VISIBLE);

                         phone_number.setText(userContact.getPhoneNumber());
                         location.setText(userContact.getLocation());
                         wed_address.setText(userContact.getAddress());

                     }
                 }

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        databaseReference.addValueEventListener(childEventListener);
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        databaseReference.removeEventListener(childEventListener);
//    }
}
