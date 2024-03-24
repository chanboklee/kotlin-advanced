package delegate

fun main() {
    // 인스턴스화 시점과 프로퍼티 초기화 시점을 분리하고 싶다면?
    val person = Person("이찬")
}

class Person(
    val name: String,
) {
    val isKim: Boolean
        get() = this.name.startsWith("김")

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

/**
 * Person 객체를 인스턴스화 한 후, name을 초기화 하지 않더라도 예외가 발생하지 않아 알 수 없다.
 */
class PersonV1{
    var name: String = "홍길동"

    val isKim: Boolean
        get() = this.name.startsWith("김")

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

/**
 * 실제 null이 될 수는 없기에, 계속 널 처(?. / ?: / !!)가 들어가게 된다.
 */
class PersonV2{
    var name: String? = null

    val isKim: Boolean
        get() = this.name!!.startsWith("김")

    val maskingName: String
        get() = name!![0] + (1 until name!!.length).joinToString("") { "*" }
}

/**
 * 초기값을 지정하지 않고, null이 들어갈 수 없는 변수를 선언
 * 초기값이 지정되지 않았는데 변수를 사용하려 하면 예외가 발생한다.
 *
 * lateinit 변수는 컴파일 단계에서 nullable 변수로 바뀌고, 변수에 접근하려 할 때 null이 예외가 발생한다.
 * lateinit은 primitive type에 사용할 수 없다.
 */
class PersonV3{
    lateinit var name: String

    val isKim: Boolean
        get() = this.name.startsWith("김")

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}