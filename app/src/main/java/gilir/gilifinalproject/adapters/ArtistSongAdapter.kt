package gilir.gilifinalproject.adapters



import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import gilir.gilifinalproject.R
import gilir.gilifinalproject.databinding.SongItemBinding
import gilir.gilifinalproject.models.artistaong.ArtistSong
import java.util.concurrent.TimeUnit

class ArtistSongAdapter(private val songs: List<ArtistSong>, val onFavoriteClicked: (ArtistSong) -> Unit) : Adapter<ArtistSongAdapter.SongViewHolder>() {

    class SongViewHolder(val binding: SongItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val context = LayoutInflater.from(parent.context)
        val binding = SongItemBinding.inflate(context, parent, false)
        return SongViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val artistSong = songs[position]

        fun intToTime(duration: Int): String {
            val min = TimeUnit.SECONDS.toMinutes(duration.toLong())
            val sec = duration-TimeUnit.MINUTES.toSeconds((min))
            return String.format("%02d:%02d",min,sec)
        }

        with(holder.binding) {
            tvSongName.text = artistSong.title
            tvArtist.text = artistSong.artist.name + ", " +intToTime(artistSong.duration)
            tvLink.setOnClickListener {
                val uri = Uri.parse(artistSong.link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(it.context,intent,null)
            }
            if(artistSong.artist.picture!=null) {
                Picasso.get().load(artistSong.artist.picture).into(ivCover)
            }else
                Picasso.get().load(artistSong.album.cover).into(ivCover)

            if(artistSong.isClicked) {
                ivFavoriteBtn.setImageResource(R.drawable.ic_full_heart_favorite)
            }



            ivFavoriteBtn.setOnClickListener {
                if(artistSong.isClicked) {
                    ivFavoriteBtn.setImageResource(R.drawable.ic_outline_favorite)
                } else {
                    ivFavoriteBtn.setImageResource(R.drawable.ic_full_heart_favorite)
                }
                onFavoriteClicked(artistSong)
            }
        }
    }

    override fun getItemCount(): Int = songs.size
}