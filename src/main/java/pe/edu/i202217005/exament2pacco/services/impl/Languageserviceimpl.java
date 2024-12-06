package pe.edu.i202217005.exament2pacco.services.impl;


import org.springframework.stereotype.Service;
import pe.edu.i202217005.exament2pacco.entity.Language;
import pe.edu.i202217005.exament2pacco.repository.LanguageRepository;
import pe.edu.i202217005.exament2pacco.services.Languageservice;
import java.util.List;

@Service
public class Languageserviceimpl implements Languageservice {
    private final LanguageRepository languageRepository;

    public Languageserviceimpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageById(Integer id) {
        return languageRepository.findById(id).orElse(null);
    }

    @Override
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteLanguage(Integer id) {
        languageRepository.deleteById(id);
    }
}