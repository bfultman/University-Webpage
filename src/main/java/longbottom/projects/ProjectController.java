package longbottom.projects;

import longbottom.DAO.DAO;
import longbottom.login.LoginController;
import longbottom.util.ViewUtil;
import spark.Response;
import spark.Request;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ProjectController {
    public static Route getAllProjects = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("projects", DAO.getAllProjects());

        return ViewUtil.render(model, "/velocity/projectHome.vm");
    };

    public static Route newProject = (Request request, Response response) -> {
        LoginController.isAuthenticated(request, response);

        String name = request.queryParams("name");
        String description = request.queryParams("description");
        String managerEmail = (request.queryParams("manager"));

        int managerId = DAO.getUserIdByEmail(managerEmail);

        if (DAO.createProject(name, description, managerId)) {
            return "Project created successfully";
        } else {
            return "Project creation failed";
        }
    };

    // Deletes a project specified by a given project ID
    public static Route deleteProject = (Request request, Response response) -> {
        LoginController.isAuthenticated(request, response);

        int projectId = Integer.parseInt(request.queryParams("projectId"));

        if(DAO.deleteProject(projectId))
            return "Delete successful.";
        else
            return "Delete failed.";
    };


    public static Route updateProject = (Request request, Response response) -> {
        LoginController.isAuthenticated(request, response);

        int projectId = Integer.parseInt(request.queryParams("projectId"));
        String name = request.queryParams("name");
        String description = request.queryParams("description");
        String timeStarted = request.queryParams("time");
        int manager;

        if (request.queryParams("manager") != null) {
            manager = Integer.parseInt(request.queryParams("manager"));
            DAO.updateProjectManager(projectId, manager);
        }

        if (name != null) {
            DAO.updateProjectName(projectId, name);
        }

        if (description != null) {
            DAO.updateProjectDescription(projectId, description);
        }

        if (timeStarted != null) {
            DAO.updateProjectTime(projectId, timeStarted);
        }

        return "Update complete";
    };

}
