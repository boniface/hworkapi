package services.Training.Impl


import domain.training.courses.CourseSubjects
import services.Service
import services.Training.CourseSubjectService

import scala.concurrent.Future
import com.websudos.phantom.dsl._
import repositories.training.courses.CourseSubjectsRepository
/**
 * Created by gavin.ackerman on 2016-11-13.
 */
class CourseSubjectsServiceImpl extends CourseSubjectService with Service{
  def createOrUpdate(courseSubjects: CourseSubjects): Future[ResultSet] = {
    CourseSubjectsRepository.save(courseSubjects)
  }

  def getCourseSubjectById( id: String, courseId: String, subjectId: String): Future[Option[CourseSubjects]] = {
    CourseSubjectsRepository.getCourseSubjectsById( id, courseId,subjectId)
  }

  def getAllCourseCourseSubjects(): Future[Seq[CourseSubjects]] = {
    CourseSubjectsRepository.getAllCourseCourseSubjects
  }
  def getCourseSubjects(id: String ): Future[Seq[CourseSubjects]] = {
    CourseSubjectsRepository.getCourseSubjects(id)
  }


}