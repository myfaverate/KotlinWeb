package edu.tyut.factory

import edu.tyut.bean.Happy

internal class HappyFactoryImpl private constructor() {
    internal companion object {
        private val INSTANCE = Happy(name = "单例")
    }
    internal fun happyFactory(): Happy = INSTANCE
}