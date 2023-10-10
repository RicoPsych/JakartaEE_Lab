package lab;

import java.util.regex.Pattern;

/**
     * Patterns used for checking servlet path.
     */
public final class Patterns {

    /**
     * UUID
     */
    private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

    public static final Pattern USERS = Pattern.compile("/users/?");

    public static final Pattern USER = Pattern.compile("/users/(%s)".formatted(UUID.pattern()));
    public static final Pattern USER_SONGS = Pattern.compile("/users/(%s)/songs".formatted(UUID.pattern()));
    public static final Pattern USER_AVATAR = Pattern.compile("/users/(%s)/avatar".formatted(UUID.pattern()));

    public static final Pattern SONGS = Pattern.compile("/songs/?");
    public static final Pattern SONG = Pattern.compile("/songs/(%s)".formatted(UUID.pattern()));

    public static final Pattern ALBUMS = Pattern.compile("/albums/?");
    public static final Pattern ALBUM = Pattern.compile("/albums/(%s)".formatted(UUID.pattern()));
    public static final Pattern ALBUM_SONGS = Pattern.compile("/albums/(%s)/songs/?".formatted(UUID.pattern()));

    public static final Pattern ARTISTS = Pattern.compile("/artists/?");
    public static final Pattern ARTIST = Pattern.compile("/artists/(%s)".formatted(UUID.pattern()));
    public static final Pattern ARTIST_SONGS = Pattern.compile("/artists/(%s)/songs/?".formatted(UUID.pattern()));

    public static final Pattern PROFESSION_CHARACTERS = Pattern.compile("/professions/(%s)/characters/?".formatted(UUID.pattern()));

    public static final Pattern USER_CHARACTERS = Pattern.compile("/users/(%s)/characters/?".formatted(UUID.pattern()));

}