package controllers;

import models.App;
import models.Sentence;
import play.db.jpa.Transactional;
import play.mvc.Result;
import views.html.index;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static play.mvc.Results.ok;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/13/14
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class OperationController {
    @Transactional
    public static Result parseSentence(){
        List<App> apps = App.findAll();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<String> sentences = null;
        for(App app : apps) {
            sentences = app.processDescription();
            entityManager.getTransaction().begin();
            for(String sentence : sentences) {
                entityManager.persist(new Sentence(sentence, app.getId()));
            }
            entityManager.getTransaction().commit();
        }
        entityManager.close();
        return ok(index.render("Sentence has been passed."));
    }
}
