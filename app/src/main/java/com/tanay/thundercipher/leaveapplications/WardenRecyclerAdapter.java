package com.tanay.thundercipher.leaveapplications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WardenRecyclerAdapter extends RecyclerView.Adapter<WardenRecyclerAdapter.ProfileViewHolder> {

    int[] images;
    String[] names, desig, phones, mails;

    public WardenRecyclerAdapter(int[] images, String[] names, String[] desig, String[] phones, String[] mails)
    {
        this.images = images;
        this.names = names;
        this.desig = desig;
        this.phones = phones;
        this.mails = mails;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.warden_list_layout, parent, false);
        ProfileViewHolder profileViewHolder = new ProfileViewHolder(view);

        return profileViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position)
    {
        int imageIndex = images[position];
        String nameIndex = names[position];
        String desigIndex = desig[position];
        String mailIndex = mails[position];
        String phoneIndex = phones[position];

        holder.profilePhotoImageView.setImageResource(imageIndex);
        holder.nameTextView.setText(nameIndex);
        holder.designationTextView.setText("(" + desigIndex + ")");
        holder.mailTextView.setText(mailIndex);
        holder.callTextView.setText(phoneIndex);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder{

        ImageView profilePhotoImageView;
        TextView nameTextView, designationTextView, callTextView, mailTextView;

        public ProfileViewHolder(@NonNull View itemView)
        {
            super(itemView);

            profilePhotoImageView = itemView.findViewById(R.id.wardenListProfilePhotoImageView);
            nameTextView = itemView.findViewById(R.id.wardenListNameTextView);
            designationTextView = itemView.findViewById(R.id.wardenListDesignationTextView);
            callTextView = itemView.findViewById(R.id.wardenListCallTextView);
            mailTextView = itemView.findViewById(R.id.wardenListMailTextView);
        }
    }
}
