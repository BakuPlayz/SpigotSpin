/**
 * CropClick - "A Spigot plugin aimed at making your farming faster, and more customizable."
 * <p>
 * Copyright (C) 2024 BakuPlayz
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.bakuplayz.spigotspin.container;

import com.github.bakuplayz.spigotspin.SpigotSpin;
import com.github.bakuplayz.spigotspin.container.common.JacksonParser;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

public abstract class DataContainer<D> {

    protected final String fileName;

    @Setter
    @Getter
    private D data;

    @Setter
    private File file;

    @Setter
    @NotNull
    private EventHandler handler;


    public DataContainer(@NotNull String fileName) {
        this.handler = new BasicEventHandler();
        this.fileName = fileName;
    }


    /**
     * Sets up the data container.
     */
    public final void create() {
        setFile(getNewFileInstance());
        try {
            Files.createFile(Paths.get(file.getAbsolutePath()));
            setData(JacksonParser.deserializeFile(file));
            handler.onCreateSuccess();
        } catch (IOException e) {
            handler.onCreateFailure();
        }
    }


    /**
     * Reloads the data container.
     */
    public final void reload() {
        setFile(getNewFileInstance());
        try {
            setData(JacksonParser.deserializeFile(file));
            handler.onReloadSuccess();
        } catch (IOException e) {
            handler.onReloadSuccess();
        }
    }


    /**
     * Saves the data container to it's dedicated file.
     */
    public void save() {
        try {
            JacksonParser.serializeFile(file.getAbsolutePath(), data);
            handler.onSaveSuccess();
        } catch (IOException e) {
            handler.onSaveFailure();
        }
    }


    /**
     * Resets the data container.
     */
    public void reset() {
        try {
            Files.deleteIfExists(file.toPath());
            create();
            handler.onResetSuccess();
        } catch (IOException exception) {
            handler.onReloadFailure();
        }
    }


    @NotNull
    private File getNewFileInstance() {
        return new File(SpigotSpin.Plugin.REF.getPlugin().getDataFolder().getAbsolutePath() + "/data", fileName);
    }


    protected interface EventHandler {

        void onCreateSuccess();


        void onCreateFailure();


        void onReloadSuccess();


        void onReloadFailure();


        void onSaveSuccess();


        void onSaveFailure();


        void onResetSuccess();


        void onResetFailure();

    }

    private final class BasicEventHandler implements EventHandler {

        @Override
        public void onCreateSuccess() {

        }


        @Override
        public void onCreateFailure() {
            SpigotSpin.LOGGER.log(Level.WARNING, "Could not create file: {}", file.getAbsolutePath());
        }


        @Override
        public void onReloadSuccess() {

        }


        @Override
        public void onReloadFailure() {
            SpigotSpin.LOGGER.log(Level.WARNING, "Could not reload file: {}", file.getAbsolutePath());
        }


        @Override
        public void onSaveSuccess() {

        }


        @Override
        public void onSaveFailure() {
            SpigotSpin.LOGGER.log(Level.WARNING, "Could not save file: {}", file.getAbsolutePath());
        }


        @Override
        public void onResetSuccess() {

        }


        @Override
        public void onResetFailure() {
            SpigotSpin.LOGGER.log(Level.WARNING, "Could not reset file: {}", file.getAbsolutePath());
        }

    }

}
