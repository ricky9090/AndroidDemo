package rickyxe.demo.recyclerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rickyxe.demo.R;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.AppItemViewHolder> {

    private Context mContext;
    final private List<String> appList = new ArrayList<>();

    public SubAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAppList(List<String> list) {
        appList.addAll(list);
    }

    @NonNull
    @Override
    public AppItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_app_item, parent, false);
        return new AppItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppItemViewHolder holder, int position) {
        String appNameStr = appList.get(position);
        holder.appName.setText(appNameStr);
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    static class AppItemViewHolder extends RecyclerView.ViewHolder {
        TextView appName;

        public AppItemViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.app_name_text);

        }
    }
}
