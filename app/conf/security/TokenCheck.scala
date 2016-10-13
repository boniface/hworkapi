package conf.security

import play.api.libs.json.JsValue
import play.api.mvc.{AnyContent, Request}
import services.util.TokenService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/10/06.
  */
object TokenCheck {
  def getTokenfromParam(request: Request[AnyContent]) = {
    val token = request.headers.get("Authorization")
    val tokenValue = token match {
      case Some(t) => t
      case None => ""
    }
    check(tokenValue) map (tkn => {
      if(tkn) LogInStatus("VALID")
      else LogInStatus("INVALID")
    })
  }

  def getToken(request: Request[JsValue]) = {
    val token = request.headers.get("Authorization")
    val tokenValue = token match {
      case Some(t) => t
      case None => ""
    }
    check(tokenValue) map (tkn => {
      if(tkn) LogInStatus("VALID")
      else LogInStatus("INVALID")
    })
  }

  private def check(token: String) = {
    val code = TokenService.apply().getEmail(token)
    TokenService.apply.isTokenValid(token, code)
  }


}
