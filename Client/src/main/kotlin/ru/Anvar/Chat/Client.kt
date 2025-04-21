package ru.Anvar.Chat;


import java.net.Socket
import java.util.*


class Client(
    val host: String,
    val port: Int,
) {

    private val communicator = Communicator(Socket(host, port))
    private  val username = registration()
    init {
        communicator.start(::parse)
        communicator.sendMessage(username)

        val userScanner = Scanner(System.`in`)
        while(communicator.isRunning) {
            val userInput = userScanner.nextLine()
            if (userInput.isNotBlank())
                communicator.sendMessage("$username: $userInput")
            else
                stop()
        }
    }
    private fun registration(): String {
        println("Введите Ваш никнейм:")
        return readlnOrNull() ?: "Anonymous"
    }


    private fun parse(message: String){
        println(message)
    }

    fun stop(){
        communicator.stop()
    }
}