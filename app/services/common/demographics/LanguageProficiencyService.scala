package services.common.demographics

import com.websudos.phantom.dsl._
import domain.common.demographics.LanguageProficiency
import services.common.demographics.Impl.LanguageProficiencyServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
trait LanguageProficiencyService {

  def createOrUpdate(languageProficiency:LanguageProficiency):Future[ResultSet]

  def getLanguageProficiencyById(languageProficiency:String):Future[Option[LanguageProficiency]]

  def getLanguageProficiencies(languageProficiency:String):Future[Seq[LanguageProficiency]]

}

object LanguageProficiencyService{

  def apply: LanguageProficiencyService = new LanguageProficiencyServiceImpl()
}
