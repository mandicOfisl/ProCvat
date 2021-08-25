package hr.algebra.lmandic.procvat.dao.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hr.algebra.lmandic.procvat.dao.entities.*

data class NarudzbaWithDokumentAndArtiklAndStatusAndKorisnik (
    @Embedded val narudzba: Narudzba,

    @Relation(
        entity = Dokument::class,
        entityColumn =  "_id",
        parentColumn = "dokumentId"
    )
    val dokument: Dokument,

    @Relation(
        entity = Artikl::class,
        entityColumn =  "_id",
        parentColumn = "artiklId"
    )
    val artikl: Artikl,

    @Relation(
        entity = Status::class,
        entityColumn =  "_id",
        parentColumn = "statusId"
    )
    val status: Status,


    @Relation(
        entity = Korisnik::class,
        entityColumn =  "_id",
        parentColumn = "korisnikId"
    )
    val korisnik: Korisnik
)