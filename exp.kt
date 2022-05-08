var PRICE = 0
const val ALLCOST = 0
fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()
    val list = mutableListOf(mutableListOf<Char>())
    seatsInTheHall(rows, seats, list)

    var answer: Int = 3
    var allCost = 0

    for (i in 0 until list.size) {
        for (j in 0 until list[i].size) {
            if (list.size  * list[i].size <= 60) {
                allCost += 10
            }
            else if (i in 0 until list.size / 2) {
                allCost += 10
            }
            else {
                allCost += 8
            }
        }
    }

    while (answer != 0) {
        println()
        println("""1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""")
        answer = readLine()!!.toInt()
        when (answer) {
            1 -> showTheSeats(rows, seats, list)
            2 -> buyTheTicket(list)
            3 -> allStat(list, allCost)
        }
    }

}
fun allStat(list: MutableList<MutableList<Char>>, allCost: Int) {
    println()
    var count = 0
    for (i in 0 until list.size) {
        for (j in 0 until  list[i].size) {
            if (list[i][j] == 'B') {
                count++
            }
        }
    }
    println("Number of purchased tickets: $count")
    val percentage = "%.2f".format(count.toDouble() / (list.size * list[0].size).toDouble() * 100.0)
    println("Percentage: $percentage%")
    println("Current income: $$PRICE")
    println("Total income: $$allCost")
}

fun buyTheTicket(list: MutableList<MutableList<Char>>) {
    println()
    var count = 0
    var rowsNeed: Int = 0
    loop@while (count == 0) {
        println("Enter a row number:")
        rowsNeed = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        val seatsNeed = readLine()!!.toInt()

        try {
            if (list[rowsNeed - 1][seatsNeed - 1] == 'S') ++count
            else {
                println()
                println("That ticket has already been purchased!")
                println()
                continue@loop
            }
        } catch (e: IndexOutOfBoundsException) {
            println()
            println("Wrong input!")
            println()
            continue@loop
        }
            list[rowsNeed - 1][seatsNeed - 1] = 'B'
    }

    if (list.size * list[0].size <= 60) {
        println("Ticket price: $${10}")
        PRICE += 10
    }
    else if (rowsNeed <= list.size / 2) {
        println("Ticket price: $${10}")
        PRICE += 10
    }
    else {
        println("Ticket price: $${8}")
        PRICE += 8
    }
}

fun seatsInTheHall(rows: Int, seats: Int, list: MutableList<MutableList<Char>>) {
    for (i in  0 until rows) {
        if (i > 0) list.add(i, mutableListOf())
        for (j in 0 until seats) {
            list[i].add( j, 'S')
        }
    }
}

fun showTheSeats(rows: Int, seats: Int, list: MutableList<MutableList<Char>>) {
    println()
    println("Cinema:")
    print(" ")
    for (i in 0 until seats)
    {
        print(" ${i + 1}")
    }
    println()
    for (i in 0 until rows) {
        print("${i + 1}")
        for (j in 0 until seats) {
            print(" ${list[i][j]}")
        }
        println()
    }
}