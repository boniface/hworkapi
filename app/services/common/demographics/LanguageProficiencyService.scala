package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.{LanguageProficiency, Title}
import services.common.demographics.Impl.LanguageProficiencyServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait LanguageProficiencyService {

  def createOrUpdate(languageProficiency:LanguageProficiency):Future[ResultSet]

  def getLanguageProficiencyById(languageProficiency:String):Future[Option[Title]]

  def getLanguageProficiencies(languageProficiency:String):Future[Seq[Title]]

}

object LanguageProficiencyService{

  def apply: LanguageProficiencyService = new LanguageProficiencyServiceImpl()
}
