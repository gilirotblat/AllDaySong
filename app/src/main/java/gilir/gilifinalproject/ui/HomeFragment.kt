package gilir.gilifinalproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import gilir.gilifinalproject.R
import gilir.gilifinalproject.adapters.ViewPagerAdapter
import gilir.gilifinalproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel:HomeViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh(null)

        val title = arrayOf("hits", "artists", "playlist", "favorite")
        val icon = arrayOf(
            R.drawable.ic_star,
            R.drawable.ic_artist,
            R.drawable.ic_baseline_music_note_24,
            R.drawable.ic_baseline_favorite_24
        )

        val tabLayout = binding.tabsLayout
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerAdapter(this, title)
        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = title[position]
            tab.icon = AppCompatResources.getDrawable(requireContext(), icon[position])
        }.attach()

        with(binding.swiperefresh) {
            setOnRefreshListener {
                viewModel.refresh(this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
