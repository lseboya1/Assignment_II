package za.ac.cut.assignment_ii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Objects;

public class New_Contact extends AppCompatActivity {

    EditText contact_name;
    EditText phone_number;
    EditText location;
    EditText wed_address;

    UserContact userContact;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__contact);

        contact_name = (EditText)findViewById(R.id.contact_name);
        phone_number = (EditText)findViewById(R.id.phone_number);
        location = (EditText)findViewById(R.id.location);
        wed_address = (EditText)findViewById(R.id.wed_address);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserContact");

    }

    public void saveInfo(View view) {


        final String name = contact_name.getText().toString();
        final String phone = phone_number.getText().toString();
        final String location1 = location.getText().toString();
        final String web = wed_address.getText().toString();

        if (!(name.isEmpty() || phone.isEmpty() || location1.isEmpty() || web.isEmpty()))

        {

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    userContact = dataSnapshot.getValue(UserContact.class);

                    if (Objects.equals(name, userContact.getName())) {

                        String key = databaseReference.push().getKey();
                        UserContact userContact = new UserContact(name, location1, phone, web, key);
                        Map<String, Object> userContactVelue = userContact.toMap();
                        databaseReference.child(key).updateChildren(userContactVelue);

                        Toast.makeText(New_Contact.this, "Contact successfully added!", Toast.LENGTH_SHORT).show();

                        contact_name.setText(null);
                        phone_number.setText(null);
                        location.setText(null);
                        wed_address.setText(null);
                    } else {
                        Toast.makeText(New_Contact.this, "A contact with this name already exists!" + "\n" +
                                "Please enter a new contact!", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
    }

    public void goBack(View view){
        finish();
    }
}
