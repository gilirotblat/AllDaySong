package gilir.gilifinalproject.adapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import gilir.gilifinalproject.R
import gilir.gilifinalproject.databinding.ArtistItemBinding
import gilir.gilifinalproject.models.artistapi.Artist
import gilir.gilifinalproject.ui.ARG_ARTIST


class ArtistAdapter(private val artists: List<Artist>) : Adapter<ArtistAdapter.ArtistViewHolder>() {

    class ArtistViewHolder(val binding: ArtistItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val context = LayoutInflater.from(parent.context)
        val binding = ArtistItemBinding.inflate(context, parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]

        with(holder.binding) {
            tvArtistName.text = artist.name
            Picasso.get().load(artist.picture).into(ivCover)

            tvArtistName.setOnClickListener {
                val bundle = Bundle().apply {
                    putParcelable(ARG_ARTIST, artist)
                }
                findNavController(it).navigate(R.id.action_homeFragment_to_fullArtistFragment,bundle)
            }
            tvFullArtistPage.setOnClickListener {
                val bundle = Bundle().apply {
                    putParcelable(ARG_ARTIST, artist)
                }
                findNavController(it).navigate(R.id.action_homeFragment_to_fullArtistFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int = artists.size
}