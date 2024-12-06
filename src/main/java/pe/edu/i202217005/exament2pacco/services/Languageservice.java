package pe.edu.i202217005.exament2pacco.services;


import pe.edu.i202217005.exament2pacco.entity.Language;
import java.util.List;

public interface Languageservice {
    List<Language> getAllLanguages();
    Language getLanguageById(Integer id);
    Language saveLanguage(Language language);
    void deleteLanguage(Integer id);
}