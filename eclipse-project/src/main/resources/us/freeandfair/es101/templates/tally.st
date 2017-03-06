tally(tally_data) ::= <<
<div align="center">
<table border=0>
<th>Candidate</th><th>Votes</th>
$tally_data:{ candidate |
  <tr><td>$candidate$</td><td>$tally_data.(candidate)$</td></tr> 
 }$
 </table>
</div>
>>