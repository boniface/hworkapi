package services.common.education

import com.websudos.phantom.dsl._
import domain.common.education.DegreeType
import services.common.education.Impl.DegreeTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
trait DegreeTypeService {
  def createOrUpdate(degreeType:DegreeType):Future[ResultSet]

  def getDegreeTypeById(degreeType: String): Future[Option[DegreeType]]

  def getDegreeTypes(degreeType: String): Future[Seq[DegreeType]]
}

object DegreeTypeService{
  def apply: DegreeTypeService = new DegreeTypeServiceImpl()

}

