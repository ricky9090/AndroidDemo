package rickyxe.demo.dialogexample;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import rickyxe.demo.R;


/**
 * 覆写 {@link DialogFragment#onCreate(Bundle)} 方法实现dialog
 */
public class SimpleDialog2 extends DialogFragment {

    private String dialogTitle;
    private String dialogContent;

    public static SimpleDialog2 newInstance(String title, String content) {
        SimpleDialog2 dialog = new SimpleDialog2();
        dialog.dialogTitle = title;
        dialog.dialogContent = content;
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.from(getActivity()).inflate(R.layout.layout_simple_dialog, container, false);
        TextView title = rootView.findViewById(R.id.dialog_title);
        TextView content = rootView.findViewById(R.id.dialog_content);
        Button cancel = rootView.findViewById(R.id.dialog_cancel);
        Button ok = rootView.findViewById(R.id.dialog_ok);

        title.setText(dialogTitle);
        content.setText(dialogContent);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
