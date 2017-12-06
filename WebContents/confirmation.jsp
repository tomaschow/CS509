<%@ page import="beans.Flight" %>
<%@ page import="beans.Airplane" %>
<%@ page import="beans.Trip" %>
<%@ page import="beans.Airport" %>
<%@ page import="util.HttpUtil" %>
<%@ page import="util.MockResult" %>
<%@ page import="core.Search" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.ParseException" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>World Plane Inc.</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <link href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <link href="./css/bootstrap.min.css" rel="stylesheet"/>
    <link href="./css/wpi.css" rel="stylesheet"/>
    <script src="./js/wpi.js"></script>
    <%
        ArrayList<Airport> airports = HttpUtil.INSTANCE.getAirports();
        ArrayList<Airplane> airplanes = HttpUtil.INSTANCE.getAirplanes();
        String searchType = request.getParameter("searchType");
        String flight1 = request.getParameter("flight1");
        String flight2 = request.getParameter("flight2");
        String flight3 = request.getParameter("flight3");
        String seat1 = request.getParameter("seat-type1");
        String seat2 = request.getParameter("seat-type2");
        String seat3 = request.getParameter("seat-type3");
    %>
</head>

<body>
<div class="container">
    <div class="row clearfix">

        <div class="well">
            <h1>
                Fly with WPI!
            </h1>
            <p>
                Please confirm the flights you have chosen!
            </p>
            <div class="bg-dark">
                <div class="row clearfix">

                    <div class="container-fluid text-left">
                        <%
                            ArrayList<String> flightNumbers = new ArrayList<>();
                            ArrayList<String> seatTypes = new ArrayList<>();

                            if (flight1 != null && seat1 != null) {
                                flightNumbers.add(flight1);
                                seatTypes.add(seat1);%>
                        <p><%=flight1 + " " + seat1%>
                        </p>
                        <%
                            }
                            if (flight2 != null && seat2 != null) {
                                flightNumbers.add(flight2);
                                seatTypes.add(seat2);
                        %>
                        <p><%=flight2 + " " + seat2%>
                        </p>
                        <%
                            }
                            if (flight3 != null && seat3 != null) {
                                flightNumbers.add(flight3);
                                seatTypes.add(seat3);
                        %>
                        <p><%=flight3 + " " + seat3%>
                        </p>
                        <%
                            }
                            if (searchType.equals("oneway")) {
                                if (HttpUtil.INSTANCE.reserveSeats(flightNumbers, seatTypes)) {
                        %><p>order SUCCESS!</p><%} else {%><p>order FAILED!</p><%
                        }
                    } else if (searchType.equals("roundtrip")) {
                        if (HttpUtil.INSTANCE.reserveSeats(flightNumbers, seatTypes)) {
                    %><p>Departure order SUCCESS!</p><%} else {%><p>Departure order FAILED!</p><%
                        }
                    %>
                        <h3>Please choose return trip</h3>
                        <div id="filterDiv" class="well col-sm-2 column">
                            <p> Filter </p>
                            <div class="btn-group btn-group-vertical" data-toggle="buttons">
                                <label class="btn active">
                                    <input type="checkbox" id='filter0stop' name='filter0stop'
                                           checked><span> Nonstop</span>
                                </label>
                                <label class="btn active">
                                    <input type="checkbox" id='filter1stop' name='filter1stop'
                                           checked><span> 1 Stop</span>
                                </label>
                                <label class="btn active">
                                    <input type="checkbox" id='filter2stop' name='filter2stop'
                                           checked><span> 2 Stops</span>
                                </label>
                            </div>
                        </div>
                        <div id="sortDiv" class="well col-sm-10 column">
                            <h3>
                                Sort part
                            </h3>
                            <select id="sort_option" class="form-control select select-primary" data-toggle="select">
                                <option value="sort_price_lo" selected>Price(Lowest)</option>
                                <option value="sort_price_hi">Price(Highest)</option>
                                <option value="sort_dep_early">Departure(Earliest)</option>
                                <option value="sort_dep_late">Departure(Latest)</option>
                                <option value="sort_arr_early">Arrival(Earliest)</option>
                                <option value="sort_arr_late">Arrival(Latest)</option>
                                <option value="sort_flightTime_lo">TravelTime(Lowest)</option>
                                <option value="sort_flightTime_hi">TravelTime(Highest)</option>
                            </select>
                            <div class="row clearfix">
                                <ul id="flight-show" class="pre-scrollable col-md-12 column">
                                    <%

                                        String depAirport = request.getParameter("dep-airport");
                                        String arrAirport = request.getParameter("arr-airport");
                                        String retDate = request.getParameter("ret-date");
                                        Search search = new Search();
                                        search.setAirplanes(airplanes);
                                        search.setAirports(airports);
                                        search.setDepAirportCode(arrAirport);
                                        search.setArrAirportCode(depAirport);
                                        ArrayList<Trip> mockTrips = new ArrayList<>();
                                        try {
                                            search.setDepDate(retDate);
                                            mockTrips = search.commenceSearch();
                                        } catch (ParseException e) {
                                    %>
                                    <li id="trip0" class="well row" style="text-align:center;">
                                        <h3>No result found! Please try other destinations/departures.</h3>
                                    </li>
                                    <%
                                        }
                                        int count = 0;
                                        if (mockTrips.size() == 0) {%>
                                    <li id="trip0" class="well row" style="text-align:center;">
                                        <h3>No result found! Please try other destinations/departures.</h3>
                                    </li>
                                    <%
                                    } else {
                                        for (Trip trip : mockTrips) {
                                            count++;
                                            long depTime = trip.getDepTimeMillis();
                                            long arrTime = trip.getArrTimeMillis();
                                    %>
                                    <li depTimeMillis="<%=depTime%>" arrTimeMillis="<%=arrTime%>"
                                        travelTime="<%= trip.getTotalTimeMinute() %>"
                                        totalPrice="<%=trip.getTotalPrice()%>" id="trip<%=count%>" class="well row">
                                        <div class="col-sm-2 column">
                                            <button id="modal-btn-<%=count%>" type="button"
                                                    href="#modal-container-<%=count%>"
                                                    data-toggle="modal" class="modalbtn btn btn-lg btn-success">
                                                Select
                                            </button>
                                            <form method="post" class="form-horizontal" role="form" id="orderForm"
                                                  action="./confirmation.jsp">
                                                <input name="searchType" type="hidden" value="oneway">
                                                <div class="modal fade" id="modal-container-<%=count%>"
                                                     role="dialog" aria-labelledby="myModalLabel"
                                                     aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close"
                                                                        data-dismiss="modal"
                                                                        aria-hidden="true">X
                                                                </button>
                                                                <h4 class="modal-title" id="myModalLabel">
                                                                    Confirm Order
                                                                </h4>
                                                            </div>
                                                            <div class="modal-body">


                                                                <% int mCount = 0;
                                                                    for (Flight flight : trip.getFlights()) {
                                                                        mCount++;%>
                                                                <div class="form-group">

                                                                    <label class="col-sm-2"><%=flight.getFlightNumber()%>
                                                                    </label><input name="flight<%=mCount%>"
                                                                                   type="hidden"
                                                                                   value="<%=flight.getFlightNumber()%>">
                                                                    <div class="col-sm-6">
                                                                        <fieldset>
                                                                            <% if (flight.hasCoach()) { %>
                                                                            <label class="check col">
                                                                                <input id="coach"
                                                                                       type="radio"
                                                                                       name="seat-type<%=mCount%>"
                                                                                       checked="checked"
                                                                                       value="Coach">
                                                                                <span class="inline-label">Coach</span>
                                                                            </label>
                                                                            <%
                                                                                }
                                                                                if (flight.hasFirst()) {
                                                                            %>
                                                                            <label class="check col">
                                                                                <input id="firstclass"
                                                                                       type="radio"
                                                                                       name="seat-type<%=mCount%>"
                                                                                       value="FirstClass">
                                                                                <span class="inline-label">FirstClass</span>
                                                                            </label>
                                                                            <%}%>
                                                                            <p><%=flight.getDepAirportCode()%>
                                                                                -> <%=flight.getArrAirportCode()%>
                                                                            </p>
                                                                        </fieldset>
                                                                        <div class="row clearfix">
                                                                            <div class="col-sm-6">
                                                                                Departure
                                                                                <p><%=flight.getLocalDepTime()%>
                                                                                </p>
                                                                            </div>
                                                                            <div class="col-sm-6">
                                                                                Arrival <p><%=flight.getLocalArrTime()%>
                                                                            </p>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-sm-3">
                                                                        <%if (flight.hasCoach()) {%>
                                                                        Coach: <p
                                                                            id="seat-price-<%=mCount%>"><%=flight.getCoachClassPrice()%>
                                                                    </p>
                                                                        <%
                                                                            }
                                                                            if (flight.hasFirst()) {
                                                                        %>
                                                                        FirstClass: <p
                                                                            id="seat-price-<%=mCount%>"><%=flight.getFirstClassPrice()%>
                                                                    </p>
                                                                        <%}%>
                                                                    </div>
                                                                </div>
                                                                <%}%>

                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button"
                                                                        class="btn btn-default"
                                                                        data-dismiss="modal">Cancel
                                                                </button>
                                                                <button name="confirmOrder" type="submit"
                                                                        class="btn btn-primary">Confirm
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="col-sm-7 column">
                                            <div class="row clearfix">
                                                <div class="col-sm-2 column">
                                                    <h3>
                                                        <%= trip.getFlights().get(0).getDepAirportCode() %>
                                                    </h3>
                                                </div>
                                                <div class="col-sm-2 column">
                                                    <h5>
                                                        <%= trip.getFlights().get(0).getLocalDepTime() %>
                                                    </h5>
                                                </div>
                                                <div class="col-sm-2 column">
                                                    <h3>
                                                        ->
                                                    </h3>
                                                </div>
                                                <div class="col-sm-2 column">
                                                    <h3>
                                                        <%= trip.getFlights().get(trip.getFlights().size() - 1).getArrAirportCode() %>
                                                    </h3>
                                                </div>
                                                <div class="col-sm-2 column">
                                                    <h5>
                                                        <%= trip.getFlights().get(trip.getFlights().size() - 1).getLocalArrTime() %>
                                                    </h5>
                                                </div>
                                            </div>
                                            <div class="row clearfix">
                                                <div class="col-sm-7 column">
                                                    <p>
                                                        Duration <%= trip.getTotalTime() %>
                                                    </p>
                                                    <a href="#"><%=trip.getFlights().size() - 1%>
                                                    </a> stops
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-3 column">

                                            <h4><strong>
                                                Starting at
                                            </strong></h4>
                                            $
                                            <price><%= trip.getTotalPrice()%>
                                            </price>

                                        </div>
                                    </li>
                                    <%
                                            }
                                        }
                                    %>
                                </ul>
                            </div>
                        </div>
                        <%

                            } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>