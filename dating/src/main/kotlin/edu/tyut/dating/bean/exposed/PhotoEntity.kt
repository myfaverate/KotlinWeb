package edu.tyut.dating.bean.exposed

import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable

internal object PhotoEntity : LongIdTable(name = "photo_info") {
    internal val photoUrl: Column<String> = varchar(name = "photo_url", length = 500)
    internal val photoName: Column<String> = varchar(name = "photo_name",  length = 255)
    internal val description: Column<String> = text(name = "description")
}