package services.common.util

import com.datastax.driver.core.ResultSet
import domain.common.util.{Credential, Token}
import domain.users.UserGeneratedToken
import services.Service
import services.common.util.Impl.TokenServiceImpl

import scala.concurrent.Future


/**
  * Created by hashcode on 2016/04/21.
  */

trait TokenService {
  def save(token: Token): Future[ResultSet]

  def createNewToken(credential: Credential): Future[UserGeneratedToken]

  def revokeToken(token: String): Future[ResultSet]

  def getTokenRoles(token: String): String

  def getEmail(token: String): String

  def getOrgCode(token: String): String

  def isTokenValid(token: String,password:String): Future[Boolean]

  def hasTokenExpired(token:String):Future[Boolean]
}

object TokenService extends Service {
  def apply(): TokenService = new TokenServiceImpl()
}
