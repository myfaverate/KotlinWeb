package edu.tyut.spring_boot_ssm.dao

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.dto.ArticleDto

internal interface ArticleDao {
    suspend fun add(articleDto: ArticleDto): Boolean

}