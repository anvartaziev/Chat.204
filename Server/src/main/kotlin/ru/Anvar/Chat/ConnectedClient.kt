package ru.Anvar.Chat

import java.net.Socket

class ConnectedClient(val socket: Socket) {

    private companion object {
        private val connectedClients = mutableListOf<ConnectedClient>()
        private val usernames = mutableSetOf<String>()
    }

    private val communicator = Communicator(socket)
    private var userName: String? = null

    init{
        connectedClients.add(this)
        communicator.start { message -> parse(message) }
    }

    private fun parse(message: String){
        if (userName == null) {
            val proposedName = message.trim()
            synchronized(usernames) {
                if (proposedName in usernames) {
                    communicator.sendMessage("Ник '$proposedName' уже занят. Попробуйте другой.")
                    stop()
                } else {
                    userName = proposedName
                    usernames.add(proposedName)
                    communicator.sendMessage("Вы вошли как $proposedName.")
                }
            }
        } else {
            sendToAll(message, echo = false)
        }
    }

    fun stop() {
        communicator.stop()
        synchronized(usernames) {
            userName?.let { usernames.remove(it) }
        }
        connectedClients.remove(this)
    }

    private fun sendToAll(message: String, echo: Boolean = true){
        connectedClients.forEach {
            if (echo || it != this) it.communicator.sendMessage(message)
        }
    }
    }