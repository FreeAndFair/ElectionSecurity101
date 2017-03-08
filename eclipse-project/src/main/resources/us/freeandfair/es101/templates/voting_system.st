voting_system(election,explanation,schema) ::= <<
<div align="center">

<h2>$election.name$ &ndash; $election.date$</h2>
<h3>$election.description$</h3>

<table border=0>
<tr><th align="center">Choose a Candidate</th></tr>
$election.candidates:{ candidate |
  <tr><td align="center"><a href="$schema$?vote=$candidate$">$candidate$</a></td></tr>
 }$
 </table>
 
<p>$explanation$</p>

</div>
>>