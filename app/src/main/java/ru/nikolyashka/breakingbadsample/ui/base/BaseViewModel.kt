package ru.nikolyashka.breakingbadsample.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    open fun onResume() { }
}