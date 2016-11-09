package services.common.education.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.education.DegreeType
import repositories.common.education.DegreeTypeRepository
import services.Service
import services.common.education.DegreeTypeService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/11/02.
  */
class DegreeTypeServiceImpl extends DegreeTypeService with Service{
  override def createOrUpdate(degreeType: DegreeType): Future[ResultSet] = {
    DegreeTypeRepository.save(degreeType)
  }

  override def getDegreeTypeById(degreeType: String): Future[Option[DegreeType]] = {
    DegreeTypeRepository.getDegreeTypeById(degreeType)
  }

  override def getDegreeTypes(degreeType: String): Future[Seq[DegreeType]] = {
    DegreeTypeRepository.getDegreeTypes(degreeType)
  }
}
