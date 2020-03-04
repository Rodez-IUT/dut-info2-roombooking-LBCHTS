package xmlws.roombooking.xmltools;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomBookingSaxParser extends RoomBooking implements RoomBookingParser {
    private RoomBooking roomBooking = new RoomBookingSaxParser();
    @Override
    public RoomBooking parse(InputStream inputStream) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(inputStream, new RoomBookingHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomBooking;
    }

    private class RoomBookingHandler extends DefaultHandler {
        private boolean label;
        private boolean username;
        private boolean startDate;
        private boolean endDate;
        
        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts)
                throws SAXException {
            System.out.println("In element: "+localName);

            if (localName.equals("label")) {
                roomBooking.setRoomLabel(qName);
            } else if(localName.equals("username")) {
                roomBooking.setUsername(qName);
            } else if(localName.equals("startDate")) {
                roomBooking.setStartDate();
            } else if(localName.equals("endDate")) {
                roomBooking.setEndDate(String.format(qName, sdf);
            }

        }

        public void characters(char ch[], int start, int length)
                throws SAXException {
            System.out.println(new String(ch, start, length));
        }
    }
}
