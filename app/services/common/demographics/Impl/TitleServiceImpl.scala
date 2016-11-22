package services.common.demographics.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.common.demographics.Title
import repositories.common.demographics.TitleRepository
import services.Service
import services.common.demographics.TitleService

import scala.concurrent.Future

/**
  * Created by Aphiwe on 2016/10/27.
  */
class TitleServiceImpl extends TitleService with Service{
  override def createOrUpdate(title: Title): Future[ResultSet] = {
    TitleRepository.save(title)
  }

  override def getTitleById(id: String): Future[Option[Title]] = {
    TitleRepository.getTitleById(id)
  }

  override def getTitles(id: String): Future[Seq[Title]] = {
    TitleRepository.getTitles(id)
  }
}
