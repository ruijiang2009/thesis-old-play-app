package controllers;

import models.Category;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.index;

import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import play.db.jpa.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/5/14
 * Time: 7:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class CategoryController extends Controller {

    public static Result list() {
        try {
            Category.findById(1L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ok(index.render("Your new application is ready."));
    }


    public static Result show(Long id) {
        Category.findById(1L);
        return ok(index.render(String.format("show category id %d", id)));
    }


}
