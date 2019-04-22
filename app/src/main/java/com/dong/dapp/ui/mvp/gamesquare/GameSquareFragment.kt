package com.dong.dapp.ui.mvp.gamesquare

import android.os.Bundle
import com.dong.dapp.R
import com.dong.dapp.adapter.recyclerview.DAppsListAdapter
import com.dong.dapp.bean.wallet.ProjectListBean
import com.dong.dapp.network.BaseObserver
import com.dong.dapp.network.DAppRequest
import kotlinx.android.synthetic.main.fragment_game_square.view.*
import me.serenadehl.base.base.mvpbase.MVPBaseFragment

/**
 * 游戏广场页Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-11 10:25:57
 */
class GameSquareFragment : MVPBaseFragment<IGameSquarePresenter>(), IGameSquareView {
    private val mAdapter by lazy {
        DAppsListAdapter(requireActivity()).apply {
            val headerView = layoutInflater.inflate(R.layout.app_recycle_header_game_square, null, false)
            addHeaderView(headerView)
        }
    }

    override fun layout() = R.layout.fragment_game_square

    override fun createPresenter() = GameSquarePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mRootView.rv_games.apply {
            adapter = mAdapter
        }

//        DAppRequest.getDApps(0, 3)
//            .subscribe(object :BaseObserver<ProjectListBean?>(){
//                override fun next(data: ProjectListBean?) {
//                    mAdapter.setNewData(data?.items)
//                }
//            })
    }

}
