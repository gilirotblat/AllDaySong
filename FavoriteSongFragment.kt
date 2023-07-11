package gilir.gilifinalproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gilir.gilifinalproject.adapters.SongAdapter
import gilir.gilifinalproject.databinding.FragmentFavoriteSongBinding
import gilir.gilifinalproject.repository.Songs

class FavoriteSongFragment : Fragment() {
    private var _binding: FragmentFavoriteSongBinding? = null
    private lateinit var homeViewModel: HomeViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteSongBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavoriteSong.layoutManager = LinearLayoutManager(requireContext())
        homeViewModel.favoriteSongs.observe(viewLifecycleOwner) {
            binding.rvFavoriteSong.adapter = SongAdapter(
                songs = Songs(it.toMutableList(),HashMap()),
                onFavoriteClicked = { song ->
                    homeViewModel.deleteSong(song)
            }, isFavoritesAdapter = true)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
