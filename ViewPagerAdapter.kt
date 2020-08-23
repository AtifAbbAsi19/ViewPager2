

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable

import java.util.concurrent.TimeUnit

class ViewPagerAdapter(bannerList: ArrayList<UBPBanner>) : RecyclerView.Adapter<ImageBannerViewHolder>() {

    var bannerList: MutableList<Banner> = arrayListOf()

    var dispose: CompositeDisposable = CompositeDisposable()

    var bannerOnClickListener: UBPImageBanner.BannerOnClickListener? = null


    init {
        this.bannerList = bannerList
    }


    fun updateBannerList(bannerList: MutableList<Banner>) {
        this.bannerList = bannerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageBannerViewHolder =
            ImageBannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_image_view_widget, parent, false))

    //get the size of array
    override fun getItemCount(): Int = bannerList?.size ?: 0
    ?: 0 //elvis operator short if else return default size 0
//    override fun getItemCount(): Int = Int.MAX_VALUE

    //binding the screen with view
    override fun onBindViewHolder(holder: ImageBannerViewHolder, position: Int) = holder.itemView.run {


        if (bannerList.isNotEmpty()) {
            holder.bind(bannerList[position])

            dispose.add(
                    RxView.clicks(holder.itemView).throttleFirst(1, TimeUnit.SECONDS).subscribe { v: Any? ->
                        if (null != bannerOnClickListener) {
                            bannerOnClickListener!!.onClick(bannerList[position])
                        }
                    }
            )

        }

    }

    fun setBannerClickListener(bannerClickListener: ImageBanner.BannerOnClickListener) {
        bannerOnClickListener = bannerClickListener
    }
}


class ImageBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val bannerImage = itemView.findViewById<ImageView>(R.id.bannerImage)

    fun bind(banner: Banner) {
        bannerImage.loadImage(banner.imageUrl)
    }

}
