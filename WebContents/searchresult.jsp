<%@ page import="beans.Flight" %>
<%@ page import="beans.Airplane" %>
<%@ page import="beans.Trip" %>
<%@ page import="beans.Airport" %>
<%@ page import="util.MockResult" %>
<%@ page import="java.util.ArrayList" %>
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
        ArrayList<Trip> mockTrips = MockResult.INSTANCE.init();
    %>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-14 column">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    
          <form role="form" class="form-inline" action="./searchresult.jsp">
          <fieldset>
            <label id="flight-type-one-way-label" class="check col" for="flight-type-one-way">
              <input id="flight-type-one-way" type="radio" name="flight-type" checked="checked">
                <span class="inline-label">One way</span>
            </label>
            <label id="flight-type-roundtrip-label" class="check col" for="flight-type-roundtrip">
              <input id="flight-type-roundtrip" type="radio" name="flight-type" >
                <span class="inline-label">Roundtrip</span>
            </label>
          </fieldset>
          <div class="text-left">
            <label class="index-label-small" for="dep-airport">Departure Airport</label><input type="text" class="form-control" id="dep-airport" name="dep-airport"/>
            <label class="index-label-small" for="arr-airport">Arrival Airport</label><input type="text" class="form-control" id="arr-airport" name="arr-airport"/>
          </div>
          <div class="text-left">
            <label class="index-label-small" for="dep-date">Departure Date</label><input class="form-control datepick" type="text" id="dep-date" name="dep-date">
              <label class="index-label-small" for="ret-date" id="ret-date-label">Return Date</label><input class="form-control datepick" type="text" id="ret-date" name="ret-date">
          </div>
          <div class="text-left">
            <label><input id="nonstop" type="checkbox"/>Nonstop</label>
          <button type="submit" class="btn btn-primary btn-large">Search</button>
          </div>
        </form> 
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-3 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <h3>
                        Filter part
                    </h3> 
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
                   
                        </div>
                    </div>
                    
                </div>
                <div class="col-md-9 column">
                    <h3>
                               Filter part
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
                        <div class="col-md-12 column">
                            <h3>
                                <%
                                int count = 0;
                                for(Trip trip : mockTrips){ count++;%>
                                    <!-- <div class="col-md-12 column">
                                        <p><%= trip.getFlights().get(0).toString() %></p>
                                    </div> -->
                                    
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-8 column">
                    <div class="row clearfix">
                        <div class="col-md-2 column">
                             <button type="button" href="#modal-container-<%=count%>" data-toggle="modal" class="btn btn-lg btn-success">Select</button>
                                    <div class="modal fade" id="modal-container-<%=count%>" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                                                <h4 class="modal-title" id="myModalLabel">
                                                    Confirm Order
                                                </h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Booking flight no. <%= trip.getFlights().get(0).getFlightNumber() %></p>
                                                <form class="form-horizontal" role="form" action="./">
                                                    <div class="form-group">
                                                        <label for="uname" class="col-sm-2 control-label">A</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" id="uname" name="uname" class="form-control well" placeholder="aaa"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="upwd" class="col-sm-2 control-label">B</label>
                                                        <div class="col-sm-9">
                                                            <input type="password" id="upwd" name="upwd" class="form-control well" placeholder="bbb"/>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                 <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button> <button type="submit" class="btn btn-primary">Confirm</button>
                                            </div>
                                        </div>
                                        
                                    </div>
                                    
                                </div>
                        </div>
                        <div class="col-md-6 column">
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <p>
                                        Starting at <%= trip.getFlights().get(0).getCoachClassPrice() %>
                                    </p>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <p>
                                        Duration <%= trip.getFlights().get(0).getFlightTime() %> minutes
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 column">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <p>
                                <%= trip.getFlights().get(0).getFlightNumber() %>
                            </p>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div class="row clearfix">
                                <div class="col-md-2 column">
                                    <p>
                                        <%= trip.getFlights().get(0).getDepAirportCode() %>
                                    </p>
                                </div>
                                <div class="col-md-2 column">
                                    <p>
                                        ->
                                    </p>
                                </div>
                                <div class="col-md-2 column">
                                    <p>
                                        <%= trip.getFlights().get(0).getArrAirportCode() %>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

                                 <%}%>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>