package ru.Anvar.Chat

import java.io.PrintWriter
import java.net.ServerSocket
import java.util.Scanner

class Server (
    val port: Int = 5204
){
    private val  serverSocket = ServerSocket(port)

    init { //тело конструктора
        val socket = serverSocket.accept() //сервер блокируется  до получения информации
        val writer = PrintWriter(socket.getOutputStream())
        writer.println("Соединение установлено!")
        writer.flush() //сбрасывает сообщение из буфера в сеть, чтобы не ждать накопления пакета в 1,5 кб
        val reader = Scanner(socket.getInputStream())
        val message = reader.nextLine()
        println("Клиент: $message")
        socket.close() //закрываем подключение
        serverSocket.close()
    }
}