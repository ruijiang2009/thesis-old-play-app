package controllers;

import models.App;
import models.Category;
import org.jboss.logging.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;
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
        System.out.println("list function");
        List<Category> categoryList = null;
        try {
             categoryList = Category.findAll();
            System.out.println("category list size is " + categoryList.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ok(views.html.categorylist.render(categoryList));
    }


    public static Result show(Long id, String categoryName) {
        System.out.println("showfunction");
        List<App> apps = App.findByCategoryId(id);
        return ok(views.html.category.render(categoryName, apps));
    }


}
