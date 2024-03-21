package generic

fun main() {
    // 코틀린은 언어 초기부터 제네릭이 고려되었기 때문에 raw type 객체를 만들 수 없다.
    // 코틀린도 JVM 위에서 동작하기 때문에 런타임 때는 타입 정보가 사라진다. -> 이를 타입 소거(Type erasure)라 부른다.
    // val numbers: List = listOf(1, 2, 3)

    val numbers = listOf(1, 2f, 3.0)
    numbers.filterIsInstance<Float>() // [2f]
}

fun checkStringList(data: Any){
    // Error.
    // Cannot check for instance of erased type : List<String>
    // if(data is List<String>){}

    // star projection을 활용해 최소한 List인지는 확인할 수 있다.
    if(data is List<*>){}
}

fun checkList(data: Any){
    if(data is List<*>){
        // data가 어떤 타입인지는 모르기 때문에 Any?로 가져온다.
        val element: Any? = data[0]
    }
}

fun checkMutableList(data: Any){
    if(data is MutableList<*>){
        // MutableList 안에 어떤 타입이 들어있을지 모르니 데이터를 넣을 수는 없다.
        // data.add(3)
    }
}

// 단점. reified 키워드가 붙은 타입 T를 이용해 T의 인스턴스를 만들거나 T의 companion object를 가져올 수는 없다.
inline fun <reified T> T.toSuperString(){
    println("${T::class.java.name} $this")
}

// 단점. reified 키워드가 붙은 타입 T를 이용해 T의 인스턴스를 만들거나 T의 companion object를 가져올 수는 없다.
inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean {
    return this.any { it is T }
}

class TypeErase {}