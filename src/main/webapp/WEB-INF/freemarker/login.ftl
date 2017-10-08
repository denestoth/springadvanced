<#import "spring.ftl" as spring />
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <form class="form-horizontal" login-page="/denes-toth/login" method="POST">
            <div class="form-group">
                <label class="col-md-4 control-label" for="emailInput">E-mail:</label>
                <div class="col-md-8">
                    <input id="emailInput" type="text" name="email" />
                 </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="passwordInput">Password:</label>
                <div class="col-md-8">
                    <input id="passwordInput" type="password" name="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12" align="center">
                    <input name="submit" type="submit" value="submit" />
                </div>
            </div>
        </form>
    </body>
</html>