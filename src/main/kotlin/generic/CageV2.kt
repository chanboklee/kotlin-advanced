package generic

fun main() {

    // Solution 3. Generic
    val cageV2 = CageV2<Carp>()
    cageV2.put(Carp("잉어"))
    val carp: Carp = cageV2.getFirst()
}

class CageV2<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        this.animals.add(animal)
    }

    fun moveFrom(cageV2: CageV2<T>){
        this.animals.addAll(cageV2.animals)
    }
}