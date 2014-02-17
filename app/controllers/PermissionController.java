package controllers;

import models.App;
import models.Permission;
import models.PermissionRank;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Iterator;
import java.util.List;

/**
 * User: ruijiang
 * Date: 2/12/14
 * Time: 8:17 PM
 */
public class PermissionController extends Controller {
    public static Result show(Long id) {
        Permission permission = Permission.findById(id);
        List<App> apps = App.findByPermissionId(id);
        return ok(views.html.permission.render(apps, permission));
    }

    @Transactional
    public static Result list() {
        List<Permission> permissions = Permission.findAll();
        List<App> apps = App.findAll();
        return ok(views.html.permissionlist.render(permissions, apps));
    }
}
