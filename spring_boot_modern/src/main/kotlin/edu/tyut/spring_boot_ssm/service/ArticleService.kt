package edu.tyut.spring_boot_ssm.service

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.bean.Result
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import kotlinx.coroutines.Deferred
import org.springframework.web.bind.annotation.RequestParam

internal interface ArticleService {
    suspend fun add(articleDto: ArticleDto): Deferred<Boolean>
    suspend fun list(
        pageIndex: Int,
        pageSize: Int,
        categoryId: Int?,
        state: String?,
    ) : Deferred<List<Article>>
}