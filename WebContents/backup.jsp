<%@ page import="beans.Flight" %>
<%@ page import="util.HttpUtil" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String code = request.getParameter("dep-airport");
    String date = request.getParameter("dep-date");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>World Plane Inc.</title>
    <script src="./js/jquery-1.9.1.min.js" type="javascript"></script>
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/bootstrap.min.js" type="javascript"></script>
</head>
<body>
<h4>
    <%
        ArrayList<Flight> flights = HttpUtil.INSTANCE.getFlights(date, code);
        out.println("Displaying departure flights from " + code + " at " + date);%>
</h4>
<p>
    <% out.println("FlightNumber\tDeparture\tDepartureTime\t\t\t\tArrival\tArrivalTime\t\t\t\t\t" +
            "FC-Booked\tFC-Price\t\t\tEC-Booked\tEC-Price\t\t" +
            "TravelTime(minutes)"); %>
</p>
<p>
    <%
        for (Flight flight : flights) {
            out.println(flight.getFlightNumber() + "\t\t\t" + flight.getDepAirportCode()
                    + "\t\t\t" + flight.getDepTime() + "\t\t" + flight.getArrAirportCode()
                    + "\t\t" + flight.getArrTime() + "\t\t" + flight.getFirstClassBooked()
                    + "\t\t\t" + flight.getFirstClassPrice() + "\t\t\t\t" + flight.getCoachClassBooked()
                    + "\t\t\t" + flight.getCoachClassPrice() + "\t\t\t" + flight.getFlightTime());
        }
    %>
</p>

</body>
</html>