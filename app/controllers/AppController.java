package controllers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import models.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.index;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/11/14
 * Time: 7:44 PM
 */
public class AppController extends Controller{

    @Transactional
    public static Result show(Long id, String operation, Integer num, String showDefault) {
        App app = App.findById(id);
        List<Permission> permissions = Permission.findByAppId(app.getId());
        List<Sentence> sentenceList = Sentence.findByApp(app.getId());
        List<Category> categoryList = Category.findByApp(app.getId());
        Boolean isDefault = Boolean.parseBoolean(showDefault.trim());
        if(operation == null || !operation.equals("edit")) {
            return ok(views.html.app.render(app, permissions,sentenceList, categoryList));
        } else {
            return ok(views.html.appedit.render(app, permissions,sentenceList, categoryList, num, isDefault));
        }
    }

    @Transactional
    public static Result edit(Long id) {
        App app = App.findById(id);
        List<Permission> permissions = Permission.findByAppId(app.getId());
        List<Sentence> sentenceList = Sentence.findByApp(app.getId());
        List<Category> categoryList = Category.findByApp(app.getId());
        return ok(views.html.appedit.render(app, permissions,sentenceList, categoryList, 3, true));
    }

    @Transactional
    public static Result processSentenceSubmit() {
        DynamicForm requestData = Form.form().bindFromRequest();
        System.out.println("processSentencesubmit");
        Map<String, String> data = requestData.data();
        Map<String, Sentence> sentenceMap = Maps.newHashMap();

        String sentenceId = null;
        String processedSentenceId = null;
        String processedSentenceContent = null;

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String appId = null;
        for(Map.Entry<String, String> entry : data.entrySet()) {
            if(entry.getKey().equals("appId")) {
                appId = entry.getValue();
                continue;
            }
            String[] strs = entry.getKey().split("_");

            System.out.println("key " + entry.getKey() + " value " + entry.getValue());

            // new processed sentence and value is empty, we don't need to save in database
            if(entry.getValue().equals("") && strs.length == 3) {
                System.out.println("value is empty");
                continue;
            }

            sentenceId = strs[0];
            processedSentenceId = strs.length == 3 ? strs[2] : strs[1];
            processedSentenceContent = entry.getValue();
            Sentence sentence = sentenceMap.get(sentenceId);
            if(sentence == null) {
                sentence = Sentence.findById(Long.parseLong(sentenceId));
                sentenceMap.put(sentenceId, sentence);
            }

            if(strs.length == 3) {
                // this is a new processed sentence
                System.out.println("persist a new processed sentence " + processedSentenceContent);
                entityManager.persist(new ProcessedSentence(processedSentenceContent, sentence));
            } else {
                // refresh
                System.out.println("refresh an old processed sentence " + processedSentenceContent);
                ProcessedSentence processedSentence = ProcessedSentence.findById(Long.parseLong(processedSentenceId));
                processedSentence.setContent(processedSentenceContent);
                entityManager.merge(processedSentence);
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        // persist the objects in list
        return redirect(routes.AppController.show(Long.parseLong(appId), "", 3, "true"));
    }
}
