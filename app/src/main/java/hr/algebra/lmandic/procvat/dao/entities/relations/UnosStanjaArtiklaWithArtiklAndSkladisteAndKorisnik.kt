package hr.algebra.lmandic.procvat.dao.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hr.algebra.lmandic.procvat.dao.entities.Artikl
import hr.algebra.lmandic.procvat.dao.entities.Korisnik
import hr.algebra.lmandic.procvat.dao.entities.Skladiste
import hr.algebra.lmandic.procvat.dao.entities.UnosStanjaArtikla

data class UnosStanjaArtiklaWithArtiklAndSkladisteAndKorisnik(
    @Embedded val unosStanjaArtikla: UnosStanjaArtikla,

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
    val skladiste: Skladiste,

    @Relation(
        entity = Korisnik::class,
        entityColumn =  "_id",
        parentColumn = "korisnikId"
    )
    val korisnik: Korisnik
)