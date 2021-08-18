import java.io.BufferedReader
import java.io.StringReader
import java.util.*

fun max(a: Int, b: Int): Int = if (a > b) a else b

fun eval(e: Expr): Int =
    when (e) {
        is Num ->
            e.value
        is Sum ->
            eval(e.right) + eval(e.left)
        else ->
            throw IllegalArgumentException("Unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun main(args: Array<String>) {
    // 2.1 Basic elements: functions and variables
    val name = if (args.size > 0) args[0] else "Kotlin"
    println("Hello, $name")
    println(max(1, 2))

    // 2.2 Classes and properties
    val person = Person("Kalle", false)
    println(person.name)
    println(person.isMarried)
    person.isMarried = true
    println(person.isMarried)

    val rectangle = Rectangle(41, 43)
    println(rectangle.isSquare)

    // 2.3 Representing and handling choices: enums and “when”
    println(Color.RED.rgb())
    println(Color.BLUE.getMnemonic(Color.RED))
    println(Color.BLUE.getWarmth(Color.ORANGE))
    println(Color.BLUE.mix(Color.BLUE, Color.YELLOW))
    println(Color.BLUE.mixOptimized(Color.RED, Color.YELLOW))



    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))

    // 2.4 Iterating over things: “while” and “for” loops
    for (i in 1..100) {
        println("$i: ${fizzBuzz(i)}")
    }

    for (i in 100 downTo 0 step 2) {
        println("$i: ${fizzBuzz(i)}")
    }

    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }

    fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
    fun isNotDigit(c: Char) = c !in '0'..'9'
    println(isLetter('q'))
    println(isNotDigit('9'))

    fun recognize(c: Char) = when (c) {
        in '0'..'9' -> "It's a digit!"
        in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
        else -> "I don't know…"
    }
    println(recognize('k'))

    // 2.5 Exceptions in Kotlin
    val number = 10
    val percentage =
        if (number in 0..100) {
            println("Number $number is a percentage")
            number
        }
        else
            throw IllegalArgumentException(
                "A percentage value must be between 0 and 100: $number")

    fun readNumber(reader: BufferedReader): Int? {
        try {
            val line = reader.readLine()
            return Integer.parseInt(line)
        }
        catch (e: NumberFormatException) {
            return null
        }
        finally {
            reader.close()
        }}
    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))

    fun readNumber2(reader: BufferedReader) {
        val number = try {
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
            null
        }
        println(number)
    }
    val reader2 = BufferedReader(StringReader("not a number"))
    readNumber2(reader2)
}

