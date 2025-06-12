package edu.tyut.spring_boot_ssm.dao

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import kotlinx.coroutines.Deferred

internal interface ArticleDao {
    suspend fun add(articleDto: ArticleDto): Boolean
    suspend fun list(
        pageIndex: Int,
        pageSize: Int,
        categoryId: Int?,
        state: String?,
    ) : List<Article>
}