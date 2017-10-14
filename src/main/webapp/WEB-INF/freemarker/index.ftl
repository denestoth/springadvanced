<#import "spring.ftl" as spring />
<html>
    <body>
        <p>
            <a href="<@spring.url '/api/tickets/price?eventId=1000&dateTime=2018-07-15T22:30Z&userId=201&seats=1'/>">Get price of tickets</a>
        </p>
        <p>
            <a href="<@spring.url '/api/tickets/book?ticketId=1'/>">Book tickets</a>
        </p>
        <p>
            <a href="<@spring.url '/api/tickets/purchased?eventId=1000&dateTime=2018-07-15T22:30Z'/>">see sold tickets</a>
        </p>
        <p>
            <a href="<@spring.url 'api/tickets/purchasedPdf?eventId=1000&dateTime=2018-07-15T22:30Z'/>">see sold tickets as PDF</a>
        </p>
        <p>
            <a href="<@spring.url '/api/user/upload'/>">Upload users</a>
        </p>
        <p>
            <a href="<@spring.url '/api/event/upload'/>">Upload events</a>
        </p>
        <p>
            <a href="<@spring.url '/logout'/>">Logout</a>
        </p>
    </body>
</html>