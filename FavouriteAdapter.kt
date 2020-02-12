package com.example.echo.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.echo.R
import com.example.echo.Songs
import com.example.echo.fragments.SongsPlayingFragment

class FavouriteAdapter(_songDetails: ArrayList<Songs>, _context: Context) :
    RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {
    var songDetails: ArrayList<Songs>? = null
    var mContext: Context? = null

    init {
        this.songDetails = _songDetails
        this.mContext = _context
    }

    override fun getItemCount(): Int {
        if (songDetails == null)
        {
            return 0
        }
        else
        {
            return (songDetails as ArrayList<Songs>).size
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val songObject = songDetails?.get(position)
        holder.trackTitle?.text = songObject?.songTitle
        holder.trackArtist?.text = songObject?.artist
        holder.contentHolder?.setOnClickListener({
            val songsPlayingFragment= SongsPlayingFragment()
            var args= Bundle()
            args.putString("songArtist",songObject?.artist)
            args.putString("path",songObject?.songData)
            args.putString("songTitle",songObject?.songTitle)
            args.putInt("SongId",songObject?.songID?.toInt() as Int)
            args.putInt("songPosition",position)
            args.putParcelableArrayList("songData",songDetails)
            songsPlayingFragment.arguments=args
            (mContext as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.details_fragment,songsPlayingFragment)
                .addToBackStack("SongPlayingFragmentFavourite")
                .commit()
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(
            R.layout.row_custom_mainscreen_adapter,
            parent, false
        )
        return MyViewHolder(itemView)
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var trackArtist: TextView? = null
        var trackTitle: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            trackTitle = view?.findViewById(R.id.trackTitle)
            trackArtist = view?.findViewById(R.id.trackArtist)
            contentHolder = view?.findViewById(R.id.contentRow)
        }
    }
}