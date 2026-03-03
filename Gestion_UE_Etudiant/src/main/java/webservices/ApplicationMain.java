package webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

// http://localhost:8080/Gestion_UE_VF_war_exploded/webjars/swagger-ui/4.15.5/index.html#/

// http://localhost:8080/Gestion_UE_VF_war_exploded/api/openapi.json


@ApplicationPath("api")
public class ApplicationMain extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        //classes de ressources
        resources.add(HelloRessources.class);
        resources.add(ModuleApi.class);
        resources.add(UERestApi.class);

        // ressource interne de Swagger qui génère le JSON/YAML
        resources.add(OpenApiResource.class);

        return resources;
    }
}