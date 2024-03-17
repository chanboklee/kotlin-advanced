package generic

fun main() {

    // 생산만 하는 클래스
    // class에 out을 붙였기에 생산만 가능하고, 소비를 위한 T를 받을 수는 없다.
    val fishCageV6 = CageV6<Fish>()
    val animalCageV6: CageV6<Animal> = fishCageV6

    // 소비만 하는 클래스
    // class에 in을 붙였기에 소비만 가능하고, 생산을 위한 T를 받을 수는 없다.
    val animalCageV7 = CageV7<Animal>()
    val fishCageV7: CageV7<Fish> = animalCageV7

}

// 선언 지점 변성 (declaration-site variance)
// java는 사용할 수 없다.
class CageV6<out T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun getAll(): List<T>{
        return this.animals
    }
}

// 선언 지점 변성 (declaration-site variance)
// java는 사용할 수 없다.
class CageV7<in T> {
    private val animals: MutableList<T> = mutableListOf()

    fun put(animal: T){
        this.animals.add(animal)
    }

    fun putAll(animals: List<T>){
        this.animals.addAll(animals)
    }
}