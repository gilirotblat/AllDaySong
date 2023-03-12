package gilir.gilifinalproject.ui


import android.os.Build
import android.os.Bundle
import android.print.PrinterInfo
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import gilir.gilifinalproject.R
import gilir.gilifinalproject.adapters.ArtistSongAdapter
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentFullArtistBinding
import gilir.gilifinalproject.models.artistapi.Artist
import kotlinx.android.synthetic.main.fragment_full_artist.*


const val ARG_ARTIST = "artist"

class FullArtistFragment : Fragment() {
    private var _binding: FragmentFullArtistBinding? = null
    private val binding get() = _binding!!

    private lateinit var fullArtViewModel: FullPageViewModel

    private var artists: Artist? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artists = it.getParcelable(ARG_ARTIST)!!

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullArtistBinding.inflate(inflater, container, false)

        val root: View = binding.root

        fullArtViewModel = ViewModelProvider(this)[FullPageViewModel::class.java]
        return root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArtistSong.layoutManager = LinearLayoutManager(requireContext())

        with(binding) {
            artists?.let { artist ->
                tvFullArtistName.text = artist.name
                Picasso.get().load(artist.picture).into(ivFullArtistCover)
                iv_back_arrow.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_fullArtistFragment_to_homeFragment)

                }

                fullArtViewModel.artistSongs.observe(viewLifecycleOwner) { response ->
                    binding.rvArtistSong.adapter = ArtistSongAdapter(response) { clickedSong->
                        clickedSong.isClicked = !clickedSong.isClicked
                        fullArtViewModel.updateASong(clickedSong)
                    }
                }
               fullArtViewModel.getArtistSongs(artist.artistId.toString())

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}