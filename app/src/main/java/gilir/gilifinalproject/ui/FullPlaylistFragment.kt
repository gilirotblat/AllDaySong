package gilir.gilifinalproject.ui


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import gilir.gilifinalproject.R
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentFullPageBinding
import gilir.gilifinalproject.models.playlistApi.Playlist


const val ARG_PLAYLIST = "playlist"


class FullSongFragment : Fragment() {
    private var _binding: FragmentFullPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var fullPlaylstViewModel: FullPageViewModel

    private var playlist: Playlist? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playlist = it.getParcelable(ARG_PLAYLIST)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullPageBinding.inflate(inflater, container, false)

        val root: View = binding.root

        fullPlaylstViewModel = ViewModelProvider(this)[FullPageViewModel::class.java]
        return root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTracklist.layoutManager = LinearLayoutManager(requireContext())

        with(binding) {
            playlist?.let { playlist ->
                tvFullTitle.text = playlist.title
                Picasso.get().load(playlist.picture).into(ivFullCover)
                ivBackArrow.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_fullPlaylistFragment_to_homeFragment)
                }

                fullPlaylstViewModel.playlistSongs.observe(viewLifecycleOwner) { response ->
                    binding.rvTracklist.adapter = SongAdapter(response, fullPlaylstViewModel)
                }
                fullPlaylstViewModel.getPlaylistSongs(playlist.id.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}