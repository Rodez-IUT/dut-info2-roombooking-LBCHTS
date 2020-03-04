package xmlws.roombooking.xmltools;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class RoomBookingDomParser implements RoomBookingParser {
    @Override
    public RoomBooking parse(InputStream inputStream) {
        RoomBooking roomBooking = new RoomBooking();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DocumentBuilderFactory dbf = 
    }
}
