package edu.tyut.spring_boot_ssm.service

import edu.tyut.spring_boot_ssm.bean.Article
import edu.tyut.spring_boot_ssm.dto.ArticleDto
import kotlinx.coroutines.Deferred

internal interface ArticleService {
    suspend fun add(articleDto: ArticleDto): Deferred<Boolean>
}