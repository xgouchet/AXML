import fr.xgouchet.axml.CompressedXmlParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

public class DumpApkXml {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: java DumpApkXml <file.apk|file.zip> [<path-in-apk>]");
            System.err.println("       java DumpApkXml <file-containing-entry-from-apk>");
            System.exit(21);
        }

        String fileName = args[0];
        InputStream is = null;
        ZipFile zip = null;

        if (fileName.endsWith(".apk") || fileName.endsWith(".zip")) {
            String entryName = args.length > 1? args[1] : "AndroidManifest.xml";
            zip = new ZipFile(fileName);
            ZipEntry entry = zip.getEntry(entryName);
            is = zip.getInputStream(entry);
        } else {
            is = new FileInputStream(fileName);
        }

        try {
            Document doc = new CompressedXmlParser().parseDOM(is);
            dumpNode(doc.getChildNodes().item(0), "");
        }
        catch (Exception e) {
            System.err.println("Failed AXML decode: " + e);
            e.printStackTrace();
        }

        is.close();
        if (zip != null) {
            zip.close();
        }
    }

    private static void dumpNode(Node node, String indent) {
        System.out.println(indent + node.getNodeName() + " " + attrsToString(node.getAttributes()) + " -> " + node.getNodeValue());
        NodeList children = node.getChildNodes();
        for (int i = 0, n = children.getLength(); i < n; ++i)
            dumpNode(children.item(i), indent + "   ");
    }

    private static String attrsToString(NamedNodeMap attrs) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0, n = attrs.getLength(); i < n; ++i) {
            if (i != 0)
                sb.append(", ");
            Node attr = attrs.item(i);
            sb.append(attr.getNodeName() + "=" + attr.getNodeValue());
        }
        sb.append(']');
        return sb.toString();
    }
}
