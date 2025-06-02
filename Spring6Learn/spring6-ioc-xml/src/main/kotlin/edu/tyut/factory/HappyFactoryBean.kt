package edu.tyut.factory

import edu.tyut.bean.Happy
import org.springframework.beans.factory.FactoryBean

internal class HappyFactoryBean : FactoryBean<Happy> {
    private val name: String = this::class.java.simpleName
    override fun getObject(): Happy? {
        return Happy(name = "<UNK>1")
    }

    override fun getObjectType(): Class<*>? {
        return Happy::class.java
    }

    override fun toString(): String {
        return "HappyFactoryBean(name='$name')"
    }

}