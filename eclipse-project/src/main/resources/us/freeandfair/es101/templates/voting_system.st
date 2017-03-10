voting_system(election,explanation,schema) ::= <<
$header(election)$

<div align="center">
<table>
<tr><th align="center">Choose a Candidate</th></tr>
$election.candidates:{ candidate |
  <tr><td align="center">
      <button type="button"
       onclick="window.location.href='$schema$?vote=$candidate$'">$candidate$</button></td></tr>
 }$
</table>
</div>
 
<p>$explanation$</p>
>>