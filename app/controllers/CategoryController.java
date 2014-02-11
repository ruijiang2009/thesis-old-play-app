package controllers;

import models.Category;
import org.jboss.logging.Logger;
import play.mvc.Controller;
import play.mvc.Result;

import views.html.index;

import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import play.db.jpa.Transactional;


import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/5/14
 * Time: 7:41 PM
 */
@Transactional
public class CategoryController extends Controller {
    private static final Logger logger = Logger.getLogger(CategoryController.class.getName());

    public static Result list() {
        try {
            List<Category> categoryList = (List<Category>) Category.findAll();
            System.out.println("category list size is " + categoryList.size());
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
