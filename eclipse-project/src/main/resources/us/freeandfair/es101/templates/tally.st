tally(tally_data) ::= <<
<div align="center">
<table border=0>
<th align="center">Candidate</th><th align="center">Votes</th>
$tally_data:{ candidate |
  <tr><td align="center">$candidate$</td><td align="center">$tally_data.(candidate)$</td></tr> 
 }$
 </table>
</div>
>>