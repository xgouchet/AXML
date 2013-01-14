package fr.xgouchet.axml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public final class CompressedXmlUtils {

	public static boolean isCompressedXml(final File source) {
		boolean result;

		try {
			final InputStream input = new FileInputStream(source.getPath());
			final byte[] header = new byte[4];
			input.read(header, 0, 4);

			result = true;
			result &= (header[0] == 0x03);
			result &= (header[1] == 0x00);
			result &= (header[2] == 0x08);
			result &= (header[3] == 0x00);

			input.close();
		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	private CompressedXmlUtils() {
	}
}
