/*
 Copyright (c) 2022 JustLittleFive

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package lab5;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class fileTypeParser {

    public static void main(String[] filename) throws Exception {
        if (filename.length == 0) {
            System.err.println("Please supply filename.");
            return;
        }

        int readIn[] = new int[MAGIC_LENGTH];
        // System.out.println(filename[0]);
        FileInputStream ins = new FileInputStream(filename[0]);

        for (int i = 0; i < MAGIC_LENGTH; i++) {
            readIn[i] = ins.read();
        }

        ins.close();

        int type = typeChecker(readIn);

        System.out.println("Filename: " + filename[0]);
        System.out.print("filenFile Header(Hex): ");
        String[] out = new String[MAGIC_LENGTH];
        for (int i = 0; i < MAGIC_LENGTH; i++) {
            out[i] = Integer.toHexString(readIn[i]);
            if (out[i].length() < 2)
                out[i] = "0" + out[i];
        }
        System.out.println(Arrays.toString(out));

        switch (type) {
            case 1:
                System.out.println("File Type: png");
                break;
            case 2:
                System.out.println("File Type: zip or jar");
                break;
            case 3:
                System.out.println("File Type: class");
                break;
            default:
                System.out.println("File Type: unknown");
        }

    }

    private static final int MAGICPNG[] = new int[] { 0x89, 0x50, 0x4e, 0x47 };
    private static final int MAGICZIP[] = new int[] { 0x50, 0x4b, 0x03, 0x04 };
    private static final int MAGICLASS[] = new int[] { 0xca, 0xfe, 0xba, 0xbe };
    private static final int MAGIC_LENGTH = 4;

    private static int typeChecker(int[] fileHeader) throws Exception {
        int res = 0;

        for (int i = 0; i < MAGIC_LENGTH; i++) {
            res = 1;
            if (fileHeader[i] != MAGICPNG[i]) {
                res = 0;
                break;
            }
        }
        if (res != 0)
            return res;
        for (int i = 0; i < MAGIC_LENGTH; i++) {
            res = 2;
            if (fileHeader[i] != MAGICZIP[i]) {
                res = 0;
                break;
            }
        }
        if (res != 0)
            return res;
        for (int i = 0; i < MAGIC_LENGTH; i++) {
            res = 3;
            if (fileHeader[i] != MAGICLASS[i]) {
                res = 0;
                break;
            }
        }

        return res;
    }

}
