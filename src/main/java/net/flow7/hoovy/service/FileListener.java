package net.flow7.hoovy.service;

import java.io.File;

public interface FileListener{
    public void fileFound(File file);
    public void fileCopied(File file);
}