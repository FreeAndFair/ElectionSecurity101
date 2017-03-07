voting_system(election,explanation,schema) ::= <<
<div align="center">

<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

<h1>Explanation</h1>
$explanation$

<p><b>Pick one of the following choices:</b></p>

<table border=0>
<th><td>Choose a Candidate</td></th>
$election.candidates:{ candidate |
  <tr><td><a href="$schema$?vote=$candidate$">$candidate$</a></td></tr>
 }$
 </table>
</div>
>>