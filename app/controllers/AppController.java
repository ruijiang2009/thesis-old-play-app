package controllers;

import models.App;
import models.Permission;
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
    public static Result show(Long id) {
        App app = App.findById(id);
        List<Permission> permissions = Permission.findByAppId(app.getId());
        return ok(views.html.app.render(app, permissions));
    }
}
