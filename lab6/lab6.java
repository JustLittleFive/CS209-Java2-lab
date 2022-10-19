/*
 * Copyright (c) 2022 SUSTech - JustLittleFive
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <https://www.gnu.org/licenses/>.
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.stream.Stream;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class lab6 {

    public static void main(final String[] args) {
        try {
            readZipFile("src.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            readJarFile("rt.jar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readZipFile(final String file) throws Exception {
        try (ZipFile fileName = new ZipFile(file)) {
            Enumeration<? extends ZipEntry> entries = fileName.entries();
            int count = 0;
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    if (entry.getName().matches("java.io.+.")
                            || entry.getName().matches("java.nio.+.")) {
                        System.out.println(entry.getName());
                        count++;
                    }
                }
            }
            System.out.print("Matches files in .zip: ");
            System.out.println(count);
        }
    }

    public static void readJarFile(final String file) throws Exception {
        try (JarFile fileName = new JarFile(file)) {
            Enumeration<? extends JarEntry> entries = fileName.entries();
            int count = 0;
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    if (entry.getName().matches("java.io.+.")
                            || entry.getName().matches("java.nio.+.")) {
                        System.out.println(entry.getName());
                        count++;
                    }
                }
            }
            System.out.print("Matches files in .jar: ");
            System.out.println(count);
        }
    }
}
