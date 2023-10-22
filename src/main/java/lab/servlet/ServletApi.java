package lab.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.Patterns;
import lab.song.controller.SongController;
import lab.song.dto.GetSongResponse;
import lab.song.entities.Song;
import lab.user.controller.UserController;
import lab.user.dto.PostUserRequest;
import lab.user.dto.PutUserRequest;
import lab.user.entities.User;

@WebServlet(urlPatterns = {
        ServletApi.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ServletApi extends HttpServlet {

    public static final class Paths {
        public static final String API = "/api";

    }

    private UserController userController;
    private SongController songController;

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public ServletApi (UserController userController,SongController songController){
        this.userController = userController;
        this.songController = songController;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }


    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USERS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(userController.getUsers()));

                return;
            }
            else if (path.matches(Patterns.USER.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.USER, path);
                try {
                    response.getWriter().write(jsonb.toJson(userController.getUser(uuid)));
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            } 
            else if (path.matches(Patterns.SONGS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(songController.getSongs()));
                return;
            }
            else if (path.matches(Patterns.SONG.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.SONG, path);
                try {
                    GetSongResponse song = songController.getSong(uuid);
                    response.getWriter().write(jsonb.toJson(song));
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            } 
            //else if (path.matches(Patterns.PROFESSION_CHARACTERS.pattern())) {
            //     response.setContentType("application/json");
            //     UUID uuid = extractUuid(Patterns.PROFESSION_CHARACTERS, path);
            //     response.getWriter().write(jsonb.toJson(characterController.getProfessionCharacters(uuid)));
            //     return;
            // } else if (path.matches(Patterns.USER_CHARACTERS.pattern())) {
            //     response.setContentType("application/json");
            //     UUID uuid = extractUuid(Patterns.USER_CHARACTERS, path);
            //     response.getWriter().write(jsonb.toJson(characterController.getUserCharacters(uuid)));
            //     return;
            // } 
            else if (path.matches(Patterns.USER_AVATAR.pattern())) {
                response.setContentType("image/png");//could be dynamic but atm we support only one format
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                    byte[] portrait = userController.getUserAvatar(uuid);
                    response.setContentLength(portrait.length);
                    response.getOutputStream().write(portrait);
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }

                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USERS.pattern())) {
                User newUser = userController.postUser(jsonb.fromJson(request.getReader(), PostUserRequest.class));
                UUID uuid = newUser.getId();
                response.addHeader("Location", createUrl(request, Paths.API, "users", uuid.toString()));
                return;
            } //else 
            // if (path.matches(Patterns.USER.pattern())) {
            //     userController.postUser(request.getPart("portrait").getInputStream());
            //     return;
            // }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                userController.putUser(uuid, jsonb.fromJson(request.getReader(), PutUserRequest.class));
                response.addHeader("Location", createUrl(request, Paths.API, "users", uuid.toString()));
                return;
            } else 
            if (path.matches(Patterns.USER_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                    userController.putUserAvatar(uuid, request.getPart("portrait").getInputStream());
                }
                catch(Exception e){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.USER_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                userController.deleteUserAvatar(uuid);
                }
                catch (Exception e){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);   
                }
                return;
            }
            else if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                userController.deleteUser(uuid);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Called by the server (via the <code>service</code> method) to allow a servlet to handle a PATCH request.
     *
     * @param request  {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param response {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @throws ServletException if the request for the PATCH cannot be handled
     * @throws IOException      if an input or output error occurs while the servlet is handling the PATCH request
     */
    @SuppressWarnings("RedundantThrows")
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String path = parseRequestPath(request);
        // String servletPath = request.getServletPath();
        // if (Paths.API.equals(servletPath)) {
        //     if (path.matches(Patterns.CHARACTER.pattern())) {
        //         UUID uuid = extractUuid(Patterns.CHARACTER, path);
        //         characterController.patchCharacter(uuid, jsonb.fromJson(request.getReader(), PatchCharacterRequest.class));
        //         return;
        //     }
        // }
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Extracts UUID from path using provided pattern. Pattern needs to contain UUID in first regular expression group.
     *
     * @param pattern regular expression pattern with
     * @param path    request path containing UUID
     * @return extracted UUID
     */
    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }

    /**
     * Gets path info from the request and returns it. No null is returned, instead empty string is used.
     *
     * @param request original servlet request
     * @return path info (not null)
     */
    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

    /**
     * Creates URL using host, port and context root from servlet request and any number of path elements. If any of
     * path elements starts or ends with '/' character, that character is removed.
     *
     * @param request servlet request
     * @param paths   any (can be none) number of path elements
     * @return created url
     */
    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        for (String path : paths) {
            builder.append("/")
                    .append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }
}
