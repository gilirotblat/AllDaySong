package gilir.gilifinalproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentSongBinding


class SongFragment : Fragment() {

    private var _binding: FragmentSongBinding? = null
    private lateinit var homeViewModel: MusicViewModel


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSongBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this)[MusicViewModel::class.java]
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSongs.layoutManager = LinearLayoutManager(requireContext())

        homeViewModel.songs.observe(viewLifecycleOwner) {
            binding.rvSongs.adapter = SongAdapter(it) { clickedSong -> // favorite click listener
                clickedSong.isClicked = !clickedSong.isClicked
                homeViewModel.updateSong(clickedSong)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}