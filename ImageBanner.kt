
import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ImageBanner : ConstraintLayout {

    val adapter = ViewPagerAdapter(arrayListOf())

    var indicator: TabLayout? = null
    var pager: ViewPager2? = null

    var currentPage = 0

    var stopAutoScroll = false

    var autoScroll: CountDownTimer? = null

    var bannerOnClickListener: BannerOnClickListener? = null

    var setMargin = false

    //Primary Constructor
    constructor(context: Context) : super(context)

    //Secondary Constructor
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        try {
            initView(context, attrs) //init view
        } catch (ignored: Exception) {
            ignored.message
        }

    }

    private fun initView(context: Context, attrs: AttributeSet?) {

        //inflating view
        inflate(context, R.layout.image_banner_widget_layout, this)
        //setting backgorund to transparent
        setBackgroundColor(Color.TRANSPARENT)

        initAttrs(attrs)



        indicator = findViewById<TabLayout>(R.id.indicator) as TabLayout
        pager = findViewById<ViewPager2>(R.id.bannerViewPager) as ViewPager2




        pager?.adapter = adapter


        TabLayoutMediator(indicator!!, pager!!, TabLayoutMediator.TabConfigurationStrategy { tab, position ->

            when (position) {
                0 -> {
                    tab.select()
                    startAutoScroll()
                }
                adapter.itemCount - 1 -> {
                    currentPage = 0
                    pager?.setCurrentItem(currentPage, true)
                    if (setMargin) { //added check here for spacing in banner indicators
                        setTabMargin()
                    }
                }

            }


        }).attach()




        indicator?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentPage = tab!!.position
                startAutoScroll()

            }
        })


//        TabLayoutMediator(indicator!!, pager!!) { tab, position ->
//          //got stuck when attached with TabLayout Mediator
//
//        }.attach()


    }


    fun setBannerClickListener(bannerOnClickListener: BannerOnClickListener) {
        this.bannerOnClickListener = bannerOnClickListener
        adapter.setBannerClickListener(bannerOnClickListener)
    }

    fun updateBannerList(bannerList: MutableList<UBPBanner>) {
        setMargin = true
        adapter.updateBannerList(bannerList)
    }

    private fun initAttrs(@NonNull attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageBanner)

        if (null != typedArray) {
            typedArray.recycle()
        }

    }


    private fun startAutoScroll() {

        if (null != autoScroll) {
            stopAutoScroll()
        }


        autoScroll = object : CountDownTimer(10000, 600) {

            override fun onTick(millisUntilFinished: Long) {

                Log.d("Timer", "milli: $millisUntilFinished")

            }

            override fun onFinish() {

                Log.d("Timer", "onFinish Called")

                if (currentPage >= adapter.itemCount - 1) {
                    currentPage = 0
                } else {
                    currentPage++
                }
                pager?.setCurrentItem(currentPage, true)

            }

        }.start()


    }


    private fun stopAutoScroll() {

        autoScroll?.cancel()
        autoScroll = null

    }


    fun stopAutoScrollerFlag(isVisible: Boolean) {
        if (isVisible) {
            startAutoScroll()
        } else {
            stopAutoScroll()
        }
    }

    fun setTabMargin() {
        for (i in 0 until indicator!!.tabCount) {
            val tab = (indicator!!.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(14, 0, 14, 0)
            tab.requestLayout()
        }
        setMargin = false
    }


    interface BannerOnClickListener {
        fun onClick(banner: Banner)
    }

}