package fpt.edu.layout_exercise;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ItemHolder> {

    private final Activity activity;
    List<Integer> images;

    public ActorAdapter(Activity activity, List<Integer> images) {
        this.activity = activity;
        this.images = images;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.recycler_item_actor, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        int image = images.get(position);
        holder.ivImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
