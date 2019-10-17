package rickyxe.demo.recyclerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rickyxe.demo.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SubListViewHolder> {

    private Context mContext;
    final private List<List<String>> dataList = new ArrayList<>();
    final private List<RecyclerView.LayoutManager> layoutManagerList = new ArrayList<>();
    final private List<SubAdapter> subAdapterList = new ArrayList<>();

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        mockData();
    }

    @NonNull
    @Override
    public SubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_recycler_item, parent, false);
        return new SubListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubListViewHolder holder, int position) {
        RecyclerView.LayoutManager layoutManager = layoutManagerList.get(position);
        SubAdapter subAdapter = subAdapterList.get(position);

        holder.title.setText("This is Section " + position);
        holder.subRecyclerView.setLayoutManager(layoutManager);
        holder.subRecyclerView.setAdapter(subAdapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class SubListViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RecyclerView subRecyclerView;

        public SubListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.section_title);
            subRecyclerView = itemView.findViewById(R.id.item_recycler_list);
        }
    }

    private void mockData() {
        for (int i = 0; i < 10; i++) {
            List<String> subList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                subList.add("AppName: " + j);
            }
            dataList.add(subList);
            layoutManagerList.add(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
            SubAdapter adapter = new SubAdapter(mContext);
            adapter.addAppList(subList);
            subAdapterList.add(adapter);
        }
    }
}
