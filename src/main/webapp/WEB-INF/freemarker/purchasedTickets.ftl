<#import "spring.ftl" as spring />
<html>
    <head>
        <style>
            tr {
                border: 1px solid black;
            }
            td {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <h1>Purchased tickets</h1>
        <table>
            <tr>
                <th>Event</th>
                <th>Date</th>
                <th>Seat</th>
            </tr>
            <#list tickets as ticket>
                <tr>
                    <td>${ticket.event}</td>
                    <td>${ticket.localDateTime}</td>
                    <td>${ticket.seat}</td>
                </tr>
            </#list>
        </table>
    </body>
</html>