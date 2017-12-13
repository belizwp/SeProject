<html>
<head>
    <title>Sheet and Share</title>

    <link rel="stylesheet" href="foundation.css">
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style type="text/css">

        @media print, screen and (min-width: 1081px) {
            body {
                display: flex;
                width: 100%;
                height: 100%;
                background: url('picture/cac2.jpg') 100% no-repeat;
                background-size: cover;
                background-position: center top;
            }
        }

        @media print, screen and (max-width: 1080px) {
            body {
                display: flex;
                width: 100%;
                height: 100%;
                background: url('picture/cac.jpg') 100% no-repeat;
                background-size: cover;
                background-position: center top;
            }
        }
    </style>
</head>
<body>

<form action="#">
    <div class="show_body" align="center">
        <div class="middle">
            <h6>เลือกรายชื่อวิชา</h6>
            <center>
                <table class="time">
                    <th class="tablinks active" onclick="openSubject(event, 'midterm')">กลางภาค</th>
                    <th class="tablinks" onclick="openSubject(event, 'final')">ไฟนอล</th>
                </table>
            </center>

            <ul class="menu2" id="midterm">
                <li><input type="image" class="previous" src="picture/previous.svg"/></li>
                <li>
                    <table class="sub">
                        <tr>
                            <td><a href="sheet.html">06016319 Introfuction to Computer System</a></td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                    </table>
                </li>
                <li><input type="image" class="next" src="picture/next.svg"/></li>
            </ul>

            <ul class="menu2" id="final">
                <li><input type="image" class="previous" src="picture/previous.svg"/></li>
                <li>
                    <table class="sub">
                        <tr>
                            <td><a href="sheet.html">Test final</a></td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                        <tr>
                            <td>06016319 Introfuction to Computer System</td>
                        </tr>
                    </table>
                </li>
                <li><input type="image" class="next" src="picture/next.svg"/></li>
            </ul>

        </div>
    </div>
</form>
<jsp:include page="navbar.html"/>
<script type="text/javascript">
    function openSubject(evt, subject) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("menu2");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(subject).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>

</body>
</html>
