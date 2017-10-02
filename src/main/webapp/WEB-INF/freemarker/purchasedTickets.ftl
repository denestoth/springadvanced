<html>
    <body>
        <h1>Purchased tickets</h1>
        <table>
            <th>
                <td>Event</td>
                <td>Date</td>
                <td>Seat</td>
            </th>
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