package ru.Anvar.Chat

import java.net.ServerSocket
import kotlin.concurrent.thread

class Server(
    val port: Int = 5204
) {
    private val serverSocket = ServerSocket(port)

    init {
        thread {
            while (true) {
                val socket = serverSocket.accept()
                ConnectedClient(socket)
            }
            serverSocket.close()
        }
    }
}