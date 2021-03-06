package com.android.sample.game.di

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.android.sample.game.MainActivity
import com.android.sample.game.R
import com.android.sample.game.fragment.InGame
import com.android.sample.game.fragment.InGameArgs
import com.android.sample.game.repository.InGameRepository
import com.android.sample.game.repository.InGameRepositoryImpl
import com.android.sample.game.viewmodel.InGameViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class InGameModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun inGameFragment(): InGame

    @Binds
    @IntoMap
    @ViewModelKey(InGameViewModel::class)
    abstract fun bindViewModel(viewModel: InGameViewModel): ViewModel

    @Binds
    internal abstract fun bindRepository(repository: InGameRepositoryImpl): InGameRepository

    @Module
    companion object {

        @Provides
        @JvmStatic
        internal fun provideQuery(activity: MainActivity): String {
            val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val fragment = navHostFragment!!.childFragmentManager.fragments?.get(0)
            val args: InGameArgs by fragment.navArgs()
            return args.query
        }
    }
}