package fr.xgouchet.axml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public final class CompressedXmlUtils {

    /**
     * @param input an input stream
     * @return if the given input stream looks like an AXML document
     */
    public static boolean isCompressedXml(final InputStream input) {
        if (input == null) return false;

        boolean result;

        try {
            final byte[] header = new byte[4];
            int read = input.read(header, 0, 4);
            if (read < 4) return false;

            result = (header[0] == 0x03)
                    && (header[1] == 0x00)
                    && (header[2] == 0x08)
                    && (header[3] == 0x00);

        } catch (Exception e) {
            result = false;
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                // ignore this exception
            }
        }

        return result;
    }

    /**
     * @param source a source file
     * @return if the given file looks like an AXML file
     */
    public static boolean isCompressedXml(final File source) {
        boolean result;

        try {
            final InputStream input = new FileInputStream(source.getPath());
            result = isCompressedXml(input);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    private CompressedXmlUtils() {
    }
}
