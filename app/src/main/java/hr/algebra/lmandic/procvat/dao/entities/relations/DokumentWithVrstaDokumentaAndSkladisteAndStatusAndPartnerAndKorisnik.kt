package hr.algebra.lmandic.procvat.dao.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import hr.algebra.lmandic.procvat.dao.entities.*

data class DokumentWithVrstaDokumentaAndSkladisteAndStatusAndPartnerAndKorisnik (
    @Embedded val dokument: Dokument,
    @Relation(
        entity = VrstaDokumenta::class,
        parentColumn = "grupaId",
        entityColumn =  "_id"
    )
    val vrstaDokumenta: VrstaDokumenta,
    @Relation(
        entity = Skladiste::class,
        entityColumn =  "_id",
        parentColumn = "skladisteId"
    )
    val skladiste: Skladiste,
    @Relation(
        entity = Status::class,
        entityColumn =  "_id",
        parentColumn = "statusId"
    )
    val status: Status,
    @Relation(
        entity = Partner::class,
        entityColumn =  "_id",
        parentColumn = "partnerId"
    )
    val partner: Partner,
    @Relation(
        entity = Korisnik::class,
        entityColumn =  "_id",
        parentColumn = "korisnikId"
    )
    val korisnik: Korisnik
)