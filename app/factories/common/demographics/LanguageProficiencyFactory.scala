package factories.common.demographics

import domain.common.demographics.LanguageProficiency

class LanguageProficiencyFactory {
  def createLanguageProficiency(values:Map[String, String]):LanguageProficiency={
    LanguageProficiency(languageProficiencyId = values("languageProficiencyId"),name = values("name"))
  }

}