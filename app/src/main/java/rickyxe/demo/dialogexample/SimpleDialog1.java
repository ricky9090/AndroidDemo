package rickyxe.demo.dialogexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * 覆写 {@link DialogFragment#onCreateDialog(Bundle)} 方法实现dialog
 */
public class SimpleDialog1 extends DialogFragment {

    private String dialogTitle = null;
    private String dialogMessage = null;

    private String dialogPositiveStr = null;
    private String dialogNegativeStr = null;
    private String dialogNeutralStr = null;
    private DialogInterface.OnClickListener positiveListener = null;
    private DialogInterface.OnClickListener negativeListener = null;
    private DialogInterface.OnClickListener neutralListener = null;


    public static SimpleDialog1 newInstance() {
        return new SimpleDialog1();
    }

    public SimpleDialog1 setTitle(String title) {
        this.dialogTitle = title;
        return this;
    }

    public SimpleDialog1 setMessage(String msg) {
        this.dialogMessage = msg;
        return this;
    }

    public SimpleDialog1 setPositiveButtonListener(String positiveStr, DialogInterface.OnClickListener listener) {
        this.dialogPositiveStr = positiveStr;
        this.positiveListener = listener;
        return this;
    }

    public SimpleDialog1 setNegativeButtonListener(String negativeStr, DialogInterface.OnClickListener listener) {
        this.dialogNegativeStr = negativeStr;
        this.negativeListener = listener;
        return this;
    }

    public SimpleDialog1 setNeutralButtonListener(String neutralStr, DialogInterface.OnClickListener listener) {
        this.dialogNeutralStr = neutralStr;
        this.neutralListener = listener;
        return this;
    }

    public SimpleDialog1 setDialogCancelable(boolean flag) {
        this.setCancelable(flag);
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(dialogTitle)
                .setMessage(dialogMessage);
        if (positiveListener != null) {
            builder.setPositiveButton(dialogPositiveStr, positiveListener);
        }
        if (negativeListener != null) {
            builder.setNegativeButton(dialogNegativeStr, negativeListener);
        }
        if (neutralListener != null) {
            builder.setNeutralButton(dialogNeutralStr, neutralListener);
        }
        return builder.create();
    }
}
