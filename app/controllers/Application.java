package controllers;

import play.*;
import play.mvc.*;
import play.templates.Template;
import play.templates.TemplateLoader;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        Template template = TemplateLoader.load("app/views/Application/index.html");
        //System.out.println(template.render());
        render();
    }

}