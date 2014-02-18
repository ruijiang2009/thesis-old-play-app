package controllers;

import models.App;
import models.Category;
import models.Permission;
import models.Sentence;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.index;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/11/14
 * Time: 7:44 PM
 */
public class AppController extends Controller{

    @Transactional
    public static Result show(Long id, String operation) {
        App app = App.findById(id);
        List<Permission> permissions = Permission.findByAppId(app.getId());
        List<Sentence> sentenceList = Sentence.findByApp(app.getId());
        List<Category> categoryList = Category.findByApp(app.getId());
        if(operation.equals("edit")) {
            return ok(views.html.appedit.render(app, permissions,sentenceList, categoryList));
        } else {
            return ok(views.html.app.render(app, permissions,sentenceList, categoryList));
        }
    }

    @Transactional
    public static Result edit(Long id) {
        App app = App.findById(id);
        List<Permission> permissions = Permission.findByAppId(app.getId());
        List<Sentence> sentenceList = Sentence.findByApp(app.getId());
        List<Category> categoryList = Category.findByApp(app.getId());
        return ok(views.html.appedit.render(app, permissions,sentenceList, categoryList));
    }

    public static Result processSentenceSubmit() {
        DynamicForm requestData = Form.form().bindFromRequest();
        System.out.println("processSentencesubmit");
        Map<String, String> data = requestData.data();
        for(Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println("key " + entry.getKey() + " value " + entry.getValue());
        }
        return ok(index.render("Your new application is ready."));
    }
}
