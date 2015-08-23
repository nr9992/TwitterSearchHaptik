package com.neelraj.twittersearchhaptik.ViewHolder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neelraj.twittersearchhaptik.Model.TwitterUser;
import com.neelraj.twittersearchhaptik.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Neel Raj on 23-08-2015.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    private View itemView;

    private TextView username,fullname,description,follower_count,following_count,tweet_count;
    private ImageView banner;
    private CircleImageView userpic;
    private TwitterUser reference;


    public UserViewHolder(View itemView) {
        super(itemView);

        this.itemView=itemView;

        username = (TextView) itemView.findViewById(R.id.username);
        fullname = (TextView) itemView.findViewById(R.id.name);
        description = (TextView) itemView.findViewById(R.id.summary);
        follower_count = (TextView) itemView.findViewById(R.id.nofollowers);
        following_count = (TextView) itemView.findViewById(R.id.nofollowing);
        tweet_count = (TextView) itemView.findViewById(R.id.notweets);
        userpic = (CircleImageView) itemView.findViewById(R.id.userimage);
        banner = (ImageView) itemView.findViewById(R.id.banner);

        userpic.setBorderColor(Color.argb(255,255,255,255));
        userpic.setBorderWidth(10);

    }

    public void update (TwitterUser twitterUser){
        reference = twitterUser;
        username.setText("@"+twitterUser.getScreenName());
        fullname.setText(twitterUser.getName());
        description.setText(twitterUser.getDescription());
        follower_count.setText(Integer.toString(twitterUser.getFollower_count()));
        following_count.setText(Integer.toString(twitterUser.getFriends_count()));
        tweet_count.setText(Integer.toString(twitterUser.getStatus_count()));

        Picasso.with(itemView.getContext()).load(twitterUser.getProfileImageUrl())
                .into(userpic);
        Picasso.with(itemView.getContext()).load(twitterUser.getProfileBannerUrl()).fit().centerCrop()
                .into(banner);
    }
}
