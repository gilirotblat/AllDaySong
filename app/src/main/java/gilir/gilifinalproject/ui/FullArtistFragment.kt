package gilir.gilifinalproject.ui


import android.os.Build
import android.os.Bundle
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
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentFullPageBinding
import gilir.gilifinalproject.models.artistapi.Artist
import kotlinx.android.synthetic.main.fragment_full_page.*


const val ARG_ARTIST = "artist"

class FullArtistFragment : Fragment() {
    private var _binding: FragmentFullPageBinding? = null
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
        _binding = FragmentFullPageBinding.inflate(inflater, container, false)

        val root: View = binding.root

        fullArtViewModel = ViewModelProvider(this)[FullPageViewModel::class.java]
        return root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTracklist.layoutManager = LinearLayoutManager(requireContext())

        with(binding) {
            artists?.let { artist ->
                tvFullTitle.text = artist.name
                Picasso.get().load(artist.picture).into(ivFullCover)
                iv_back_arrow.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_fullArtistFragment_to_homeFragment)

                }

                fullArtViewModel.artistSongs.observe(viewLifecycleOwner) { response ->
                    binding.rvTracklist.adapter = SongAdapter(response, fullArtViewModel)
                }
               fullArtViewModel.getArtistSongs(artist.artistId)

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}