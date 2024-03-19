package generic

fun main() {
    // CageV8<Int>      // Animal 타입이 아니다.
    // CageV8<String>   // Animal 타입이 아니다.
    CageV8<Carp>()

    val cage = CageV9(mutableListOf(Eagle(), Sparrow()))
    cage.printAfterSorting()
}

// T : Animal 을 사용하면, 타입 파라미터의 상한(upper bound)을 Animal로 정할 수 있다.
class CageV8<T : Animal> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        this.animals.add(animal)
    }

    fun moveFrom(cageV8: CageV8<T>){
        this.animals.addAll(cageV8.animals)
    }

    fun moveTo(cageV8: CageV8<T>){
        cageV8.animals.addAll(this.animals)
    }
}

// 제한 조건을 여러개 두고 싶다면, where 키워드를 사용해 타입 파라미터에 여러 조건을 설정할 수 있다.
// 순서대로 정렬해 출력하는 함수를 만들 수 있다. ( Comparable<T>를 T가 구현하고 있기 때문 )
class CageV9<T> (
    private val animals: MutableList<T> = mutableListOf()
) where T : Animal, T : Comparable<T> {

    fun printAfterSorting(){
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        this.animals.add(animal)
    }

    fun moveFrom(cageV9: CageV9<T>){
        this.animals.addAll(cageV9.animals)
    }

    fun moveTo(cageV9: CageV9<T>){
        cageV9.animals.addAll(this.animals)
    }
}

abstract class Bird(
    name: String,
    private val size: Int,
) : Animal(name), Comparable<Bird>{
    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }
}

class Sparrow : Bird("참새", 100)
class Eagle : Bird("독수리", 500)

/*fun List<String>.hasIntersection(other: List<String>): Boolean{
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}

fun List<Int>.hasIntersection(other: List<Int>): Boolean{
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}*/

// 제네릭 확장 함수
fun <T> List<T>.hasIntersection(other: List<T>): Boolean{
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}