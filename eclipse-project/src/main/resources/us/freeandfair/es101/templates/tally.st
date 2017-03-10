tally(tally_data) ::= <<
<div align="center">
<table>
<tr><th colspan="3" class="large">Current Results</th></tr>
<tr><th align="center">Candidate</th><th align="center">&nbsp;&nbsp;Legitimate&nbsp;&nbsp;<br/>Tally</th><th align="center">Hacked<br/>Tally</th></tr>
$tally_data.legitimateTally:{ candidate |
  <tr><td align="center">$candidate$</td><td align="center">$tally_data.legitimateTally.(candidate)$</td><td align="center">$tally_data.hackedTally.(candidate)$</td></tr> 
 }$
</table>
</div>
>>