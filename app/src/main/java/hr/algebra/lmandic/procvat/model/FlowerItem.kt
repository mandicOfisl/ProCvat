package hr.algebra.lmandic.procvat.model

import hr.algebra.lmandic.procvat.dao.entities.Artikl

data class FlowerItem (
    var artikl: Artikl,
    var grupaNaziv: String,
    var bojaNaziv: String,
    var bojaHex: String,
    var kolicinaNaSkladistu: Int,
    var kolicinaZaNarudzbe: Int
)