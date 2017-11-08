<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>World Plane Inc.</title>
    <script src="./js/jquery-1.9.1.min.js" type="javascript"></script>
    <link href="./css/bootstrap.min.css" rel="stylesheet"/>
    <link href="./css/wpi.css" rel="stylesheet"/>
    <script src="./js/bootstrap.min.js" type="javascript"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <ul class="nav nav-pills">
                        <li class="active">
                            <a href="#">Home</a>
                        </li>
                        <li>
                            <a href="#">More</a>
                        </li>
                    </ul>
                </div>
            </div>
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
                        <label id="flight-type-roundtrip-label" class="check col" for="flight-type-roundtrip">
                            <input id="flight-type-roundtrip" type="radio" name="flight-type" checked="checked">
                            <span class="inline-label">Roundtrip</span>
                        </label>
                        <label id="flight-type-one-way-label" class="check col" for="flight-type-one-way">
                            <input id="flight-type-one-way" type="radio" name="flight-type">
                            <span class="inline-label">One way</span>
                        </label>
                    </fieldset>
                    <div class="container-fluid text-left">
                        <label class="index-label-small" for="dep-airport">Departure Airport</label><input type="text" class="form-control" id="dep-airport" name="dep-airport"/>
                        <label class="index-label-small" for="arr-airport">Arrival Airport</label><input type="text" class="form-control" id="arr-airport" name="arr-airport"/>

                    </div>
                    <div class="container-fluid text-left">
                        <label class="index-label-small" for="dep-date">Departure Date</label><input type="text" class="form-control" id="dep-date" name="dep-date"/>
                        <label class="index-label-small" for="arr-date">Arrival Date</label><input type="text" class="form-control" id="arr-date" name="arr-date"/>
                    </div>
                    <div class="container-fluid text-left">
                        <label><input id="nonstop" type="checkbox"/>Nonstop</label>
                        <button type="submit" class="btn btn-primary btn-large">Search</button>
                    </div>
                </form>
            </div>


            </div>


        </div>
    </div>
</div>
</body>
</html>