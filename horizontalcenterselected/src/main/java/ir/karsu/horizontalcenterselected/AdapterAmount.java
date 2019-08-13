package ir.karsu.horizontalcenterselected;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trah3 on 3/7/2018.
 */

public class AdapterAmount extends RecyclerView.Adapter<AdapterAmount.viewHolder> {
    Context context;
    List<String> Items;
    ClickItemAmount onClickListenerItemAmount;
    ItemListener itemListener;
    public int mSelectedItem = -1;

    public static final int VIEW_TYPE_PADDING = 1;
    public static final int VIEW_TYPE_PADDING_Left = 3;
    public static final int VIEW_TYPE_ITEM = 2;
    private int paddingWidth;

    int layout;
    int bgitemselected;
    int bgitem;

    public AdapterAmount(Context context, int bgitemselected, int bgitem, ClickItemAmount onClickListenerItemAmount, ItemListener itemListener, int padding, int layout) {
        this.context = context;
        Items = new ArrayList<>();
        this.itemListener = itemListener;
        this.onClickListenerItemAmount = onClickListenerItemAmount;
        this.paddingWidth = padding;
        this.layout = layout;
        this.bgitemselected = bgitemselected;
        this.bgitem = bgitem;
    }

    public void setItems(List<String> Items) {
        this.Items.clear();
        this.Items.addAll(Items);
        notifyDataSetChanged();
    }


    @Override
    public AdapterAmount.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(this.layout, parent, false);
        if (viewType == VIEW_TYPE_PADDING) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(0, 0, paddingWidth, 0);
            view.setLayoutParams(layoutParams);
        } else if (viewType == VIEW_TYPE_PADDING_Left) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(paddingWidth, 0, 0, 0);
            view.setLayoutParams(layoutParams);
        } else {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            view.setLayoutParams(layoutParams);
        }
        return new AdapterAmount.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterAmount.viewHolder holder, final int position) {
        holder.itemView.setVisibility(View.VISIBLE);
        holder.title.setText(String.valueOf(Items.get(position)));
        if (position == mSelectedItem) {
            holder.title.setBackgroundResource(bgitemselected);
        } else {
            holder.title.setBackgroundResource(bgitem);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenerItemAmount.ClickItem(position);
            }
        });

    }

    public void setSelecteditem(int selecteditem) {
        this.mSelectedItem = selecteditem;
        if (itemListener != null)
            this.itemListener.getValue(Items.get(selecteditem), selecteditem);
        notifyDataSetChanged();
    }

    public String getmSelectedItem() {
        return Items.get(mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_PADDING_Left;
        } else if (position == Items.size() - 1) {
            return VIEW_TYPE_PADDING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}