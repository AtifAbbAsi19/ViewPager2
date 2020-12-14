# ViewPager2
Banner and Indicator using ViewPager2



             <com.google.android.material.tabs.TabLayout
                    android:id="@+id/tabLayout"
                    android:layout_width="wrap_content"
                    android:layout_height="@dimen/_32sdp"
                    android:layout_marginTop="@dimen/_4sdp"
                    android:background="@android:color/transparent"
                    app:layout_constraintBottom_toTopOf="@+id/pieChart"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent"
                    app:tabBackground="@android:color/transparent"
                    app:tabGravity="center"
                    app:tabIndicatorHeight="0dp"
                    app:tabMode="fixed"
                    app:tabPaddingEnd="16dp"
                    app:tabPaddingStart="16dp"
                    app:tabRippleColor="@null"
                    app:tabTextAppearance="@android:style/TextAppearance.Widget.TabWidget"
                    app:tabTextColor="@drawable/tab_selector">

                    <com.google.android.material.tabs.TabItem
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="@string/first_tab" />

                    <com.google.android.material.tabs.TabItem
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="@string/second_tab" />

                </com.google.android.material.tabs.TabLayout>
                
                
                ViewCompat.setBackgroundTintList(
                itemViewHolder.logo,
                ColorStateList.valueOf(Color.parseColor(YELLOW_COLOR)))
                    
                DrawableCompat.setTint(arrowSign.getDrawable(),
                ContextCompat.getColor(arrowSign.getContext(), R.color.orange));

                DrawableCompat.setTint(inviteButton.getBackground(),
                ContextCompat.getColor(inviteButton.getContext(), R.color.darkBlack));



    private fun updateUiFlow(inflated: View) {

        val tabLayout = inflated.findViewById<TabLayout>(R.id.tabLayout)
        val pager = inflated.findViewById<ViewPager2>(R.id.pager)
        pager.adapter =  PagerAdapter(this)

        TabLayoutMediator(tabLayout, pager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->

            when (position) {
                0 -> {
                    tab.text = resources.getString(R.string.first_tab)
                }
                1 -> {
                    tab.text = resources.getString(R.string.second_tab)
                }
            }
            //for first time initialization tab will call for each view
        }).attach()
        addTabChangeListener(tabLayout)
    }

    private fun addTabChangeListener(tabLayout: TabLayout) {

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        tab.text = resources.getString(R.string.first_tab)
                    }
                    1 -> {
                        tab.text = resources.getString(R.string.second_tab)
                    }
                }
            }
        })
    }

