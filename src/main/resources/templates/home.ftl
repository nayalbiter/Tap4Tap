<!DOCTYPE html>
<html>
    <head>
        <title>tap4tap Project - Homepage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/tap4tap/tap4tap.css">
    </head>
    <body>
        <h1>tap4tap</h1>
        <h2>Our CSD297 Project!</h2>

        <#if loggedIn>
            <a href="?cmd=logout">Log Out</a>
        <#else>
            <a href="?cmd=showLogin">Log In</a><br />
        </#if>
    </body>
</html>
