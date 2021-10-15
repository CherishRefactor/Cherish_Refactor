package com.example.cherish_refactor

import android.app.Application
import com.example.cherish_refactor.ui.detail.DetailPlantViewModel
import com.example.cherish_refactor.ui.home.HomeViewModel
import com.example.cherish_refactor.ui.manage.ManageViewModel
import com.example.cherish_refactor.ui.manage.PlantViewModel
import com.example.cherish_refactor.ui.setting.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@MainApplication)
            androidFileProperties()
            // use modules
            modules(myViewModel)
        }
    }

    val myViewModel = module {
        viewModel { HomeViewModel()}
        viewModel { ManageViewModel() }
        viewModel { SettingViewModel() }
        viewModel { DetailPlantViewModel() }
        viewModel { PlantViewModel() }
    }

   /* val myModule = module {
        single { MyPageRepository(get()) }
        single { ShopRepository(get()) }
        single { ReviewRepository(get()) }
    }
*/

}