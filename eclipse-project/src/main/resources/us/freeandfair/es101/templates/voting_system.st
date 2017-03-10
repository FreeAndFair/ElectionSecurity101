voting_system(election,schema) ::= <<
<div align="center">
<table>
<tr><th align="center"><h3>Choose a Candidate</h3></th></tr>
$election.candidates:{ candidate |
  <tr><td align="center">
      <button type="button" style="width:100%"
       onclick="window.location.href='$schema$?vote=$candidate$'">$candidate$</button></td></tr>
 }$
</table>
</div>
>>