voting_system(voting_system_data) := <<
<div align="center">

<b>Pick one of the following choices:</b>

<table border=0>
<th><td>Choose a Candidate</td></th>
$voting_system_data.my_election.candidates:{ candidate |
  <tr><td><a href="/$voting_system_data.schema$?vote=\"$candidate.name$\"">$candidate.name$</a></td></tr>
 }
 </table>
</div>
>>