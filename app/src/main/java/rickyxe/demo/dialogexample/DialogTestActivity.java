package rickyxe.demo.dialogexample;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rickyxe.demo.R;


public class DialogTestActivity extends AppCompatActivity {

    Button btnDialogA;
    Button btnDialogB;
    Button btnDialogC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        btnDialogA = findViewById(R.id.btn_dialog_test_1);
        btnDialogA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialog1.newInstance()
                        .setTitle("Caution")
                        .setMessage("This is a dialog override onCreateDialog")
                        .setPositiveButtonListener("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButtonListener("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButtonListener("TODO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setDialogCancelable(false)
                        .show(getFragmentManager(), "dialog1");
            }
        });

        btnDialogB = findViewById(R.id.btn_dialog_test_2);
        btnDialogB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialog2.newInstance("Caution", "This is a dialog override onCreateView")
                .show(getFragmentManager(), "dialog2");
            }
        });

        btnDialogC = findViewById(R.id.btn_dialog_test_3);
        btnDialogC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDialog3 dialog3 = SimpleDialog3.newInstance();
                dialog3.setCustomView(getExtraView(dialog3))
                        .setDialogCancelable(false)
                        .show(getFragmentManager(), "dialog3");
            }
        });
    }

    private View getExtraView(final DialogFragment dialog) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.layout_simple_dialog, null);
        TextView title = rootView.findViewById(R.id.dialog_title);
        TextView content = rootView.findViewById(R.id.dialog_content);
        Button cancel = rootView.findViewById(R.id.dialog_cancel);
        Button ok = rootView.findViewById(R.id.dialog_ok);
        title.setText("Custom view title");
        content.setText("Custom view content !!!");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }
}
