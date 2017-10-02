<#import "spring.ftl" as spring />
<html>
    <body>
        <p>
            ${someAttribute}
        </p>
        <p>
            <a href="./api/tickets/price?eventId=1&dateTime=2018-07-15T22:30Z&userId=1&seats=1">Get price of tickets</a>
        </p>
        <p>
            <a href="./api/tickets/book?ticketId=1">Book tickets</a>
        </p>
        <p>
            <a href="./api/tickets/purchased?eventId=1&dateTime=2018-07-15T22:30Z">see sold tickets</a>
        </p>
        <p>
            <a href="./api/tickets/purchasedPdf?eventId=1&dateTime=2018-07-15T22:30Z">see sold tickets</a>
        </p>
    </body>
</html>