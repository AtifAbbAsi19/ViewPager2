# ViewPager2
Banner and Indicator using ViewPager2




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

