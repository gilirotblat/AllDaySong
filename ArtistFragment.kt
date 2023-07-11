package gilir.gilifinalproject.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gilir.gilifinalproject.adapters.ArtistAdapter
import gilir.gilifinalproject.databinding.FragmentArtistBinding



class ArtistFragment : Fragment() {
    private var _binding: FragmentArtistBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArtistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel.artist.observe(viewLifecycleOwner) {
            binding.rvArtist.adapter = ArtistAdapter(it)
            binding.rvArtist.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}