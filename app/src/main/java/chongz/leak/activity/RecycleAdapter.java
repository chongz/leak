package chongz.leak.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import chongz.leak.R;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> items = new ArrayList<>();

    public RecycleAdapter(Context context) {
        this.mContext = context;

        items.add("1");
        items.add("2");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(mContext).inflate(R.layout
            .activity_recycle_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindRecycleViewHolder((RecycleViewHolder) holder, position);
    }

    private void bindRecycleViewHolder(RecycleViewHolder holder, int position) {
        String data = items.get(position);
        holder.recycleTextView.setText(data);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("11", "onClick: 111");
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recycle_text_view)
        TextView recycleTextView;

        @BindView(R.id.recycle_view_linearlayout)
        LinearLayout linearLayout;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
