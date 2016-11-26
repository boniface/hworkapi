package services.common.util.Impl

import java.util.UUID

import authentikat.jwt.{JsonWebToken, JwtClaimsSet, JwtHeader}
import com.datastax.driver.core.ResultSet
import com.github.nscala_time.time.Imports._
import com.github.t3hnar.bcrypt._
import conf.util.Util
import domain.common.util.{Credential, Token}
import domain.users.{User, UserGeneratedToken}
import repositories.common.util.TokenRepository
import services.Service
import services.common.util.TokenService
import services.users.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
  * Created by hashcode on 2016/09/09.
  */
class TokenServiceImpl extends TokenService with Service {
  override def save(token: Token): Future[ResultSet] = {
    TokenRepository.save(token)
  }

  override def createNewToken(credential: Credential): Future[UserGeneratedToken] = {
    val userAccounts = UserService.apply().getUserByEmail(credential.email)
    val results = userAccounts map {
      case Nil => Future {
        UserGeneratedToken("NONE", "FAILED", "USER NOT FOUND", "NONE")
      }
      case user :: Nil => {
        if (credential.password.isBcrypted(user.authvalue)) {
          val checkAccounts = Try {
            for {
              token <- generateToken(user, credential) if credential.password.isBcrypted(user.authvalue)
              saveToken <- save(Token(getTokenId(token), token)) if credential.password.isBcrypted(user.authvalue)
            } yield UserGeneratedToken(token, "SUCCEED", "USER AUTHENTICATED", user.organisationId)
          }
          checkAccounts match {
            case Success(foundUsers) => foundUsers
            case Failure(f) => Future {
              UserGeneratedToken("NONE", "FAILED", "USER NOT FOUND", "")
            }
          }
        } else {
          Future {
            UserGeneratedToken("NONE", "FAILED", "WRONG PASSWORD", "NONE")
          }
        }
      }
      case accounts@(user :: tail) => {

        Future {
          UserGeneratedToken("NONE", "MULTIPLE", "MULTIPLE ACCOUNTS FOUND", user.organisationId)
        }
      }
    }
    results flatMap (result => result)

  }

  override def revokeToken(token: String): Future[ResultSet] = {
    val tokenId = getTokenId(token)
    for {
      token <- TokenRepository.getTokenById(tokenId)
      revoke <- TokenRepository.save(token.get.copy(tokenValue = "REVOKED"))
    } yield revoke
  }

  override def getTokenRoles(token: String): String = {
    val claims = getClaims(token)
    val roles = claims.getOrElse(Map.empty[String, String]).get("role")
    roles match {
      case Some(role) => role
      case None => "NONE"
    }
  }

  override def getEmail(token: String): String = {
    val claims = getClaims(token)
    claims.getOrElse(Map.empty[String, String]).get("sub") match {
      case Some(id) => id.toString
      case None => "WRONG TOKEN"
    }
  }

  override def isTokenValid(token: String, password: String): Future[Boolean] = {

    Future {
      JsonWebToken.validate(token, password)
    }

  }

  private def getTokenId(token: String): String = {
    val claims = getClaims(token)
    claims.getOrElse(Map.empty[String, String]).get("jit") match {
      case Some(id) => id.toString
      case None => ""
    }
  }

  private def getCreationDate(token: String): String = {
    val claims = getClaims(token)
    claims.getOrElse(Map.empty[String, String]).get("creation") match {
      case Some(date) => date.toString
      case None => ""
    }
  }

  private def getDuration(token: String): String = {
    val claims = getClaims(token)
    claims.getOrElse(Map.empty[String, String]).get("exp") match {
      case Some(date) => date.toString
      case None => ""
    }
  }

  private def getClaims(token: String): Option[Map[String, String]] = {
    token match {
      case JsonWebToken(header, claims, signature) =>
        claims.asSimpleMap.toOption
      case x =>
        None
    }
  }

  def generateToken(user: User, credential: Credential): Future[String] = {
    val signature = Future {
      credential.email
    }
    val userRole = UserService.apply.getRole(user) map (role => {
      role map (e => e.roleId)
    })



    val tokenSignatureAndRoles = for {
      tokenSignature <- signature
      tokenRole <- userRole
    } yield (tokenSignature, tokenRole)

    tokenSignatureAndRoles map (tokenValues => {
      val claims = JwtClaimsSet(Map(
        "iss" -> user.organisationId,
        "sub" -> user.email,
        "role" -> tokenValues._2.head, //getOrElse("")
        "exp" -> DateTime.now.plusHours(12).getMillis,
        "creation" -> DateTime.now.getMillis,
        "iat" -> DateTime.now.getMillis,
        "jit" -> Util.md5Hash(UUID.randomUUID().toString)))
      JsonWebToken(JwtHeader("HS256", "Hashcode"), claims, tokenValues._1)
    })
  }

  override def hasTokenExpired(token: String): Future[Boolean] = {
    Future {
      DateTime.now() < DateTime.parse(getCreationDate(token)).plus(getDuration(token).toLong)
    }
  }

  override def getOrgCode(token: String): String = {
    val claims = getClaims(token)
    claims.getOrElse(Map.empty[String, String]).get("iss") match {
      case Some(id) => id.toString
      case None => ""
    }
  }
}
