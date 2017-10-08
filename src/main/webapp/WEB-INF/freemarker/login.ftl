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
                <label class="col-md-4 control-label" for="email">E-mail:</label>
                <div class="col-md-8">
                    <input id="email" type="text" name="email" />
                 </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Password:</label>
                <div class="col-md-8">
                    <input id="passwordI" type="password" name="password" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12" align="center">
                    <input name="submit" type="submit" value="submit" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="rememberme">Remember:</label>
                <div class="col-md-8">
                    <input id="rememberme" name="rememberme" type="checkbox" />
                </div>
            </div>
        </form>
        <div>
            User: first_user@foo.com/password
            <br />
            Admin: second_user@foo.com/password
        </div>
    </body>
</html>