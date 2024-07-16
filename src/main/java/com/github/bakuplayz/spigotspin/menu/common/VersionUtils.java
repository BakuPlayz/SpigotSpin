package com.github.bakuplayz.spigotspin.menu.common;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class VersionUtils {

    public static final String SERVER_VERSION = Bukkit.getBukkitVersion().split("-")[0].substring(2);


    /**
     * Gets the server version.
     *
     * @return the version of the server.
     */
    @NotNull
    public static String getServerVersion() {
        return SERVER_VERSION;
    }


    /**
     * Checks whether the {@link #getServerVersion() server version} is within or equal the min and max versions.
     *
     * @param min the minimum version.
     * @param max the maximum version.
     *
     * @return true if between the interval, otherwise false.
     */
    public static boolean between(double min, double max) {
        double serverVersion = Double.parseDouble(VersionUtils.getServerVersion());
        return (serverVersion >= min) && (max >= serverVersion);
    }

}
