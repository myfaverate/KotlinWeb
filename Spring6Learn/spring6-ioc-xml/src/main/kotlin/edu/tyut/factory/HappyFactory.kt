package edu.tyut.factory

import edu.tyut.bean.Happy

internal class HappyFactory private constructor() {
    internal companion object {
        private val INSTANCE = Happy(name = "单例1")
        @JvmStatic
        internal fun happyFactory(): Happy = INSTANCE
    }
}