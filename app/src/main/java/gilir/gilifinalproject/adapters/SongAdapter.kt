package gilir.gilifinalproject.adapters


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import gilir.gilifinalproject.R
import gilir.gilifinalproject.databinding.SongItemBinding
import gilir.gilifinalproject.models.songsapi.Song
import gilir.gilifinalproject.ui.ViewModelSongUpdater
import java.util.concurrent.TimeUnit

class SongAdapter(private val songs: List<Song>, private val viewModel: ViewModelSongUpdater) :
    Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(val binding: SongItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val context = LayoutInflater.from(parent.context)
        val binding = SongItemBinding.inflate(context, parent, false)
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]

        fun intToTime(duration: Int): String {
            val min = TimeUnit.SECONDS.toMinutes(duration.toLong())
            val sec = duration - TimeUnit.MINUTES.toSeconds((min))
            return String.format("%02d:%02d", min, sec)
        }

        with(holder.binding) {
            tvSongName.text = song.title
            tvArtist.text = song.artist?.name + ", " + song.duration?.let { intToTime(it) }
            tvLink.setOnClickListener {
                val uri = Uri.parse(song.link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(it.context, intent, null)
            }
            if (song.artist?.picture != null) {
                Picasso.get().load(song.artist.picture).into(ivCover)
            } else
                Picasso.get().load(song.album?.cover).into(ivCover)

            if (song.isFavorite == true) {
                ivFavoriteBtn.setImageResource(R.drawable.ic_full_heart_favorite)
            }

            ivFavoriteBtn.setOnClickListener {
                if (song.isFavorite == true) {
                    ivFavoriteBtn.setImageResource(R.drawable.ic_outline_favorite)
                } else {
                    ivFavoriteBtn.setImageResource(R.drawable.ic_full_heart_favorite)
                }
                favoriteSongClick(song)
            }
        }
    }

    private fun favoriteSongClick(song: Song) {
        Log.d("TAG", "onViewCreated: $song")
        song.isFavorite = !(song.isFavorite ?: false)
        viewModel.updateSong(song)
        viewModel.addSongToFavorites(song)
    }

    override fun getItemCount(): Int = songs.size
}