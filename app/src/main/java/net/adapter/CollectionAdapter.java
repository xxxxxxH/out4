package net.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.basicmodel.R;
import net.entity.DataEntity;
import net.utils.Language;
import net.utils.OnclickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    ArrayList<DataEntity> data;
    String type;
    OnclickListener listener;
    HashMap<String, String> map = new HashMap<>();

    public void setListener(OnclickListener listener) {
        this.listener = listener;
    }

    public CollectionAdapter(ArrayList<DataEntity> data, String type) {
        this.data = data;
        this.type = type;
        map = Language.getInstance().getLanguage();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String from = Language.getInstance().getCountry(map, data.get(position).getFrom());
        String to = Language.getInstance().getCountry(map, data.get(position).getTo());
        String content = from + " - " + to + "  " + "(" + data.get(position).getFrom() + " - " + data.get(position).getTo() + ")";
        holder.textView1.setText(content);
        String before = data.get(position).getTrans_result().get(0).getSrc();
        holder.textView2.setText(before);
        String after = data.get(position).getTrans_result().get(0).getDst();
        holder.textView3.setText(after);
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener == null) {
                    return;
                }
                int pos = holder.getLayoutPosition();
                if (TextUtils.equals(type, "collection")) {
                    listener.onItemClick(holder.copy, pos, type);
                } else {
                    listener.onItemClick(holder.copy, pos, type);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView copy;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.item_tv1);
            textView2 = itemView.findViewById(R.id.item_tv2);
            textView3 = itemView.findViewById(R.id.item_tv3);
            copy = itemView.findViewById(R.id.item_img);
        }
    }
}
