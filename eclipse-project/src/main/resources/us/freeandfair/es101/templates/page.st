page(enable_refresh, refresh, title, enable_results, results, body) ::= <<
<html>
<head>
$if(enable_refresh)$
<meta http-equiv="refresh" content="$refresh$">
$endif$
<title>$title$</title>
</head>
<body>
<h1>$title$</h1>

$if(enable_results)$
$results$
$endif$

$body$

</body>
</html>
>>
