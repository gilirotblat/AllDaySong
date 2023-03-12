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
import gilir.gilifinalproject.adapters.PlaylistSongAdapter
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentFullPlaylistBinding
import gilir.gilifinalproject.models.playlistApi.Playlist
import kotlinx.android.synthetic.main.fragment_full_artist.*


const val ARG_PLAYLIST = "playlist"


class FullSongFragment : Fragment() {
    private var _binding: FragmentFullPlaylistBinding? = null
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
        _binding = FragmentFullPlaylistBinding.inflate(inflater, container, false)

        val root: View = binding.root

        fullPlaylstViewModel = ViewModelProvider(this)[FullPageViewModel::class.java]
        return root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPlaylistTracklist.layoutManager =LinearLayoutManager(requireContext())

        with(binding) {
            playlist?.let { playlist ->
                tvFullPlaylistTitle.text = playlist.title
                Picasso.get().load(playlist.picture).into(ivFullCover)
               ivBackArrowPlaylist.setOnClickListener {
                   Navigation.findNavController(it).navigate(R.id.action_fullPlaylistFragment_to_homeFragment)
               }



                fullPlaylstViewModel.playlistSongs.observe(viewLifecycleOwner) {response->
                    binding.rvPlaylistTracklist.adapter = PlaylistSongAdapter(response) { clickedSong ->
                        clickedSong.isClicked = !clickedSong.isClicked
                        fullPlaylstViewModel.updateSong(clickedSong)
                    }
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