package hr.algebra.lmandic.procvat.dao.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hr.algebra.lmandic.procvat.dao.entities.Artikl
import hr.algebra.lmandic.procvat.dao.entities.Boja
import hr.algebra.lmandic.procvat.dao.entities.Grupa
import hr.algebra.lmandic.procvat.dao.entities.Status

data class ArtiklWithGrupaAndBojaAndStatus(
    @Embedded val artikl: Artikl,
    @Relation(
       entity = Grupa::class,
       entityColumn =  "_id",
       parentColumn = "grupaId"
    )
    val grupa: Grupa,
    @Relation(
       entity = Boja::class,
       entityColumn =  "_id",
       parentColumn = "bojaId"
    )
    val boja: Boja,
    @Relation(
        entity = Status::class,
        entityColumn = "_id",
        parentColumn = "statusId"
    )
    val status: Status
)