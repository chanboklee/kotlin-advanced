package generic

fun main() {
    val cageV1 = CageV1()
    cageV1.put(Carp("잉어"))

    // Error. Type Mismatch
    // getFirst() 함수는 Animal을 반환하기 때문
    // val carp: Carp = cageV1.getFirst()

    // Solution 1. Type Casting
    // cageV1.put(GoldFish("금붕어"))를 넣어도 컴파일 시에는 확인할 수 없다가 런타임이 되어야 에러를 찾을 수 있다.
    // val carp: Carp = cageV1.getFirst() as Carp

    // Solution 2. Safe Type Casting과 Elvis Operator
    // 실수로 GoldFish를 넣을 수 있고, 이로 인해 IllegalArgumentException 발생
    // val carp: Carp = cageV1.getFirst() as? Carp ?: throw IllegalArgumentException()

    // Solution 3. Generic
    val cageV2 = CageV2<Carp>()
    cageV2.put(Carp("잉어"))
    val carp: Carp = cageV2.getFirst()

}

class CageV1 {
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animals.first()
    }

    fun put(animal: Animal){
        this.animals.add(animal)
    }

    fun moveFrom(cageV1: CageV1){
        this.animals.addAll(cageV1.animals)
    }
}

// 제네릭 제약을 Non-Null 타입 한정에 사용할 수 있다.
// T : Any
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