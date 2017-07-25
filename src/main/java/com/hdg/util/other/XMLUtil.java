package com.hdg.util.other;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class XMLUtil {

    public static Document getDocument(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputStream);
        return doc;
    }
}
