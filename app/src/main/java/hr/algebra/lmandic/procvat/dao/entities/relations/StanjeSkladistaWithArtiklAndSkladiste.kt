package hr.algebra.lmandic.procvat.dao.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hr.algebra.lmandic.procvat.dao.entities.Artikl
import hr.algebra.lmandic.procvat.dao.entities.Skladiste
import hr.algebra.lmandic.procvat.dao.entities.StanjeSkladista

data class StanjeSkladistaWithArtiklAndSkladiste(
    @Embedded val stanjeSkladista: StanjeSkladista,

    @Relation(
        entity = Artikl::class,
        entityColumn =  "_id",
        parentColumn = "artiklId"
    )
    val artikl: Artikl,

    @Relation(
        entity = Skladiste::class,
        entityColumn =  "_id",
        parentColumn = "skladisteId"
    )
    val skladiste: Skladiste
)