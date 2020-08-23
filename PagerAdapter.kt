

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

import java.lang.ref.WeakReference


class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {


    private val FRAGMENTS_SIZE = 2

    var currentFragmentWeakReference: WeakReference<Fragment>? = null


    override fun getItemCount(): Int {
        return this.FRAGMENTS_SIZE
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> {
                currentFragmentWeakReference = WeakReference(TestFragment())
                return TestFragment()
            }
            1 -> {
                currentFragmentWeakReference = WeakReference(TestFragment())
                return TestFragment()
            }
        }

        return TestFragment()

    }

}