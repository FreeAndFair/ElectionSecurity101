voting_system(election,explanation,schema) ::= <<
<div align="center">

<b>$election.my_name$</b><br/>
<i>$election.my_date$</i>

<h1>Explanation</h1>
<p>$explanation$</p>

<table border=0>
<tr><th align="center">Choose a Candidate</th></tr>
$election.candidates:{ candidate |
  <tr><td align="center"><a href="$schema$?vote=$candidate$">$candidate$</a></td></tr>
 }$
 </table>
</div>
>>