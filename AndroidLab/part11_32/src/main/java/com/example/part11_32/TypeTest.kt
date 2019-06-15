package com.example.part11_32
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
class TypeTest {
    fun testType(): String {
        val intData: Int=10
        val result=intData.minus(5)

        val doubleData: Double = result.toDouble()
        return "testType() : $doubleData"
    }
    fun testArray(): String {
        val array= arrayOf(1, "hello", false)
        return "testArray() : ${array.size}..${array.get(2)}"
    }
    fun testAny(obj : Any): String {
        when(obj){
            1->return "Int 1"
            "Hello"->return "String Hello"
            else ->return "unknown"
        }
    }
}