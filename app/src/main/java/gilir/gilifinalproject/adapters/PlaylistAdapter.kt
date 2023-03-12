package gilir.gilifinalproject.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import gilir.gilifinalproject.R
import gilir.gilifinalproject.databinding.PlaylistItemBinding
import gilir.gilifinalproject.models.playlistApi.Playlist
import gilir.gilifinalproject.ui.ARG_PLAYLIST

class PlaylistAdapter(private val playlists: List<Playlist>) : Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    class PlaylistViewHolder(val binding: PlaylistItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val context = LayoutInflater.from(parent.context)
        val binding = PlaylistItemBinding.inflate(context, parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]


        // כל מה שהיה קשור לPlaylist api ולא קשור לרדיו
//        val creationDate = playlist.creationDate.substring(0,4)
//        tvPlaylistCreationDate.text =creationDate
 //       tvPlaylistNbTracks.text ="Amount : "+ playlist.nbTracks.toString()


        with(holder.binding) {
            tvPlaylistTitle.text = playlist.title
            Picasso.get().load(playlist.picture).into(ivCover)

            tvFullPlaylist.setOnClickListener {
                val bundle = Bundle().apply {
                    putParcelable(ARG_PLAYLIST , playlist)
                }
                findNavController(it).navigate(R.id.action_homeFragment_to_fullPlaylistFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int = playlists.size
}

