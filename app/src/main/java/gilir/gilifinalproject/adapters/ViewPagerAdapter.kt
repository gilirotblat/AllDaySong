package gilir.gilifinalproject.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import gilir.gilifinalproject.ui.ArtistFragment
import gilir.gilifinalproject.ui.FavoriteSongFragment
import gilir.gilifinalproject.ui.PlaylistFragment
import gilir.gilifinalproject.ui.SongFragment

class ViewPagerAdapter(fragment: Fragment, private val title: Array<String>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = title.size

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return SongFragment()
            1 -> return ArtistFragment()
            2 -> return PlaylistFragment()
            3 -> return FavoriteSongFragment()
        }
        return SongFragment()
    }

}