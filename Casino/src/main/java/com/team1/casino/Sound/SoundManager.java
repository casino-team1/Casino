/*
 * This peace of Software has been written by Nick Flückiger
 * You are free to use and modifiy this software to your needs
 * For information and contact with the developer please write to
 * Mail: nick.flueckiger@outlook.de
 */
package com.team1.casino.Sound;

/**
 *
 * @author Nick Flückiger
 */
public abstract class SoundManager {

    private String relativeSoundPath;

    public SoundManager(String soundPath) {
        this.relativeSoundPath = soundPath;
    }

    public String getRelativeSoundPath() {
        return relativeSoundPath;
    }

    public boolean isValidSoundFile() {
        return false;
    }

    public void playSound() {

    }

}
