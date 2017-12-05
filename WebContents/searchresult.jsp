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
    <title>World Plane Inc. Search Result</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <link href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <link href="./css/bootstrap.min.css" rel="stylesheet"/>
    <!--  <link href="./css/flat-ui.min.css" rel="stylesheet">
     <link href="./css/demo.css" rel="stylesheet"> -->
    <link href="./css/wpi.css" rel="stylesheet"/>
    <script src="./js/wpi.js"></script>
    <%
        ArrayList<Airport> airports = HttpUtil.INSTANCE.getAirports();
        ArrayList<Airplane> airplanes = HttpUtil.INSTANCE.getAirplanes();
    %>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-14 column">
            <div class="well row clearfix">
                <div class="col-md-12 column">
                    <form role="form" class="form-inline" action="./test.jsp">
                        <fieldset class="sub-nav-select">
                            <label id="flight-type-one-way-label" class="check col" for="flight-type-one-way">
                                <input id="flight-type-one-way" type="radio" name="flight-type" value="oneway"
                                       checked="checked">
                                <span class="inline-label">One way</span>
                            </label>
                            <label id="flight-type-roundtrip-label" class="check col" for="flight-type-roundtrip">
                                <input id="flight-type-roundtrip" type="radio" name="flight-type" value="roundtrip">
                                <span class="inline-label">Roundtrip</span>
                            </label>
                        </fieldset>
                        <div class="text-left">
                            <label class="index-label-small" for="dep-airport">Departure Airport</label>
                            <select id="dep-airport" name="dep-airport" class="form-control select select-primary"
                                    data-toggle="select">
                                <%for (Airport airport : airports) {%>
                                <option value="<%=airport.getCode()%>"><%=airport.getCode() + ", " + airport.getName()%>
                                </option>
                                <%}%>
                            </select><br>
                            <label class="arr-label-small" for="arr-airport">Arrival Airport</label>
                            <select id="arr-airport" name="arr-airport" class="form-control select select-primary"
                                    data-toggle="select">
                                <%for (Airport airport : airports) {%>
                                <option value="<%=airport.getCode()%>"><%=airport.getCode() + ", " + airport.getName()%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                        <div class="text-left">
                            <label class="date-label-small" for="dep-date">Departure Date</label><input
                                class="form-control datepick" type="text" id="dep-date" name="dep-date">
                            <label class="date-label-small" for="ret-date" id="ret-date-label">Return Date</label><input
                                class="form-control datepick" type="text" id="ret-date" name="ret-date">
                        </div>
                        <div class="text-left">
                            <label class="date-label-small"><input id="nonstop" type="checkbox"/>Nonstop</label>
                            <button type="submit" class="btn btn-primary btn-large">Search</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row clearfix">
                <div id="filterDiv" class="well col-sm-2 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <p><strong>Stops</strong></p>
                            <p>NonStop</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p>1 Stop</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p>2 Stops</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p><strong>Departure Time</strong></p>
                            <p>Morning</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p>Afternoon</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p>Night</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p><strong>Arrival Time</strong></p>
                            <p>Morning</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p>Afternoon</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>
                            <p>Night</p>
                            <label class="switch">
                                <input checked="checked" type="checkbox">
                                <span class="slider round"></span>
                            </label>

                        </div>
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
                        <option value="sort_dur_short">Duration(Shortest)</option>
                        <option value="sort_dur_long">Duration(Longest)</option>
                    </select>
                    <div class="row clearfix">
                        <div id="flight-show" class="pre-scrollable col-md-12 column">
                            <%

                                //    if(searchType.equals("roundtrip")){
                                //    	// reverse departure and arrival
                                //    	Search retSearch = new Search();
                                //    	retSearch.setAirplanes(airplanes);
                                //    	retSearch.setAirports(airports);
                                //    	retSearch.setDepAirportCode(arrAirport);
                                //    	retSearch.setArrAirportCode(depAirport);
                                //    	retSearch.setDepDate(retDate);
                                //    	try{
                                //    		mockTrips = search.commenceSearch();
                                //    	} catch(ParseException e){
                                //    	e.printSatc
                                //    }
                                // }
                                // ArrayList<Trip> mockTrips = MockResult.INSTANCE.init();

                                String depAirport = request.getParameter("dep-airport");
                                String arrAirport = request.getParameter("arr-airport");
                                String depDate = request.getParameter("dep-date");
                                String retDate = request.getParameter("ret-date");
                                String searchType = request.getParameter("flight-type");
                                Search search = new Search();
                                search.setAirplanes(airplanes);
                                search.setAirports(airports);
                                search.setDepAirportCode(depAirport);
                                search.setArrAirportCode(arrAirport);
                                search.setDepDate(depDate);
                                ArrayList<Trip> mockTrips = new ArrayList<>();
                                try {
                                    mockTrips = search.commenceSearch();
                                } catch (ParseException e) {
                            %>
                            <div id="trip0" class="well row" style="text-align:center;">
                                <h3>No result found! Please try other destinations/departures.</h3>
                            </div>
                            <%
                                }


                                int count = 0;
                                if (mockTrips.size() == 0) {%>
                            <div id="trip0" class="well row" style="text-align:center;">
                                <h3>No result found! Please try other destinations/departures.</h3>
                            </div>
                            <%
                            } else {
                                for (Trip trip : mockTrips) {
                                    count++;
                            %>
                            <div id="trip<%=count%>" class="well row">
                                <div class="col-sm-2 column">
                                    <button id="modal-btn-<%=count%>" type="button" href="#modal-container-<%=count%>"
                                            data-toggle="modal" class="modalbtn btn btn-lg btn-success">
                                        Select
                                    </button>
                                    <form method="post" class="form-horizontal" role="form" id="orderForm" action="#"
                                          onsubmit="return canSubmit(<%=searchType%>);">
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

                                                        <form class="form-horizontal" role="form">
                                                            <% int mCount = 0;
                                                                for (Flight flight : trip.getFlights()) {
                                                                    mCount++;%>
                                                            <div class="form-group">
                                                                <label class="col-sm-2"><%=flight.getFlightNumber()%>
                                                                </label>
                                                                <div class="col-sm-6">
                                                                    <fieldset>
                                                                        <% if (flight.hasCoach()) { %>
                                                                        <label class="check col">
                                                                            <input id="coach"
                                                                                   type="radio"
                                                                                   name="seat-type<%=mCount%>"
                                                                                   checked="checked">
                                                                            <span class="inline-label">Coach</span>
                                                                        </label>
                                                                        <%
                                                                            }
                                                                            if (flight.hasFirst()) {
                                                                        %>
                                                                        <label class="check col">
                                                                            <input id="firstclass"
                                                                                   type="radio"
                                                                                   name="seat-type<%=mCount%>">
                                                                            <span class="inline-label">FirstClass</span>
                                                                        </label>
                                                                        <%}%>
                                                                        <p><%=flight.getDepAirportCode()%>
                                                                            -> <%=flight.getArrAirportCode()%>
                                                                        </p>
                                                                    </fieldset>
                                                                </div>
                                                                <div class="col-sm-3">
                                                                    <%if (flight.hasCoach()) {%>
                                                                    <p id="seat-price-<%=mCount%>"><%=flight.getCoachClassPrice()%>
                                                                    </p>
                                                                    <%} else {%>
                                                                    <p id="seat-price-<%=mCount%>"><%=flight.getFirstClassPrice()%>
                                                                    </p>
                                                                    <%}%>
                                                                </div>
                                                            </div>
                                                            <%}%>
                                                        </form>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button"
                                                                class="btn btn-default"
                                                                data-dismiss="modal">Cancel
                                                        </button>
                                                        <button type="submit"
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
                                            <h3>
                                                ->
                                            </h3>
                                        </div>
                                        <div class="col-sm-2 column">
                                            <h3>
                                                <%= trip.getFlights().get(trip.getFlights().size() - 1).getArrAirportCode() %>
                                            </h3>
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-sm-7 column">
                                            <p>
                                                Duration <%= trip.getFlights().get(0).getFlightTime() %> minutes
                                                (<%=trip.getFlights().size()%>) stop(s)
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3 column">

                                    <h4><strong>
                                        Starting at $<%= trip.getTotalPrice()%>
                                    </strong></h4>

                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>