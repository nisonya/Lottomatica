package lotto.matica.scommesse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ExersiseAdapter extends RecyclerView.Adapter<ExersiseAdapter.ExersiseViewHolder>{
    private List<Exersise> mExersise;
    private static int viewHolderCount;
    private int numberItems;
    public ExersiseAdapter(List<Exersise> exersises){
        mExersise =exersises;
        viewHolderCount = 0;
    }
    @NonNull
    @Override
    public ExersiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListitem = R.layout.exersise_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(layoutIdForListitem, parent, false);
        ExersiseAdapter.ExersiseViewHolder viewHolder = new ExersiseAdapter.ExersiseViewHolder(contactView);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExersiseViewHolder holder, int position) {
        holder.bind(position);
        ImageView imageView = ((ExersiseViewHolder) holder).photo;
        holder.photo.setImageResource(R.drawable.chest);
        Exersise exersiseItem = mExersise.get(position);
        holder.name.setText(String.valueOf(exersiseItem.getName()));
        holder.description.setText(exersiseItem.getDescription());
        Glide
                .with(holder.itemView.getContext())
                .load(String.valueOf(exersiseItem.getPhoto()))
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return mExersise.size();
    }

    class ExersiseViewHolder extends RecyclerView.ViewHolder {

        TextView name, description;
        ImageView photo;

        public ExersiseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_desc);
            photo = itemView.findViewById(R.id.iv_photo);
        }
        void bind(int listIndex){
        }
    }
}
