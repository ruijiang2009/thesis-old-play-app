package controllers;

import models.App;
import models.Category;
import models.Permission;
import models.Sentence;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/11/14
 * Time: 7:44 PM
 */
public class AppController extends Controller{

    @Transactional
    public static Result show(Long id) {
        App app = App.findById(id);
        List<Permission> permissions = Permission.findByAppId(app.getId());
        List<Sentence> sentenceList = Sentence.findByApp(app.getId());
        List<Category> categoryList = Category.findByApp(app.getId());
        return ok(views.html.app.render(app, permissions,sentenceList, categoryList));
    }
}
