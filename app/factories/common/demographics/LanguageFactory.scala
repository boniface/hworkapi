package factories.common.demographics

import domain.common.demographics.Language

class LanguageFactory {
  def createLanguage(values:Map[String, String]):Language={
 Language(languageId = values("languageId"),name = values("name"))
  }

}