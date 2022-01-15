package resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.Profile;
import service.ProfileService;

import java.util.List;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private final ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @GET
    @Path("/{id}")
    public Profile getProfile(@PathParam("id") int id) {
        return profileService.getProfile(id);
    }

    @PUT
    @Path("/{id}")
    public Profile updateProfile(@PathParam("id") int id, Profile profile) {
        profile.setId(id);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMessage(@PathParam("id") int id) {
        profileService.removeProfile(id);
    }


}
