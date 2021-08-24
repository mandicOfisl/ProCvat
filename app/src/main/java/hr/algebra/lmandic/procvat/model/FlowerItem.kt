package hr.algebra.lmandic.procvat.model

data class FlowerItem (
    var flowerId: Int,
    var name: String,
    var colorHex: String,
    var picturePath: String?,
    var kolicinaNaSkladistu: Int,
    var kolicinaZaNarudzbe: Int
)