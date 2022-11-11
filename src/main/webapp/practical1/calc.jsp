<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>

        .container {
            height: 500px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .content {
            width: 600px;
            height: 400px;
        }

        .content form {
            width: 100%;
        }

        .content form > .sub {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .content form > .sub input {
            width: calc(40%);
            margin: 8px;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="content">
        <h2 class="text-center bg-warning text-white mb-3 p-2">Calculator</h2>
        <form method="post" name="frmCalc">
            <div class="form-group mb-3">
                <input class="form-control" type="text" name="txtNum1" placeholder="Enter number 1"/>
            </div>
            <div class="form-group mb-3">
                <input class="form-control" type="text" name="txtNum2" placeholder="Enter number 2"/>
            </div>
            <div class="sub">
                <input class="btn btn-primary" type="submit" name="btnCalc" value="+"/>
                <input class="btn btn-primary" type="submit" name="btnCalc" value="-"/>
                <input class="btn btn-primary" type="submit" name="btnCalc" value="x"/>
                <input class="btn btn-primary" type="submit" name="btnCalc" value="/"/>
            </div>
        </form>
        <div class="card card-body mt-3">
            <h6 class="text-danger text-center m-0">${error}</h6>
            <h4 class="text-primary text-center m-0">${result}</h4>
        </div>
    </div>
</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>