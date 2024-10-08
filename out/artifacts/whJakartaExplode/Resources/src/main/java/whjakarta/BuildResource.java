package whjakarta;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@Path("/build")
public class BuildResource {

    @GET
    @Produces(MediaType.TEXT_HTML) // Produces HTML instead of plain text
    public Response getBuildStatus() {
        // Get the current date and time (simulating build time)
        LocalDateTime buildTime = LocalDateTime.now();

        // Simulate a build status check (replace this with actual logic if needed)
        String buildStatus = "SUCCESS"; // Could be SUCCESS, FAILURE, etc.

        // Build an HTML response
        String htmlResponse = "<html>" +
                "<head><title>Build Status</title></head>" +
                "<body>" +
                "<h1>Latest Build Status</h1>" +
                "<p><strong>Build Time:</strong> " + buildTime + "</p>" +
                "<p><strong>Status:</strong> " + buildStatus + "</p>" +
                "</body>" +
                "</html>";

        // Return the HTML response
        return Response.ok(htmlResponse).build();
    }
}
