package conf.util

import java.util.Random

import org.apache.commons.lang3.RandomStringUtils

/**
  * Created by hashcode on 2015/11/07.
  */
object Util {

  import java.net._

  def md5Hash(text: String): String = {
    val hash = text + InetAddress.getLocalHost.getHostName
    java.security.MessageDigest.getInstance("MD5").digest(hash.getBytes()).map(0xFF & _).map {
      "%02x".format(_)
    }.foldLeft("") {
      _ + _
    }
  }


  private def generateOrganisationCode(name: String): String = {
    val count: Int = 4
    val useLetters: Boolean = true
    val useNumbers: Boolean = false
    val choseFrom = name.toCharArray
    RandomStringUtils.random(count, 0, 0, useLetters, useNumbers, choseFrom, new Random())
  }

  private def getSalt(): String = {
    val length: Int = 5
    val useLetters: Boolean = true
    val useNumbers: Boolean = true
    RandomStringUtils.random(length, useLetters, useNumbers)
  }

  def codeGen(name: String): String = {
    val code = generateOrganisationCode(name)
      .toCharArray
      .sortWith(_ < _)
      .mkString("").toUpperCase

    val salt = getSalt()
      .toCharArray
      .sortWith(_ < _)
      .mkString("").toUpperCase
    (code + "-" + salt)
  }
}
