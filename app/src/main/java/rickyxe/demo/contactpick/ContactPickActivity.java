package rickyxe.demo.contactpick;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rickyxe.demo.R;


public class ContactPickActivity extends AppCompatActivity {

    protected static final int CODE_CONTACT_PICK = 1;

    Button contactPickBtn;
    TextView urlText;
    TextView nameText;
    TextView phoneText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_pick);

        contactPickBtn = findViewById(R.id.btn_contact_pick);
        contactPickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, CODE_CONTACT_PICK);
            }
        });

        urlText = findViewById(R.id.contact_pick_url);
        nameText = findViewById(R.id.contact_pick_name);
        phoneText = findViewById(R.id.contact_pick_phone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != CODE_CONTACT_PICK) {
            return;
        }
        if (resultCode != RESULT_OK) {
            return;
        }
        if (data == null) {
            return;
        }

        Uri uri = data.getData();
        if (uri != null) {
            urlText.setText(uri.toString());
            Cursor cursor = getContentResolver()
                    .query(uri,
                            new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                    ContactsContract.CommonDataKinds.Phone.NUMBER},
                            null, null, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String number = cursor.getString(1);
                nameText.setText(nameText.getText() + ", " + name);
                phoneText.setText(phoneText.getText() + ", " + number);
            }
        }
    }
}
