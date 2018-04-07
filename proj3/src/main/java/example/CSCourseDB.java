package example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Dummy DB of Berkeley CS courses, populated from an XML file.
 */
public class CSCourseDB {

    private final Map<String, Course> courses = new LinkedHashMap<>();

    private CSCourseDB(String dbPath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CSCourseDBHandler prereqHandler = new CSCourseDBHandler(this);
            File in = new File(dbPath);
            saxParser.parse(in, prereqHandler);
        } catch (NullPointerException | ParserConfigurationException | SAXException | IOException
            e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a course to the database.
     *
     * @param c course
     */
    void addCourse(Course c) {
        this.courses.put(c.id, c);
    }

    /**
     * Add a prerequisite to the database. A student must take "from" before "to".
     *
     * @param from course that must be taken before "to"
     * @param to course that can be taken after "from"
     */
    void addPrereq(String from, String to) {
        this.courses.get(to).prereqs.add(from);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================BERKELEY CS PREREQS==================\n");
        for (String courseId : courses.keySet()) {
            Course c = courses.get(courseId);
            sb.append(c);
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * A CS course.
     */
    static class Course {
        String id;
        String division;
        Set<String> prereqs;
        Map<String, String> extraInfo;

        Course(String id, String division) {
            this.id = id;
            this.division = division;
            this.prereqs = new HashSet<>();
            this.extraInfo = new HashMap<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("=========COURSE ID: ").append(id).append("=========\n");
            sb.append("* Division: ").append(division).append('\n');

            /* Print extra information about the course if available. */
            if (!extraInfo.isEmpty()) {
                sb.append("* Extra Information:\n");
                for (Map.Entry<String, String> entry : extraInfo.entrySet()) {
                    sb.append('\t')
                        .append(entry.getKey())
                        .append(" = ")
                        .append(entry.getValue())
                        .append('\n');
                }
            }

            /* Print prereqs if available. */
            if (!prereqs.isEmpty()) {
                sb.append("* Prereqs: ");
                for (String c : prereqs) {
                    sb.append(c).append(" ");
                }
                sb.append('\n');
            }

            return sb.toString();
        }
    }

    /**
     * Entrypoint. Creates the DB, prints all course information and exits.
     * @param args UNUSED
     */
    public static void main(String[] args) {
        CSCourseDB db = new CSCourseDB("berkeley-cs.xml");
        System.out.println(db);
    }
}
