page(enable_refresh, refresh, body) ::= <<
<html>
<head>
$if(enable_refresh)$
<meta http-equiv="refresh" content="$refresh$">
$endif$
<link rel="icon" href="/cropped-FF-favicon-01-32x32.png" sizes="32x32" />
<link rel="icon" href="/cropped-FF-favicon-01-192x192.png" sizes="192x192" />
<link rel="apple-touch-icon-precomposed" href="/cropped-FF-favicon-01-180x180.png" />
<meta name="msapplication-TileImage" content="/cropped-FF-favicon-01-270x270.png" />
<link rel="STYLESHEET" type="text/css" href="style.css">
<title>ES101</title>
</head>
<body>
$body$
</body>
</html>
>>
