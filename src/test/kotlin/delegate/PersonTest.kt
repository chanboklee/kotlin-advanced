package delegate

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * 두 개의 테스트 메소드가 Person을 각각 인스턴스화 하고 있다.
 * 두 개의 테스트 메소드는 초기값이 다르다.
 */
class PersonTest {

    @Test
    fun isKimTest(){
        // given
        val person = Person("김수한무")

        // when // then
        assertTrue(person.isKim)
    }

    @Test
    fun maskingNameTest(){
        // given
        val person = Person("이찬복")

        // when // then
        assertEquals(person.maskingName, "이**")
    }
}

