package ru.Anvar.Chat

import java.net.Socket

class ConnectedClient(val socket: Socket) {

    private val communicator = Communicator(socket)

    init{
        communicator.start { message -> parse(message) }
    }

    private fun parse(message: String){
        communicator.sendMessage(message)
    }

    fun stop(){
        communicator.stop()
    }
}