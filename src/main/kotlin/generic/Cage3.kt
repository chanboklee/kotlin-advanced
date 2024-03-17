package generic

fun main() {
    val fishCage = CageV5<Fish>()
    val goldFishCage = CageV5<GoldFish>()
    goldFishCage.put(GoldFish("금붕어"))

    // Error. Type Mismatch
    // CageV3<GoldFish>에 CageV3<Fish>를 넣는다.
    // CageV3<Fish>가 CageV3<GoldFish>의 하위 타입이어야 한다.
    // in을 통해 반공변하게 만들어야 한다.
    // goldFishCage.moveTo(fishCage)
}

class CageV5<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        this.animals.add(animal)
    }

    fun moveFrom(cageV5: CageV5<out T>){
        this.animals.addAll(cageV5.animals)
    }

    fun moveTo(cageV5: CageV5<in T>){
        // in을 붙이게 되면, 데이터를 받을 수만(소비) 있다.
        cageV5.animals.addAll(this.animals)
    }
}