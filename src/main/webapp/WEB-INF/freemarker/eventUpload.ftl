<#import "spring.ftl" as spring />
<html>
    <body>
        <form method="POST" action="/denes-toth/api/event/upload" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>Select a file to upload</td>
                    <td><input type="file" name="file" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>