package com.example.part11_33

import android.util.Log
/**
 * Created by kkang
 * 깡샘의 안드로이드 프로그래밍 - 루비페이퍼
 * 위의 교재에 담겨져 있는 코드로 설명 및 활용 방법은 교제를 확인해 주세요.
 */
fun printLog(str: String){
    Log.d("kkang", str)
}

open class User(name: String){
    constructor(name: String, email: String): this(name)
    open var x: Int=0
    open fun someFun(){
        printLog("Super...someFun()")
    }
}

interface MyInterface {
    fun bar()
    fun foot() {}
}

open class Customer: User("kkang"), MyInterface{
    override var x: Int = 20
    override fun someFun() {
        super.someFun()
        printLog("Sub...${super.x}...$x")
    }

    override fun bar() {

    }
}