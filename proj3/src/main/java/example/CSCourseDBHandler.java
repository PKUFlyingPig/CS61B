package example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parse XML file to fill a CSCourseDB.
 * <a href="https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html">SAX parser tutorial</a>.
 *
 * @author Mo
 */
public class CSCourseDBHandler extends DefaultHandler {
    private final CSCourseDB db;
    private String activeState = "";
    private CSCourseDB.Course lastCourse;
    private String from;
    private String to;

    CSCourseDBHandler(CSCourseDB db) {
        this.db = db;
        this.lastCourse = null;
    }

    /**
     * Called at the beginning of an element. Typically, you will want to handle each element in
     * here, and you may want to track the parent element.
     *
     * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if
     * Namespace processing is not being performed.
     * @param localName The local name (without prefix), or the empty string if Namespace processing
     * is not being performed.
     * @param qName The qualified name (with prefix), or the empty string if qualified names are not
     * available.
     * @param attributes The attributes attached to the element. If there are no attributes, it
     * shall be an empty Attributes object.
     * @throws SAXException Any SAX exception, possibly wrapping another exception.
     * @see Attributes
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {

        if (qName.equals("course")) {
            activeState = "course";
            CSCourseDB.Course c = new CSCourseDB.Course(attributes.getValue("id"),
                attributes.getValue("division"));
            db.addCourse(c);
            lastCourse = c;
        } else if (qName.equals("req")) {
            activeState = "req";
            lastCourse = null;
        } else if (activeState.equals("req") && qName.equals("from")) {
            from = attributes.getValue("ref");
        } else if (activeState.equals("req") && qName.equals("to")) {
            to = attributes.getValue("ref");
        } else if (activeState.equals("course") && qName.equals("tag")) {
            lastCourse.extraInfo.put(attributes.getValue("k"), attributes.getValue("v"));
        }
    }

    /**
     * Receive notification of the end of an element. You may want to take specific terminating
     * actions here, like finalizing vertices or edges found.
     *
     * @param uri The Namespace URI, or the empty string if the element has no Namespace URI or if
     * Namespace processing is not being performed.
     * @param localName The local name (without prefix), or the empty string if Namespace processing
     * is not being performed.
     * @param qName The qualified name (with prefix), or the empty string if qualified names are not
     * available.
     * @throws SAXException Any SAX exception, possibly wrapping another exception.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("req")) {
            this.db.addPrereq(from, to);
            from = null;
            to = null;
        }
    }
}
