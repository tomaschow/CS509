<%@ page import="java.util.ArrayList" %>
<%@ page import="util.HttpUtil" %>
<%@ page import="beans.Airport" %>
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
  %>
</head>

<body>
<div class="container">
  <div class="row clearfix">
      
    <div class="jumbotron well">
      <h1>
        Fly with WPI!
      </h1>
      <p>
        World Plane Inc is your airplane ticket reservation system that provides best offers every day!
      </p>
      <div class="jumbotron bg-dark">
        <form role="form" class="form-inline" action="./searchresult.jsp">
          <fieldset class="sub-nav-select">
            <label id="flight-type-one-way-label" class="check col" for="flight-type-one-way">
              <input id="flight-type-one-way" type="radio" name="flight-type" value="oneway" checked="checked">
                <span class="inline-label">One way</span>
            </label>
            <label id="flight-type-roundtrip-label" class="check col" for="flight-type-roundtrip">
              <input id="flight-type-roundtrip" type="radio" name="flight-type" value="roundtrip" >
                <span class="inline-label">Roundtrip</span>
            </label>
          </fieldset>
          <div class="text-left">
            <label class="index-label-small" for="dep-airport">Departure Airport</label>
                      <select id="dep-airport" name="dep-airport" class="form-control select select-primary" data-toggle="select">
                        <%for(Airport airport : airports){%>
                        <option value="<%=airport.getCode()%>"><%=airport.getCode()+", "+airport.getName()%></option>
                        <%}%>
                    </select><br>
            <label class="arr-label-small" for="arr-airport">Arrival Airport</label>                      
                      <select id="arr-airport" name="arr-airport" class="form-control select select-primary" data-toggle="select">
                        <%for(Airport airport : airports){%>
                        <option value="<%=airport.getCode()%>"><%=airport.getCode()+", "+airport.getName()%></option>
                        <%}%>
                    </select>
          </div>
          <div class="text-left">
            <label class="date-label-small" for="dep-date">Departure Date</label><input class="form-control datepick" type="text" id="dep-date" name="dep-date">
            <label class="date-label-small" for="ret-date" id="ret-date-label">Return Date</label><input class="form-control datepick" type="text" id="ret-date" name="ret-date">
          </div>
          <div class="text-left">
          
          <button type="submit" class="btn btn-primary btn-large">Search</button>
          </div>
        </form>   
    </div>
  </div>
</div>
</body>
</html>