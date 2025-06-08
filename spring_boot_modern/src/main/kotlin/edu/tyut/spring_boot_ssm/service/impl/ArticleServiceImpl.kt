package edu.tyut.spring_boot_ssm.service.impl

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.dao.ArticleDao
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import edu.tyut.spring_boot_ssm.service.ArticleService
import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransactionAsync
import org.springframework.stereotype.Service

@Service
internal final class ArticleServiceImpl internal constructor (
    private val articleDao: ArticleDao
) : ArticleService {

    override suspend fun add(articleDto: ArticleDto): Deferred<Boolean> = suspendTransactionAsync {
        articleDao.add(articleDto = articleDto)
    }

}