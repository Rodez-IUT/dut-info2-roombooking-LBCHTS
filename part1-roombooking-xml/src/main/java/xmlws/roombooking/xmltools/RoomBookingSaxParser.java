package xmlws.roombooking.xmltools;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomBookingSaxParser implements RoomBookingParser {

    @Override
    public RoomBooking parse(InputStream inputStream) {
        RoomBooking roomBooking = new RoomBooking();
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(inputStream, new RoomBookingHandler(roomBooking));
        } catch (Exception e) {
            roomBooking = null;
            e.printStackTrace();
        }
        return roomBooking;
    }

    private class RoomBookingHandler extends DefaultHandler {
        private RoomBooking roomBooking;

        private boolean label;
        private boolean username;
        private boolean startDate;
        private boolean endDate;

        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        public RoomBookingHandler (RoomBooking roomBooking) {
            this.roomBooking = roomBooking;
        }

        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts)
                throws SAXException {

            switch (localName) {
                case "label"     : label     = true; break;
                case "username"  : username  = true; break;
                case "startDate" : startDate = true; break;
                case "endDate"   : endDate   = true; break;
            }
        }

        public void characters(char ch[], int start, int length)
                throws SAXException {
            if (label) {
                this.roomBooking.setRoomLabel(new String(ch, start, length));
                label = false;
            } else if (username) {
                this.roomBooking.setUsername(new String(ch, start, length));
                username = false;
            } else if (startDate) {
                try {
                    this.roomBooking.setStartDate(sdf.parse(new String(ch, start, length)));
                    startDate = false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (endDate) {
                try {
                    this.roomBooking.setEndDate(sdf.parse(new String(ch, start, length)));
                    endDate = false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
