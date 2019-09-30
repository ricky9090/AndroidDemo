package rickyxe.demo.dialogexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

/**
 * 添加自定义布局
 */
public class SimpleDialog3 extends DialogFragment {

    private String dialogPositiveStr = null;
    private String dialogNegativeStr = null;
    private String dialogNeutralStr = null;
    private DialogInterface.OnClickListener positiveListener = null;
    private DialogInterface.OnClickListener negativeListener = null;
    private DialogInterface.OnClickListener neutralListener = null;

    private View customView;

    public static SimpleDialog3 newInstance() {
        return new SimpleDialog3();
    }

    public SimpleDialog3 setPositiveButtonListener(String positiveStr, DialogInterface.OnClickListener listener) {
        this.dialogPositiveStr = positiveStr;
        this.positiveListener = listener;
        return this;
    }

    public SimpleDialog3 setNegativeButtonListener(String negativeStr, DialogInterface.OnClickListener listener) {
        this.dialogNegativeStr = negativeStr;
        this.negativeListener = listener;
        return this;
    }

    public SimpleDialog3 setNeutralButtonListener(String neutralStr, DialogInterface.OnClickListener listener) {
        this.dialogNeutralStr = neutralStr;
        this.neutralListener = listener;
        return this;
    }

    public SimpleDialog3 setDialogCancelable(boolean flag) {
        this.setCancelable(flag);
        return this;
    }

    public SimpleDialog3 setCustomView(View view) {
        this.customView = view;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(customView);
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
