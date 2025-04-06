package ru.Anvar.Chat;

import java.io.PrintWriter
import java.net.Socket
import java.util.*

class Client (
    val host : String,
     val port: Int
){
     private val socket = Socket(host, port)
     init{
         val reader = Scanner(socket.getInputStream())
         val message = reader.nextLine()
         println("Сервер: $message")
         val writer = PrintWriter(socket.getOutputStream())
         writer.println("Привет, сервер!")
         writer.flush()
         socket.close()
     }
}
