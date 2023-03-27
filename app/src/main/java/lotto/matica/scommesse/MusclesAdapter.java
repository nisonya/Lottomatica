package lotto.matica.scommesse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.List;

public class MusclesAdapter extends RecyclerView.Adapter<MusclesAdapter.MusclesViewHoler> {

    interface OnMusclesClickListener{
        void onMusclesClick(Muscles musclesItem);
    }
    private List<Muscles> mMuscles;
    private final OnMusclesClickListener onClickListener;
    private static int viewHolderCount;
    private int numberItems;
    private Context parent;
    public MusclesAdapter(List<Muscles> muscles, Context parent, OnMusclesClickListener onClickListener){
        mMuscles =muscles;
        this.onClickListener = onClickListener;
        viewHolderCount = 0;
        this.parent = parent;
    }


    @NonNull
    @Override
    public MusclesViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListitem = R.layout.muscles_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(layoutIdForListitem, parent, false);
        MusclesViewHoler viewHolder = new MusclesViewHoler(contactView);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusclesViewHoler holder, int position) {
        holder.bind(position);
        Muscles musclesItem = mMuscles.get(position);
        holder.chipMuscle.setText(String.valueOf(musclesItem.getName()));
        holder.chipMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int positionIndex = getAbsoluteAdapterPosition();
                onClickListener.onMusclesClick(musclesItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMuscles.size();
    }

    class MusclesViewHoler extends RecyclerView.ViewHolder {

        Chip chipMuscle;

        public MusclesViewHoler(@NonNull View itemView) {
            super(itemView);
            chipMuscle = itemView.findViewById(R.id.chip_item);

        }
        void bind(int listIndex){
        }
    }
}

