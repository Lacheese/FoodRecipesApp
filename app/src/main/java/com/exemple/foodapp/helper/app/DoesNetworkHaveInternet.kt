package com.exemple.foodapp.helper.app

import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory



object DoesNetworkHaveInternet {

  fun execute(socketFactory: SocketFactory): Boolean {
    return try{
      val socket = socketFactory.createSocket() ?: throw IOException()
      socket.connect(InetSocketAddress("8.8.4.4", 53), 2000)
      socket.close()
      true
    }catch (e: IOException){
      false
    }
  }
}