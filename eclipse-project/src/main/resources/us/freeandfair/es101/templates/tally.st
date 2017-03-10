tally(tally_data) ::= <<
<div align="center">
<table>
<tr><th colspan="3" class="title"><b>Current Results</b></th></tr>
<tr><th class="tally">Candidate</th><th class="tally">&nbsp;&nbsp;Legitimate&nbsp;&nbsp;<br/>Tally</th><th class="tally">Hacked<br/>Tally</th></tr>
$tally_data.legitimateTally:{ candidate |
  <tr><td class="tally">$candidate$</td><td class="tally">$tally_data.legitimateTally.(candidate)$</td><td class="tally">$tally_data.hackedTally.(candidate)$</td></tr> 
 }$
</table>
</div>
>>