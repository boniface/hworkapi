package services.common.education

import com.websudos.phantom.dsl._
import domain.common.education.EducationType
import services.common.education.Impl.EducationTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait EducationTypeService {

  def createOrUpdate(educationType:EducationType):Future[ResultSet]

  def getEducationTypeById(educationType: String): Future[Option[EducationType]]

  def getEducationTypes(educationType: String): Future[Seq[EducationType]]
}

object EducationTypeService{
  def apply: EducationTypeService = new EducationTypeServiceImpl()

}
