package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/ue")
public class UERestApi {
    //methode=webservice de l'UE = rest api
    public static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();
    @GET
    @Path("/list")
    // @Consumes(MediaType.APPLICATION_JSON)//type de d entree  est json
    @Produces(MediaType.APPLICATION_JSON)//type de sortie est text
    public Response getAll() {
        return Response.status(200)
                .entity(helper.getListeUE())
                .build();
        //retutn Response.ok.entity(this.helper.getListeUE()).build();
    }
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUE(UniteEnseignement ue) {
        return Response.status(201)
                .entity(helper.addUniteEnseignement(ue))
                .build();
    }

    @DELETE
    @Path("/delete/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        return Response.status(200)
                .entity(helper.deleteUniteEnseignement(code))
                .build();
    }
    @PUT
    @Path("/update/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement updatedUE) {
        return Response.status(200)
                .entity(helper.updateUniteEnseignement(code, updatedUE))
                .build();
    }
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam(value = "code") int code) {

        return Response.status(200)
                .entity(this.helper.getUEByCode(code))
                .build();
    }
    @GET
    @Path("domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)//bich ye5dem bil json fi spring boot
    public Response getUEByDomaine(@PathParam(value = "domaine") String domaine) {

        return Response.status(200)
                .entity(this.helper.getUEByDomaine(domaine))
                .build();
    }




    @GET
    @Path("Semestre/{Semestre}")
    @Produces(MediaType.APPLICATION_JSON)//bich ye5dem bil json fi spring boot
    public Response getUEBySemestre(@PathParam(value = "Semestre") int Semestre) {

        return Response.status(200)
                .entity(helper.getUEBySemestre(Semestre))
                .build();
    }
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)//bich ye5dem bil json fi spring boot
    public Response getUEBySemestre2(@QueryParam(value = "semestre") int s) {

        return Response.status(200)
                .entity(helper.getUEBySemestre(s))
                .build();
    }


    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)//bich ye5dem bil json fi spring boot
    public Response sayHelloQuerry(@QueryParam(value = "name") String name) {

        return Response.status(200)
                .entity("Hello Querry " + name + "!")
                .build();
    }
}
