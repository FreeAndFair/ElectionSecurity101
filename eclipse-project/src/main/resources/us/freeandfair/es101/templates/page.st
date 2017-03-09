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
<style>
.small_button {
	font-size: 16px;
	font-family: Helvetica, Arial, sans-serif;
}
.big_button {
	font-size: 32px;
	font-family: Helvetica, Arial, sans-serif;
}
select {
	font-size: 16px;
	font-family: Helvetica, Arial, sans-serif;
}	
h1 {
	font-family: Helvetica, Arial, sans-serif;
}
h2 {
	font-family: Helvetica, Arial, sans-serif;
}
h3 {
	font-family: Helvetica, Arial, sans-serif;
}
th {
	font-family: Helvetica, Arial, sans-serif;
}
td {
	font-family: Helvetica, Arial, sans-serif;
}
p {
	font-family: Helvetica, Arial, sans-serif;
}
</style>
<title>ES101</title>
</head>
<body>
$body$
</body>
</html>
>>
