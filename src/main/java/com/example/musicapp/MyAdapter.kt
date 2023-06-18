package com.example.musicapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context:Activity,val trackArrayList:List<Track>):
        RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        var songname:TextView
        var image:ShapeableImageView

        init {
            songname=itemView.findViewById(R.id.SongName)
            image=itemView.findViewById(R.id.SongImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //iss ma view create karna hai ha

        val itemView = LayoutInflater.from(context).inflate(R.layout.eachrow,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return trackArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentSong=trackArrayList[position]
        holder.songname.text=currentSong.name
        //image view ko data dana hai ab

        val imageurl = currentSong.album.images[1].url
        Picasso.get().load(imageurl).into(holder.image)

        holder.itemView.setOnClickListener {
            val name=currentSong.name
            val image=currentSong.album.images[1].url

            val songUrl=currentSong.preview_url
            val intent=Intent(context,PlayerScreen::class.java)

            intent.putExtra("songName",name)
            intent.putExtra("songImage",image)
            intent.putExtra("url",songUrl)

            context.startActivity(intent)

        }

    }


}