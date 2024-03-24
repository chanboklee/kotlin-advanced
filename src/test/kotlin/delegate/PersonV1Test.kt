package delegate

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersonV1Test {

    private val person = PersonV1()

    @Test
    fun isKimTest(){
        Assertions.assertTrue(person.apply { name = "김수한무" }.isKim)
    }

    @Test
    fun maskingNameTest(){
        Assertions.assertEquals(person.apply { name = "이찬복" }.maskingName, "이**")
    }
}