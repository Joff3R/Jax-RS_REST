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

    private ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public void deleteMessage(@PathParam("profileName") String profileName) {
        profileService.removeProfile(profileName);
    }


}
