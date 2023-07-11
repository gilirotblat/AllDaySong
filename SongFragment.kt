package gilir.gilifinalproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentSongBinding
import gilir.gilifinalproject.models.songsapi.Song


class SongFragment : Fragment() {

    private var _binding: FragmentSongBinding? = null
    private lateinit var homeViewModel: HomeViewModel


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSongBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSongs.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.songs.observe(viewLifecycleOwner) { songs ->
            binding.rvSongs.adapter = SongAdapter(
                songs = songs,
                onFavoriteClicked = { song ->
                if (songs.favoritesMapping[song.id] == true) {
                    homeViewModel.deleteSong(song)
                } else {
                    homeViewModel.addSongToFavorites(song)
                }
            })
        }

        homeViewModel.favoriteSongs.observe(viewLifecycleOwner) {
            val adapter:SongAdapter? = binding.rvSongs.adapter as? SongAdapter
            adapter?.updateFavorites(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}