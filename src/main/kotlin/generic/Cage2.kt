package generic

fun main() {

     val goldFishCage = CageV3<GoldFish>()
     goldFishCage.put(GoldFish("금붕어"))
     val cage = CageV3<Fish>()

    // Error. Type Mismatch
    // CageV3<Fish>에 CageV3<GoldFish>를 넣는다.
    // CageV3<Fish>와 CageV3<GoldFish>는 아무 관계도 아니다. -> CageV3는 무공변(in-variant, 불공변)하다.
    // cage.moveFrom(goldFishCage)

    val goldFishCageV4 = CageV4<GoldFish>()
    goldFishCageV4.put(GoldFish("금붕어"))

    val cageV4 = CageV4<Fish>()

    // CageV4<Fish>가 CageV4<GoldFish>의 상위 타입이면 가능하다.
    // out을 통해 공변하게 만들어주어 상위 타입관계를 유지한다.
    cageV4.moveFrom(goldFishCageV4)

}

class CageV3<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        this.animals.add(animal)
    }

    fun moveFrom(cageV3: CageV3<T>){
        this.animals.addAll(cageV3.animals)
    }
}

class CageV4<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T{
        return animals.first()
    }

    fun put(animal: T){
        this.animals.add(animal)
    }

    // 사용 지점 변성 (use-site variance)
    // <? extends T>
    fun moveFrom(cageV4: CageV4<out T>){
        // out을 붙이게 되면, 데이터를 꺼낼 수만(생산) 있다.
        // cageV4.getFirst()        // OK
        // cageV4.put(Carp("잉어"))  // Error -> 타입 안정성이 깨진다.
        this.animals.addAll(cageV4.animals)
    }
}